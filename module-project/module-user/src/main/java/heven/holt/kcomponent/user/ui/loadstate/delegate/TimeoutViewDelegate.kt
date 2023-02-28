package heven.holt.kcomponent.user.ui.loadstate.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import heven.holt.kcomponent.lib.view.loading.state.LoadingStateView
import heven.holt.kcomponent.user.R

class TimeoutViewDelegate : LoadingStateView.ViewDelegate(TimeoutViewDelegate::class) {
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup): View {
        val view = inflater.inflate(R.layout.load_state_timeout, parent, false)
        view.setOnClickListener {
            onReloadListener?.onReload()
        }
        return view
    }
}