package heven.holt.kcomponent.lib.view.loading.state.ktx

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import androidx.annotation.DrawableRes

enum class NavBtnType {
    ICON, NONE
}

class ToolbarConfig(
    var title: String? = null,
    var navBtnType: NavBtnType = NavBtnType.ICON
) {
    @DrawableRes
    var navIcon: Int? = null
        private set
    var onNavClickListener = View.OnClickListener {
        var context: Context? = it.context
        while (context is ContextWrapper) {
            if (context is Activity) {
                context.finish()
                return@OnClickListener
            }
            context = context.baseContext
        }
    }
        private set

    fun navIcon(@DrawableRes resId: Int? = navIcon, listener: View.OnClickListener) {
        this.navIcon = resId
        this.onNavClickListener = listener
    }
}