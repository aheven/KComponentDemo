package heven.holt.kcomponent.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import heven.holt.kcomponent.lib.view.loading.state.OnReloadListener
import heven.holt.kcomponent.lib.view.loading.state.ktx.Decorative
import heven.holt.kcomponent.lib.view.loading.state.ktx.LoadingState
import heven.holt.kcomponent.lib.view.loading.state.ktx.LoadingStateDelegate

abstract class BaseActivity(private val layoutRes: Int) : AppCompatActivity(),
    LoadingState by LoadingStateDelegate(), OnReloadListener, Decorative {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        decorateContentView(this, this)
    }
}