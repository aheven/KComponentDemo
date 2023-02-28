package heven.holt.kcomponent.lib.view.loading.state

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout

class LoadingStateView @JvmOverloads constructor(
    private val contentView: View,
    var onReloadListener: OnReloadListener? = null
) {
    lateinit var decorView: View private set
    lateinit var currentViewType: Any private set
    private lateinit var contentParent: ViewGroup
    private val parent: ViewGroup?
    private var currentView: View? = null
    private var viewDelegates: HashMap<Any, ViewDelegate> = hashMapOf()
    private val viewCaches: HashMap<Any, View> = hashMapOf()

    @JvmOverloads
    constructor(activity: Activity, listener: OnReloadListener? = null) :
            this(activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0), listener)

    init {
        poolInitializer?.apply { PoolInitializer(this@LoadingStateView).invoke() }
        parent = contentView.parent as ViewGroup?
        register(ContentViewDelegate())
        setDecorView(LinearDecorViewDelegate(emptyList()))
    }

    fun setHeaders(vararg delegates: ViewDelegate) =
        setDecorView(LinearDecorViewDelegate(delegates))

    fun setDecorView(decorViewDelegate: DecorViewDelegate) {
        currentView = null
        if (parent != null) {
            val index = parent.indexOfChild(contentView)
            if (index >= 0) {
                parent.removeView(contentView)
            } else {
                parent.removeView(decorView)
                (contentView.parent as ViewGroup).removeView(contentView)
            }
            decorView = decorViewDelegate.createDecorView()
            parent.addView(decorView, index)
        } else {
            decorView = decorViewDelegate.createDecorView()
        }
        contentParent = decorViewDelegate.getContentParent(decorView)
        showView(ViewType.CONTENT)
    }

    private fun DecorViewDelegate.createDecorView() = onCreateDecorView(
        contentView.context,
        LayoutInflater.from(contentView.context)
    ).also { decorView ->
        contentView.layoutParams?.let {
            decorView.layoutParams =
                if (it is ConstraintLayout.LayoutParams) ConstraintLayout.LayoutParams(it) else it
        }
    }

    @JvmOverloads
    fun showView(viewType: Any, animation: Animation? = null) {
        val currentView = currentView
        if (currentView == null) {
            addView(viewType)
        } else {
            if (viewCaches[viewType] == null) addView(viewType)
            if (viewType != currentView) {
                val view = getView(viewType)
                view.visibility = View.VISIBLE
                if (animation != null) {
                    animation.onStartHideAnimation(currentView, currentViewType)
                    animation.onStartShowAnimation(
                        view,
                        getViewDelegate<ViewDelegate>(viewType)!!.viewType
                    )
                } else {
                    currentView.visibility = View.GONE
                }
                this.currentView = view
            }
        }
        currentViewType = viewType
    }

    fun register(vararg delegates: ViewDelegate) =
        delegates.forEach { viewDelegates[it.viewType] = it }

    @JvmOverloads
    fun showLoadingView(animation: Animation? = null) = showView(ViewType.LOADING, animation)

    @JvmOverloads
    fun showContentView(animation: Animation? = null) = showView(ViewType.CONTENT, animation)

    @JvmOverloads
    fun showErrorView(animation: Animation? = null) = showView(ViewType.ERROR, animation)

    @JvmOverloads
    fun showEmptyView(animation: Animation? = null) = showView(ViewType.EMPTY, animation)

    fun <T : ViewDelegate> updateViewDelegate(viewType: Any, callback: Callback<T>) =
        callback.apply { getViewDelegate<T>(viewType)?.invoke() }

    @Suppress("UNCHECKED_CAST")
    fun <T : ViewDelegate> getViewDelegate(viewType: Any): T? = viewDelegates[viewType] as? T

    private fun addView(viewType: Any) {
        val view = getView(viewType)
        (view.parent as? ViewGroup)?.removeView(view)
        if (parent is ConstraintLayout && viewType == ViewType.CONTENT) {
            val params = view.layoutParams
            if (view.measuredWidth == 0) params.width = MATCH_PARENT
            if (view.measuredHeight == 0) params.height = MATCH_PARENT
            view.layoutParams = params
        }
        contentParent.addView(view)
        currentView = view
    }

    private fun getView(viewType: Any): View {
        if (viewCaches[viewType] == null) {
            val viewDelegate =
                requireNotNull(getViewDelegate(viewType)) { "Please register view delegate for $viewType type." }
            val view =
                viewDelegate.onCreateView(LayoutInflater.from(contentParent.context), contentParent)
            viewDelegate.onReloadListener = onReloadListener
            viewCaches[viewType] = view
        }
        return viewCaches[viewType]!!
    }

    abstract class ViewDelegate(val viewType: Any) {
        var onReloadListener: OnReloadListener? = null
            internal set

        abstract fun onCreateView(inflater: LayoutInflater, parent: ViewGroup): View
    }

    private inner class ContentViewDelegate : ViewDelegate(ViewType.CONTENT) {
        override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup): View = contentView
    }

    abstract class DecorViewDelegate {
        abstract fun onCreateDecorView(context: Context, inflater: LayoutInflater): View
        abstract fun getContentParent(decorView: View): ViewGroup
    }

    private inner class LinearDecorViewDelegate(private val views: List<View>) :
        DecorViewDelegate() {

        private lateinit var contentParent: FrameLayout

        constructor(delegates: Array<out ViewDelegate>) : this(delegates.map {
            register(it)
            getView(it.viewType)
        })

        override fun onCreateDecorView(context: Context, inflater: LayoutInflater): View =
            LinearLayout(inflater.context).apply {
                orientation = LinearLayout.VERTICAL
                contentParent = FrameLayout(context)
                contentParent.layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                views.forEach { addView(it) }
                addView(contentParent)
            }

        override fun getContentParent(decorView: View): ViewGroup = contentParent
    }

    class PoolInitializer internal constructor(private val stateView: LoadingStateView) {
        fun register(vararg delegates: ViewDelegate) = stateView.register(*delegates)
    }

    fun interface Callback<in T> {
        fun T.invoke()
    }

    interface Animation {
        fun onStartShowAnimation(view: View, viewType: Any)
        fun onStartHideAnimation(view: View, viewType: Any)
    }

    companion object {
        private var poolInitializer: Callback<PoolInitializer>? = null

        @JvmStatic
        fun setViewDelegatePool(poolInitializer: Callback<PoolInitializer>) {
            this.poolInitializer = poolInitializer
        }
    }
}

interface OnReloadListener {
    fun onReload() = Unit
}

enum class ViewType {
    TITLE, LOADING, CONTENT, ERROR, EMPTY
}