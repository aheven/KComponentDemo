package heven.holt.kcomponent.user.ui.loadstate.widget

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.graphics.ColorFilter
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.drawable.Animatable

class LoadingDrawable internal constructor(private val mLoadingRender: LoadingRenderer) :
    Drawable(), Animatable {
    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        mLoadingRender.setBounds(bounds)
    }

    override fun draw(canvas: Canvas) {
        if (!bounds.isEmpty) {
            mLoadingRender.draw(canvas)
        }
    }

    override fun setAlpha(alpha: Int) {
        mLoadingRender.setAlpha(alpha)
    }

    override fun setColorFilter(cf: ColorFilter?) {
        mLoadingRender.setColorFilter(cf)
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun start() {
        mLoadingRender.start()
    }

    override fun stop() {
        mLoadingRender.stop()
    }

    override fun isRunning(): Boolean {
        return mLoadingRender.isRunning()
    }

    override fun getIntrinsicHeight(): Int {
        return mLoadingRender.mHeight.toInt()
    }

    override fun getIntrinsicWidth(): Int {
        return mLoadingRender.mWidth.toInt()
    }

    init {
        val mCallback: Callback = object : Callback {
            override fun invalidateDrawable(d: Drawable) {
                invalidateSelf()
            }

            override fun scheduleDrawable(d: Drawable, what: Runnable, `when`: Long) {
                scheduleSelf(what, `when`)
            }

            override fun unscheduleDrawable(d: Drawable, what: Runnable) {
                unscheduleSelf(what)
            }
        }
        mLoadingRender.setCallback(mCallback)
    }
}