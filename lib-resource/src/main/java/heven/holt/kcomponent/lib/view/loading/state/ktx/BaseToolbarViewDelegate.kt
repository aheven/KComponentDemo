package heven.holt.kcomponent.lib.view.loading.state.ktx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import heven.holt.kcomponent.lib.view.loading.state.LoadingStateView
import heven.holt.kcomponent.lib.view.loading.state.ViewType

abstract class BaseToolbarViewDelegate : LoadingStateView.ViewDelegate(ViewType.TITLE) {

    internal lateinit var config: ToolbarConfig

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup): View =
        onCreateToolbar(inflater,parent).apply { onBindToolbar(config) }

    abstract fun onCreateToolbar(inflater: LayoutInflater, parent: ViewGroup): View

    abstract fun onBindToolbar(config: ToolbarConfig)
}