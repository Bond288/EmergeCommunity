package com.glootie.networking.base.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData

class ConstraintStartupLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private companion object {

        const val LEFT_TOUCH_SCREEN = 0.33
        const val RIGHT_TOUCH_SCREEN = 0.66
    }

    val moveEvent = MutableLiveData<MoveEvent>()

    private var downX = 0f

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return super.onTouchEvent(event)
        return when {
            MotionEvent.ACTION_DOWN == event.action -> {
                downX = event.rawX
                true
            }

            isLeftSwipe(event) -> {
                moveEvent.value = MoveEvent.SWIPE_LEFT
                return true
            }

            isRightSwipe(event) -> {
                moveEvent.value = MoveEvent.SWIPE_RIGHT
                return true
            }
            else -> super.onTouchEvent(event)
        }
    }

    private fun isLeftSwipe(event: MotionEvent): Boolean =
        MotionEvent.ACTION_UP == event.action
                && downX - event.rawX > width * LEFT_TOUCH_SCREEN
                && event.rawX <= width * LEFT_TOUCH_SCREEN

    private fun isRightSwipe(event: MotionEvent): Boolean =
        MotionEvent.ACTION_UP == event.action
                && event.rawX - downX > width * LEFT_TOUCH_SCREEN
                && event.rawX >= width * RIGHT_TOUCH_SCREEN

    enum class MoveEvent {
        SWIPE_LEFT,
        SWIPE_RIGHT
    }
}