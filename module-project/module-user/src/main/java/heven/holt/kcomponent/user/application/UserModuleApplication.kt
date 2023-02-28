package heven.holt.kcomponent.user.application

import android.app.Application
import android.util.Log
import com.xiaojinzi.component.anno.ModuleAppAnno
import com.xiaojinzi.component.application.IComponentApplication
import com.xiaojinzi.component.application.IModuleNotifyChanged

@ModuleAppAnno
class UserModuleApplication : IComponentApplication,IModuleNotifyChanged {
    override fun onCreate(app: Application) {
        Log.e("TAG", "onCreate: ", )
    }

    override fun onDestroy() {
        Log.e("TAG", "onDestroy: ", )
    }

    override fun onModuleChanged(app: Application) {
        Log.e("TAG", "onModuleChanged: ", )
    }
}