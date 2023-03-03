package heven.holt.kcomponent.user.ui.loadstate.widget

import android.animation.Animator
import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import heven.holt.kcomponent.lib.logan.dp

abstract class LoadingRenderer {
    private val ANIMATION_DURATION: Long = 1333
    private val DEFAULT_SIZE = 56.0f

    private val mRenderAnimator: ValueAnimator by lazy {
        ValueAnimator.ofFloat(0.0f, 1.0f).apply {
            repeatCount = Animation.INFINITE
            repeatMode = ValueAnimator.RESTART
            duration = mDuration
            interpolator = LinearInterpolator()
        }
    }

    private val mAnimatorUpdateListener = ValueAnimator.AnimatorUpdateListener {
        computeRender(it.animatedValue as Float)
        invalidateSelf()
    }

    /**
     * Whenever [LoadingDrawable] boundary changes mBounds will be updated.
     * More details you can see [LoadingDrawable.onBoundsChange]
     */
    protected val mBounds = Rect()

    private var mCallback: Drawable.Callback? = null

    protected var mDuration: Long = 0

    var mWidth = 0f
    var mHeight = 0f

    init {
        initParams()
        setupAnimators()
    }

    fun setCallback(callback: Drawable.Callback?) {
        this.mCallback = callback
    }

    open fun draw(canvas: Canvas) {
    }

    fun setBounds(bounds: Rect) {
        mBounds.set(bounds)
    }

    private fun initParams() {
        mWidth = DEFAULT_SIZE.dp
        mHeight = DEFAULT_SIZE.dp

        mDuration = ANIMATION_DURATION
    }

    private fun setupAnimators() {
        mRenderAnimator.addUpdateListener(mAnimatorUpdateListener)
    }

    protected abstract fun computeRender(renderProgress: Float)

    abstract fun setAlpha(alpha: Int)

    abstract fun setColorFilter(cf: ColorFilter?)

    protected abstract fun reset()

    protected open fun addRenderListener(animatorListener: Animator.AnimatorListener?) {
        mRenderAnimator.addListener(animatorListener)
    }

    open fun start() {
        reset()
        mRenderAnimator.addUpdateListener(mAnimatorUpdateListener)
        mRenderAnimator.repeatCount = ValueAnimator.INFINITE
        mRenderAnimator.duration = mDuration
        mRenderAnimator.start()
    }

    open fun stop() {
        // if I just call mRenderAnimator.end(),
        // it will always call the method onAnimationUpdate(ValueAnimator animation)
        // why ? if you know why please send email to me (dinus_developer@163.com)
        mRenderAnimator.removeUpdateListener(mAnimatorUpdateListener)
        mRenderAnimator.repeatCount = 0
        mRenderAnimator.duration = 0
        mRenderAnimator.end()
    }

    open fun isRunning(): Boolean {
        return mRenderAnimator.isRunning
    }

    private val colorDrawable = ColorDrawable(Color.BLACK)

    private fun invalidateSelf() {
        mCallback?.invalidateDrawable(colorDrawable)
    }
}