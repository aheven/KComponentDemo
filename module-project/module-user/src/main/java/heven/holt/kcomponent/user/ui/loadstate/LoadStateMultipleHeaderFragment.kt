package heven.holt.kcomponent.user.ui.loadstate

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import heven.holt.kcomponent.base.ui.BaseFragment
import heven.holt.kcomponent.lib.view.loading.state.animation.FadeAnimation
import heven.holt.kcomponent.user.R
import heven.holt.kcomponent.user.ui.loadstate.delegate.NothingViewDelegate
import heven.holt.kcomponent.user.ui.loadstate.delegate.SearchHeaderViewDelegate
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadStateMultipleHeaderFragment : BaseFragment(R.layout.load_state_layout_content),
    SearchHeaderViewDelegate.OnSearchListener {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerView(NothingViewDelegate())
        setHeaders(
            ToolbarViewDelegate("MultipleHeader(search)"),
            SearchHeaderViewDelegate(this)
        )
        showEmptyView()
    }

    override fun onSearch(keyword: String) {
        showLoadingView()
        lifecycleScope.launch {
            delay(3000)
            showContentView(FadeAnimation())
        }
    }
}