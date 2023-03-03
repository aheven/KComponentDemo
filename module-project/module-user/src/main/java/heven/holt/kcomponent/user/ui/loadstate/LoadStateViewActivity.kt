package heven.holt.kcomponent.user.ui.loadstate

import android.os.Bundle
import android.view.View
import androidx.navigation.NavDirections
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
        val action: NavDirections? =
            when (view.id) {
                R.id.load_state_activity_error -> {
                    LoadStateViewHomeFragmentDirections.actionLoadStateViewHomeFragmentToLoadStateErrorActActivity()
                }
                R.id.load_state_fragment_empty -> {
                    LoadStateViewHomeFragmentDirections.actionLoadStateViewHomeFragmentToLoadStateEmptyFragment()
                }
                R.id.load_state_view_placeholder -> {
                    LoadStateViewHomeFragmentDirections.actionLoadStateViewHomeFragmentToLoadStatePlaceholderFragment()
                }
                R.id.load_state_viewpager_timeout -> {
                    LoadStateViewHomeFragmentDirections.actionLoadStateViewHomeFragmentToLoadStateViewPagerFragment()
                }
                R.id.load_state_recyclerview_loading -> {
                    LoadStateViewHomeFragmentDirections.actionLoadStateViewHomeFragmentToLoadStateRecyclerViewFragment()
                }
                R.id.load_state_custom_header -> {
                    LoadStateViewHomeFragmentDirections.actionLoadStateViewHomeFragmentToLoadStateCustomHeaderFragment()
                }
                R.id.load_state_search_header -> {
                    LoadStateViewHomeFragmentDirections.actionLoadStateViewHomeFragmentToLoadStateMultipleHeaderFragment()
                }
                R.id.load_state_scrolling -> {
                    LoadStateViewHomeFragmentDirections.actionLoadStateViewHomeFragmentToLoadStateScrollingToolbarFragment()
                }
                R.id.load_state_bottom_editor -> {
                    LoadStateViewHomeFragmentDirections.actionLoadStateViewHomeFragmentToLoadStateBottomEditorFragment()
                }
                else -> null
            }
        action?.let { view.findNavController().navigate(it) }
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