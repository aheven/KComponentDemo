package heven.holt.kcomponent.application

import android.app.Application
import android.util.Log
import com.xiaojinzi.component.anno.ModuleAppAnno
import com.xiaojinzi.component.application.IComponentApplication
import heven.holt.kcomponent.base.application.BaseApplication

@ModuleAppAnno
class AppModuleApplication : BaseApplication(), IComponentApplication {
    override fun onCreate(app: Application) {
        Log.e("TAG", "onCreate: AppModuleApplication")
    }

    override fun onDestroy() {
        Log.e("TAG", "onDestroy: AppModuleApplication")
    }
}