package heven.holt.kcomponent.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import heven.holt.kcomponent.lib.view.loading.state.OnReloadListener
import heven.holt.kcomponent.lib.view.loading.state.ktx.Decorative
import heven.holt.kcomponent.lib.view.loading.state.ktx.LoadingState
import heven.holt.kcomponent.lib.view.loading.state.ktx.LoadingStateDelegate

abstract class BaseFragment(private val layoutRes: Int) : Fragment(),
    LoadingState by LoadingStateDelegate(), OnReloadListener, Decorative {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(layoutRes, container, false)
        return root.decorate(this, this)
    }
}