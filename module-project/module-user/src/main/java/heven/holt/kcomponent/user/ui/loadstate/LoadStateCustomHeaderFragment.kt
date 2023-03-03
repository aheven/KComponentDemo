package heven.holt.kcomponent.user.ui.loadstate

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import heven.holt.kcomponent.base.ui.BaseFragment
import heven.holt.kcomponent.user.R
import heven.holt.kcomponent.user.ui.loadstate.delegate.CustomHeaderViewDelegate

class LoadStateCustomHeaderFragment : BaseFragment(R.layout.load_state_view_pager_single) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as LoadStateViewActivity).updateToolbar("SpecialHeader(custom)")

        setHeaders(
            CustomHeaderViewDelegate(
                this::onMessageClick,
                R.drawable.icon_load_state_baseline_photo_camera_24,
                this::onFirstBtnClick,
                R.drawable.icon_load_state_baseline_favorite_24,
                this::onSecondBtnClick
            )
        )

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = view.findViewById<ViewPager2>(R.id.view_pager)
        viewPager.adapter = TabPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab $position"
        }.attach()
    }

    private fun onMessageClick(view: View) {
        Toast.makeText(view.context, "on message click", Toast.LENGTH_SHORT).show()
    }

    private fun onFirstBtnClick(view: View) {
        Toast.makeText(view.context, "on first click", Toast.LENGTH_SHORT).show()
    }

    private fun onSecondBtnClick(view: View) {
        Toast.makeText(view.context, "on second click", Toast.LENGTH_SHORT).show()
    }

    private class TabPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment = LoadStateTimeoutFragment()
    }
}