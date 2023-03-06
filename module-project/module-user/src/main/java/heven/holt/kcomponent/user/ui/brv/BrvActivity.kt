package heven.holt.kcomponent.user.ui.brv

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.xiaojinzi.component.anno.RouterAnno
import heven.holt.kcomponent.base.RouterConfig
import heven.holt.kcomponent.base.ui.BaseBindingActivity
import heven.holt.kcomponent.user.R
import heven.holt.kcomponent.user.databinding.ActivityBrvBinding
import heven.holt.kcomponent.user.databinding.BrvLayoutDrawerNavHeaderBinding

@RouterAnno(hostAndPath = RouterConfig.USER_BRV_ADAPTER)
class BrvActivity : BaseBindingActivity<ActivityBrvBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment)
        binding.toolbar.setupWithNavController(
            navController,
            AppBarConfiguration(binding.navView.menu, binding.drawer)
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.subtitle =
                (destination as FragmentNavigator.Destination).className.substringAfterLast('.')
        }

        binding.navView.setupWithNavController(navController)

        BrvLayoutDrawerNavHeaderBinding.bind(binding.navView.getHeaderView(0))
            .executePendingBindings()
    }

    override fun onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawers()
        } else super.onBackPressed()
    }
}