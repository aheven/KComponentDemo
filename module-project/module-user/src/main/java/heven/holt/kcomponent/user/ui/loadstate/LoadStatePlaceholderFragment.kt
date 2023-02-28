package heven.holt.kcomponent.user.ui.loadstate

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import heven.holt.kcomponent.base.ui.BaseFragment
import heven.holt.kcomponent.lib.view.loading.state.animation.FadeAnimation
import heven.holt.kcomponent.user.R
import heven.holt.kcomponent.user.ui.loadstate.delegate.PlaceholderViewDelegate
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadStatePlaceholderFragment : BaseFragment(R.layout.view_load_state) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as LoadStateViewActivity).updateToolbar("View(placeholder)")
        registerView(PlaceholderViewDelegate())
        loadData()
    }

    private fun loadData() {
        showLoadingView()
        lifecycleScope.launch {
            delay(3000)
            showContentView(FadeAnimation())
        }
    }
}