package heven.holt.kcomponent.user.ui.loadstate

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import heven.holt.kcomponent.base.ui.BaseActivity
import heven.holt.kcomponent.lib.view.loading.state.animation.FadeAnimation
import heven.holt.kcomponent.user.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadStateErrorActActivity : BaseActivity(R.layout.load_state_layout_content) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbar("Activity(error)")
        loadData()
    }

    private fun loadData() {
        showLoadingView()
        lifecycleScope.launch {
            delay(3000)
            showErrorView(FadeAnimation())
        }
    }

    override fun onReload() {
        showLoadingView()
        lifecycleScope.launch {
            delay(3000)
            showContentView(FadeAnimation())
        }
    }
}