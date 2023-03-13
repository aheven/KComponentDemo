package heven.holt.kcomponent.demo

import android.util.Log
import com.drake.net.okhttp.setRequestInterceptor
import com.xiaojinzi.component.support.ASMUtil
import heven.holt.kcomponent.base.application.BaseApplication
import heven.holt.kcomponent.user.ui.net.interceptor.GlobalHeaderInterceptor
import okhttp3.OkHttpClient

class App : BaseApplication() {
    override fun onCreate() {
        super.onCreate()

        Log.d(
            "demoApp",
            "moduleNames = ${ASMUtil.getModuleNames()}"
        )
    }


    override fun OkHttpClient.Builder.appendInterceptors() {
        setRequestInterceptor(GlobalHeaderInterceptor())
    }
}