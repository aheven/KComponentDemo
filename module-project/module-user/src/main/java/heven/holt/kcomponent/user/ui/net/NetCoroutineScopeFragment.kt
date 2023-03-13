package heven.holt.kcomponent.user.ui.net

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import com.drake.net.utils.scope
import com.drake.net.utils.scopeLife
import com.drake.net.utils.scopeNet
import com.drake.net.utils.scopeNetLife
import heven.holt.kcomponent.base.ui.BaseBindingFragment
import heven.holt.kcomponent.user.databinding.FragmentNetCoroutineScopeBinding
import kotlinx.coroutines.delay

class NetCoroutineScopeFragment : BaseBindingFragment<FragmentNetCoroutineScopeBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 其作用域在应用进程销毁时才销毁
        scope {
            while (true) {
                delay(5000)
                Log.e("scope", "onViewCreated: scope")
            }
        }

        // 其作用域在Actvity或Fragment销毁（OnDestroy）时被动取消
        scopeLife {
            while (true) {
                delay(3000)
                Log.e("scope", "onViewCreated: scopeLife")
            }
        }

        // 自定义取消跟随的生命周期，失去焦点时立即取消作用域
        scopeLife(Lifecycle.Event.ON_PAUSE) {
            while (true) {
                delay(3000)
                Log.e("scope", "onViewCreated: scopeLife onPause")
            }
        }

        // 此作用域会捕捉发生的异常，如果是网络异常会进入网络异常的全局处理函数，例如自动弹出吐司[NetConfig.onError]
        scopeNet {

        }

        // 自动网络处理 + 生命周期管理
        scopeNetLife {

        }
    }
}