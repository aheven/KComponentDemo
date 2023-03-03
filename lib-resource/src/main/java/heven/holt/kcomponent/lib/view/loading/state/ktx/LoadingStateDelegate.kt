package heven.holt.kcomponent.lib.view.loading.state.ktx

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import heven.holt.kcomponent.lib.view.loading.state.LoadingStateView
import heven.holt.kcomponent.lib.view.loading.state.OnReloadListener
import heven.holt.kcomponent.lib.view.loading.state.ViewType

class LoadingStateDelegate : LoadingState {
    private var loadingStateView: LoadingStateView? = null

    override fun Activity.decorateContentView(
        listener: OnReloadListener?,
        decorative: Decorative?
    ) {
        findViewById<ViewGroup>(android.R.id.content).getChildAt(0).decorate(listener, decorative)
    }

    override fun View.decorate(listener: OnReloadListener?, decorative: Decorative?): View =
        when {
            decorative?.isDecorated == false -> this
            decorative?.contentView == null ->
                LoadingStateView(this, listener).also { loadingStateView = it }.decorView
            else -> {
                loadingStateView = LoadingStateView(decorative.contentView!!, listener)
                this
            }
        }

    override fun registerView(vararg viewDelegates: LoadingStateView.ViewDelegate) {
        loadingStateView?.register(*viewDelegates)
    }

    override fun Activity.setToolbar(
        titleId: Int,
        navBtnType: NavBtnType,
        block: (ToolbarConfig.() -> Unit)?
    ) {
        setToolbar(getString(titleId), navBtnType, block)
    }

    override fun Activity.setToolbar(
        title: String?,
        navBtnType: NavBtnType,
        block: (ToolbarConfig.() -> Unit)?
    ) {
        loadingStateView?.setHeaders(ToolbarViewDelegate(title, navBtnType, block))
    }

    override fun Fragment.setToolbar(
        titleId: Int,
        navBtnType: NavBtnType,
        block: (ToolbarConfig.() -> Unit)?
    ) {
        setToolbar(getString(titleId), navBtnType, block)
    }

    override fun Fragment.setToolbar(
        title: String?,
        navBtnType: NavBtnType,
        block: (ToolbarConfig.() -> Unit)?
    ) {
        loadingStateView?.addChildHeaders(ToolbarViewDelegate(title, navBtnType, block))
    }

    override fun Activity.setHeaders(vararg delegates: LoadingStateView.ViewDelegate) {
        loadingStateView?.setHeaders(*delegates)
    }

    override fun Fragment.setHeaders(vararg delegates: LoadingStateView.ViewDelegate) {
        loadingStateView?.addChildHeaders(*delegates)
    }

    override fun Activity.setDecorView(delegate: LoadingStateView.DecorViewDelegate) {
        loadingStateView?.setDecorView(delegate)
    }

    override fun Fragment.setDecorView(delegate: LoadingStateView.DecorViewDelegate) {
        loadingStateView?.addChildDecorView(delegate)
    }

    override fun showLoadingView(animation: LoadingStateView.Animation?) {
        loadingStateView?.showLoadingView(animation)
    }

    override fun showContentView(animation: LoadingStateView.Animation?) {
        loadingStateView?.showContentView(animation)
    }

    override fun showErrorView(animation: LoadingStateView.Animation?) {
        loadingStateView?.showErrorView(animation)
    }

    override fun showEmptyView(animation: LoadingStateView.Animation?) {
        loadingStateView?.showEmptyView(animation)
    }

    override fun showCustomView(viewType: Any, animation: LoadingStateView.Animation?) {
        loadingStateView?.showView(viewType, animation)
    }

    override fun updateToolbar(block: ToolbarConfig.() -> Unit) {
        updateView<BaseToolbarViewDelegate>(ViewType.TITLE) {
            onBindToolbar(config.apply(block))
        }
    }

    override fun <T : LoadingStateView.ViewDelegate> updateView(
        viewType: Any,
        block: T.() -> Unit
    ) {
        loadingStateView?.updateViewDelegate(viewType, block)
    }

    override fun ToolbarViewDelegate(
        title: String?,
        navBtnType: NavBtnType,
        block: (ToolbarConfig.() -> Unit)?
    ): BaseToolbarViewDelegate =
        requireNotNull(loadingStateView?.getViewDelegate<BaseToolbarViewDelegate>(ViewType.TITLE)) {
            "ToolbarViewDelegate must be registered before."
        }.apply {
            config = ToolbarConfig(title, navBtnType).apply { block?.invoke(this) }
        }
}