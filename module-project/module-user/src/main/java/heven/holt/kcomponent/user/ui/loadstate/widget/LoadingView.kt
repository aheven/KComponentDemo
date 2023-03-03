package heven.holt.kcomponent.user.ui.loadstate.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import heven.holt.kcomponent.user.R
import heven.holt.kcomponent.user.ui.loadstate.widget.LoadingRendererFactory.createLoadingRenderer

class LoadingView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    AppCompatImageView(context, attrs) {
    private var mLoadingDrawable: LoadingDrawable? = null

    init {
        initAttrs(context, attrs)
    }

    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        try {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.LoadingView)
            val loadingRendererId = ta.getInt(R.styleable.LoadingView_loading_renderer, 0)
            val loadingRenderer = createLoadingRenderer(context, loadingRendererId)
            setLoadingRenderer(loadingRenderer)
            ta.recycle()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setLoadingRenderer(loadingRenderer: LoadingRenderer?) {
        mLoadingDrawable = LoadingDrawable(loadingRenderer!!)
        setImageDrawable(mLoadingDrawable)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startAnimation()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopAnimation()
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        val visible = visibility == VISIBLE && getVisibility() == VISIBLE
        if (visible) {
            startAnimation()
        } else {
            stopAnimation()
        }
    }

    private fun startAnimation() {
        if (mLoadingDrawable != null) {
            mLoadingDrawable!!.start()
        }
    }

    private fun stopAnimation() {
        if (mLoadingDrawable != null) {
            mLoadingDrawable!!.stop()
        }
    }
}