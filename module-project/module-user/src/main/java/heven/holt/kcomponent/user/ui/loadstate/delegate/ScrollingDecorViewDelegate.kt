package heven.holt.kcomponent.user.ui.loadstate.delegate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import heven.holt.kcomponent.lib.view.loading.state.LoadingStateView
import heven.holt.kcomponent.user.R

class ScrollingDecorViewDelegate : LoadingStateView.DecorViewDelegate() {
    override fun onCreateDecorView(context: Context, inflater: LayoutInflater): View {
        val view = inflater.inflate(R.layout.load_state_scrolling_toolbar, null)
        return view
    }

    override fun getContentParent(decorView: View): ViewGroup {
        return decorView.findViewById(R.id.content_parent)
    }
}