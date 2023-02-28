package heven.holt.kcomponent.lib.view.loading.state.ktx

import android.app.Activity
import android.view.View
import androidx.annotation.StringRes
import heven.holt.kcomponent.lib.view.loading.state.LoadingStateView
import heven.holt.kcomponent.lib.view.loading.state.OnReloadListener

interface LoadingState {

    fun Activity.decorateContentView(
        listener: OnReloadListener? = null,
        decorative: Decorative? = null
    )

    fun View.decorate(listener: OnReloadListener? = null, decorative: Decorative? = null): View

    fun registerView(vararg viewDelegates: LoadingStateView.ViewDelegate)

    fun Activity.setToolbar(
        @StringRes titleId: Int,
        navBtnType: NavBtnType = NavBtnType.ICON,
        block: (ToolbarConfig.() -> Unit)? = null
    )

    fun Activity.setToolbar(
        title: String? = null,
        navBtnType: NavBtnType = NavBtnType.ICON,
        block: (ToolbarConfig.() -> Unit)? = null
    )

    fun Activity.setHeaders(vararg delegates: LoadingStateView.ViewDelegate)

    fun showLoadingView(animation: LoadingStateView.Animation? = null)

    fun showContentView(animation: LoadingStateView.Animation? = null)

    fun showErrorView(animation: LoadingStateView.Animation? = null)

    fun showEmptyView(animation: LoadingStateView.Animation? = null)

    fun showCustomView(viewType: Any, animation: LoadingStateView.Animation? = null)

    fun updateToolbar(block: ToolbarConfig.() -> Unit)

    fun <T : LoadingStateView.ViewDelegate> updateView(viewType: Any, block: T.() -> Unit)

    fun ToolbarViewDelegate(
        title: String? = null,
        navBtnType: NavBtnType = NavBtnType.ICON,
        block: (ToolbarConfig.() -> Unit)?
    ): BaseToolbarViewDelegate
}