package heven.holt.kcomponent.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import heven.holt.kcomponent.lib.view.loading.state.OnReloadListener
import heven.holt.kcomponent.lib.view.loading.state.ktx.Decorative
import heven.holt.kcomponent.lib.view.loading.state.ktx.LoadingState
import heven.holt.kcomponent.lib.view.loading.state.ktx.LoadingStateDelegate
import heven.holt.kcomponent.lib.viewbinding.base.FragmentBinding
import heven.holt.kcomponent.lib.viewbinding.base.FragmentBindingDelegate

abstract class BaseBindingFragment<VB : ViewBinding> : Fragment(),
    FragmentBinding<VB> by FragmentBindingDelegate(), LoadingState by LoadingStateDelegate(),
    OnReloadListener, Decorative {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createViewWithBinding(layoutInflater, container)
        return binding.root.decorate(this, this)
    }
}