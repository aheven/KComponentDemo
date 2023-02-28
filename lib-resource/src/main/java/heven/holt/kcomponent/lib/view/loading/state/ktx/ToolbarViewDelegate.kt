package heven.holt.kcomponent.lib.view.loading.state.ktx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hjq.bar.TitleBar

class ToolbarViewDelegate : BaseToolbarViewDelegate() {

    private lateinit var titleBar: TitleBar

    override fun onCreateToolbar(inflater: LayoutInflater, parent: ViewGroup): View {
        return TitleBar(parent.context).also { titleBar = it }
    }

    override fun onBindToolbar(config: ToolbarConfig) {
        titleBar.title = config.title

        when (config.navBtnType) {
            NavBtnType.ICON -> {
                config.navIcon?.let { titleBar.setLeftIcon(it) }
                titleBar.leftView.setOnClickListener(config.onNavClickListener)
            }
            NavBtnType.NONE -> {
                titleBar.leftIcon = null
            }
        }
    }
}