package heven.holt.kcomponent.user.ui.loadstate

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import heven.holt.kcomponent.base.ui.BaseFragment
import heven.holt.kcomponent.lib.view.loading.state.animation.FadeAnimation
import heven.holt.kcomponent.user.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadStateEmptyFragment : BaseFragment(R.layout.load_state_layout_content) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as LoadStateViewActivity).updateToolbar("Fragment(empty)")
        loadData()
    }

    private fun loadData() {
        showLoadingView()
        lifecycleScope.launch {
            delay(3000)
            showEmptyView(FadeAnimation())
        }
    }
}