package com.glootie.networking.qr

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.SparseArray
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.glootie.networking.R
import com.glootie.networking.base.BaseActivity
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import java.io.IOException
import javax.security.auth.callback.Callback


class TicketScannerActivity : BaseActivity() {

    var surfaceView: SurfaceView? = null
    var txtBarcodeValue: TextView? = null
    private var barcodeDetector: BarcodeDetector? = null
    private var cameraSource: CameraSource? = null
    private val REQUEST_CAMERA_PERMISSION = 201
    var btnAction: Button? = null
    var intentData = ""
    var isUrl = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code_picture)
        initViews()
    }

    private fun initViews() {
        txtBarcodeValue = findViewById(R.id.txtBarcodeValue)
        surfaceView = findViewById(R.id.surfaceView)
        btnAction = findViewById(R.id.btnAction)
    }

    fun onClick(view: View) {
        if (intentData.isNotEmpty()) {
            if (isUrl)
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(intentData)))
            else {
                Toast.makeText(this, getString(R.string.target_camera_to_ticket), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initialiseDetectorsAndSources() {
        Toast.makeText(applicationContext, getString(R.string.ticket_scanner_started), Toast.LENGTH_SHORT)
            .show()
        barcodeDetector = BarcodeDetector.Builder(this)
            .setBarcodeFormats(Barcode.ALL_FORMATS)
            .build()
        cameraSource = CameraSource.Builder(this, barcodeDetector)
            .setRequestedPreviewSize(1920, 1080)
            .setAutoFocusEnabled(true) //you should add this feature
            .build()
        surfaceView?.holder?.addCallback(object : Callback, SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder?) {
                try {
                    if (ActivityCompat.checkSelfPermission(
                            this@TicketScannerActivity,
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        cameraSource!!.start(surfaceView?.holder)
                    } else {
                        ActivityCompat.requestPermissions(
                            this@TicketScannerActivity,
                            arrayOf(Manifest.permission.CAMERA),
                            REQUEST_CAMERA_PERMISSION
                        )
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun surfaceChanged(
                holder: SurfaceHolder?,
                format: Int,
                width: Int,
                height: Int
            ) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {
                cameraSource!!.stop()
            }
        })
        barcodeDetector?.run {
            setProcessor(object : Detector.Processor<Barcode?> {
                override fun release() {
                    Toast.makeText(
                        applicationContext,
                        "To prevent memory leaks barcode scanner has been stopped",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun receiveDetections(detections: Detector.Detections<Barcode?>) {
                    val barcodes: SparseArray<Barcode?>? = detections.getDetectedItems()
                    if (barcodes?.size() != 0) {
                        txtBarcodeValue!!.post {
                            if (barcodes?.valueAt(0)?.url != null) {
                                isUrl = true
                                btnAction!!.text = getString(R.string.login_to_conference)
                                intentData = barcodes.valueAt(0)?.displayValue.toString()
                                txtBarcodeValue!!.text = intentData
                            } else {
                                isUrl = false
                                btnAction!!.text = getString(R.string.ticket_not_recognize)
                                intentData = barcodes?.valueAt(0)?.displayValue.toString()
                                txtBarcodeValue!!.text = intentData
                            }
                        }
                    }
                }
            })
        }
    }


    override fun onPause() {
        super.onPause()
        cameraSource?.release()
    }

    override fun onResume() {
        super.onResume()
        initialiseDetectorsAndSources()
    }
}
