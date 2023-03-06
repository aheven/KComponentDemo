package heven.holt.kcomponent.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import heven.holt.kcomponent.lib.view.loading.state.OnReloadListener
import heven.holt.kcomponent.lib.view.loading.state.ktx.Decorative
import heven.holt.kcomponent.lib.view.loading.state.ktx.LoadingState
import heven.holt.kcomponent.lib.view.loading.state.ktx.LoadingStateDelegate
import heven.holt.kcomponent.lib.viewbinding.base.ActivityBinding
import heven.holt.kcomponent.lib.viewbinding.base.ActivityBindingDelegate

abstract class BaseBindingActivity<VB : ViewBinding> : AppCompatActivity(),
    ActivityBinding<VB> by ActivityBindingDelegate(), LoadingState by LoadingStateDelegate(),
    OnReloadListener, Decorative {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentViewWithBinding()
        binding.root.decorate(this, this)
    }
}