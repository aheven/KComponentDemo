package heven.holt.kcomponent.demo

import android.util.Log
import com.xiaojinzi.component.Component
import com.xiaojinzi.component.Config
import com.xiaojinzi.component.support.ASMUtil
import heven.holt.kcomponent.base.application.BaseApplication

class App : BaseApplication() {
    override fun onCreate() {
        super.onCreate()

        Log.d(
            "demoApp",
            "moduleNames = ${ASMUtil.getModuleNames()}"
        )

        Component.init(
            application = this,
            isDebug = BuildConfig.DEBUG,
            config = Config.Builder()
                .optimizeInit(true)
                .autoRegisterModule(true)
                .build()
        )
    }
}