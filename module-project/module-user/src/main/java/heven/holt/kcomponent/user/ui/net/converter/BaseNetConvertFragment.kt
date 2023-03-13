package heven.holt.kcomponent.user.ui.net.converter

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.viewbinding.ViewBinding
import heven.holt.kcomponent.base.ui.BaseBindingFragment
import heven.holt.kcomponent.user.R

open class BaseNetConvertFragment<VB : ViewBinding> : BaseBindingFragment<VB>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_net_converter, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item.onNavDestinationSelected(findNavController())
        return super.onOptionsItemSelected(item)
    }
}