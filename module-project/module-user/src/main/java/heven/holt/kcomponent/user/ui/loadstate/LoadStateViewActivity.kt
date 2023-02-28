package heven.holt.kcomponent.user.ui.loadstate

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.xiaojinzi.component.anno.RouterAnno
import heven.holt.kcomponent.base.RouterConfig
import heven.holt.kcomponent.base.ui.BaseActivity
import heven.holt.kcomponent.user.R

@RouterAnno(hostAndPath = RouterConfig.USER_LOAD_STATE_VIEW)
class LoadStateViewActivity : BaseActivity(R.layout.activity_load_state_view) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbar(getString(R.string.title_load_state_view)) {
            navIcon {
                onBackPressed()
            }
        }
    }

    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.load_state_activity_error -> {
                val action =
                    LoadStateViewHomeFragmentDirections.actionLoadStateViewHomeFragmentToLoadStateErrorActActivity()
                view.findNavController().navigate(action)
            }
            R.id.load_state_fragment_empty -> {
                val action =
                    LoadStateViewHomeFragmentDirections.actionLoadStateViewHomeFragmentToLoadStateEmptyFragment()
                view.findNavController().navigate(action)
            }
            R.id.load_state_view_placeholder -> {
                val action =
                    LoadStateViewHomeFragmentDirections.actionLoadStateViewHomeFragmentToLoadStatePlaceholderFragment()
                view.findNavController().navigate(action)
            }
            R.id.load_state_viewpager_timeout -> {
                val action =
                    LoadStateViewHomeFragmentDirections.actionLoadStateViewHomeFragmentToLoadStateViewPagerFragment()
                view.findNavController().navigate(action)
            }
        }
    }

    override fun onBackPressed() {
        if (findNavController(R.id.nav_host_fragment).navigateUp()) {
            updateToolbar(getString(R.string.title_load_state_view))
        } else {
            finish()
        }
    }

    fun updateToolbar(title: String) {
        updateToolbar {
            this.title = title
        }
    }
}