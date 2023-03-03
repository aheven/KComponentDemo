package heven.holt.kcomponent.user.ui.loadstate.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import heven.holt.kcomponent.lib.view.loading.state.LoadingStateView
import heven.holt.kcomponent.lib.view.loading.state.ViewType

class NothingViewDelegate : LoadingStateView.ViewDelegate(ViewType.EMPTY) {
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup): View =
        View(parent.context)
}