package heven.holt.kcomponent.user.application

import android.app.Application
import android.util.Log
import com.xiaojinzi.component.anno.ModuleAppAnno
import com.xiaojinzi.component.application.IComponentApplication
import com.xiaojinzi.component.application.IModuleNotifyChanged
import heven.holt.kcomponent.user.mock.MockDispatcher

@ModuleAppAnno
class UserModuleApplication : IComponentApplication, IModuleNotifyChanged {
    override fun onCreate(app: Application) {
        MockDispatcher.initialize()
        Log.d("TAG", "onCreate: UserModuleApplication")
    }

    override fun onDestroy() {
        Log.d("TAG", "onDestroy: UserModuleApplication")
    }

    override fun onModuleChanged(app: Application) {
        Log.d("TAG", "onModuleChanged: UserModuleApplication")
    }
}