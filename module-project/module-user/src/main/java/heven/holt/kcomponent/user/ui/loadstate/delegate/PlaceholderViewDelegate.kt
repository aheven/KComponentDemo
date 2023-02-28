package heven.holt.kcomponent.user.ui.loadstate.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import heven.holt.kcomponent.lib.view.loading.state.LoadingStateView
import heven.holt.kcomponent.lib.view.loading.state.ViewType
import heven.holt.kcomponent.user.R

class PlaceholderViewDelegate : LoadingStateView.ViewDelegate(ViewType.LOADING) {
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup): View =
        inflater.inflate(R.layout.load_state_placeholder, parent, false)
}