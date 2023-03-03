package heven.holt.kcomponent.base.application

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import heven.holt.kcomponent.lib.view.loading.state.LoadingStateView
import heven.holt.kcomponent.lib.view.loading.state.ktx.EmptyViewDelegate
import heven.holt.kcomponent.lib.view.loading.state.ktx.ErrorViewDelegate
import heven.holt.kcomponent.lib.view.loading.state.ktx.LoadingViewDelegate
import heven.holt.kcomponent.lib.view.loading.state.ktx.ToolbarViewDelegate

open class BaseApplication : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()
        LoadingStateView.setViewDelegatePool {
            register(
                ToolbarViewDelegate(),
                LoadingViewDelegate(),
                ErrorViewDelegate(),
                EmptyViewDelegate()
            )
        }
    }

    override fun newImageLoader(): ImageLoader = ImageLoader.Builder(this).build()
}