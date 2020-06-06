package com.glootie.networking.qr

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.SparseArray
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.glootie.networking.R
import com.glootie.networking.base.BaseFragment
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.fragment_ticket_scanner.*
import java.io.IOException
import javax.security.auth.callback.Callback


class TicketScannerFragment : BaseFragment() {

    private var barcodeDetector: BarcodeDetector? = null
    private var cameraSource: CameraSource? = null
    private val REQUEST_CAMERA_PERMISSION = 201
    var intentData = ""
    var isUrl = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_ticket_scanner, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        button_action.setOnClickListener {
            if (intentData.isNotEmpty()) {
                if (isUrl)
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(intentData)))
                else {
                    Toast.makeText(activity, getString(R.string.target_camera_to_ticket), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun initialiseDetectorsAndSources() {
        Toast.makeText(activity, getString(R.string.ticket_scanner_started), Toast.LENGTH_SHORT)
            .show()
        barcodeDetector = BarcodeDetector.Builder(activity)
            .setBarcodeFormats(Barcode.ALL_FORMATS)
            .build()
        cameraSource = CameraSource.Builder(activity, barcodeDetector)
            .setRequestedPreviewSize(1920, 1080)
            .setAutoFocusEnabled(true) //you should add this feature
            .build()
        surface_view?.holder?.addCallback(object : Callback, SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder?) {
                try {
                    if (ActivityCompat.checkSelfPermission(
                            activity!!.applicationContext,
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        cameraSource!!.start(surface_view?.holder)
                    } else {
                        ActivityCompat.requestPermissions(
                            activity!!,
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
                        activity,
                        "To prevent memory leaks barcode scanner has been stopped",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun receiveDetections(detections: Detector.Detections<Barcode?>) {
                    val barcodes: SparseArray<Barcode?>? = detections.getDetectedItems()
                    if (barcodes?.size() != 0) {
                        barcode_value!!.post {
                            if (barcodes?.valueAt(0)?.url != null) {
                                isUrl = true
                                button_action!!.text = getString(R.string.login_to_conference)
                                intentData = barcodes.valueAt(0)?.displayValue.toString()
                                barcode_value!!.text = intentData
                            } else {
                                isUrl = false
                                button_action!!.text = getString(R.string.ticket_not_recognize)
                                intentData = barcodes?.valueAt(0)?.displayValue.toString()
                                barcode_value!!.text = intentData
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
