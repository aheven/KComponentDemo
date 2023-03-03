package heven.holt.kcomponent.user.ui.loadstate

import android.os.Bundle
import android.view.View
import heven.holt.kcomponent.base.ui.BaseFragment
import heven.holt.kcomponent.user.R
import heven.holt.kcomponent.user.ui.loadstate.delegate.ScrollingDecorViewDelegate

class LoadStateScrollingToolbarFragment : BaseFragment(R.layout.load_state_scrolling){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDecorView(ScrollingDecorViewDelegate())
    }
}