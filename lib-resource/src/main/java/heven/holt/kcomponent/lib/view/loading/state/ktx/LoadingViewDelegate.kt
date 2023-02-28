package heven.holt.kcomponent.lib.view.loading.state.ktx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import heven.holt.kcomponent.lib.R
import heven.holt.kcomponent.lib.view.loading.state.LoadingStateView
import heven.holt.kcomponent.lib.view.loading.state.ViewType

class LoadingViewDelegate : LoadingStateView.ViewDelegate(ViewType.LOADING) {
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup): View =
        inflater.inflate(R.layout.load_state_loading, parent, false)
}