package heven.holt.kcomponent.base.application

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.drake.net.NetConfig
import com.drake.net.interceptor.LogRecordInterceptor
import com.drake.net.okhttp.setDebug
import com.drake.net.okhttp.setDialogFactory
import com.xiaojinzi.component.Component
import com.xiaojinzi.component.Config
import heven.holt.kcomponent.base.BuildConfig
import heven.holt.kcomponent.base.api.Api
import heven.holt.kcomponent.base.ui.dialog.BubbleDialog
import heven.holt.kcomponent.lib.view.loading.state.LoadingStateView
import heven.holt.kcomponent.lib.view.loading.state.ktx.EmptyViewDelegate
import heven.holt.kcomponent.lib.view.loading.state.ktx.ErrorViewDelegate
import heven.holt.kcomponent.lib.view.loading.state.ktx.LoadingViewDelegate
import heven.holt.kcomponent.lib.view.loading.state.ktx.ToolbarViewDelegate
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

open class BaseApplication : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()
        Component.init(
            application = this,
            isDebug = BuildConfig.DEBUG,
            config = Config.Builder()
                .optimizeInit(true)
                .autoRegisterModule(true)
                .build()
        )

        LoadingStateView.setViewDelegatePool {
            register(
                ToolbarViewDelegate(),
                LoadingViewDelegate(),
                ErrorViewDelegate(),
                EmptyViewDelegate()
            )
        }

        NetConfig.initialize(Api.HOST, this) {
            //超时设置
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)

            // 本框架支持Http缓存协议和强制缓存模式
            cache(Cache(cacheDir, 1024 * 1024 * 128)) // 缓存设置, 当超过maxSize最大值会根据最近最少使用算法清除缓存来限制缓存大小

            // LogCat是否输出异常日志, 异常日志可以快速定位网络请求错误
            setDebug(BuildConfig.DEBUG)

            // AndroidStudio OkHttp Profiler 插件输出网络日志
            addInterceptor(LogRecordInterceptor(BuildConfig.DEBUG))

            appendInterceptors()

            setDialogFactory {
                BubbleDialog(it)
            }
        }
    }

    override fun newImageLoader(): ImageLoader = ImageLoader.Builder(this).build()

    open fun OkHttpClient.Builder.appendInterceptors() {

    }
}