package heven.holt.kcomponent.user.ui.net

import android.os.Bundle
import android.view.View
import com.drake.net.Get
import com.drake.net.utils.scopeNetLife
import heven.holt.kcomponent.base.ui.BaseBindingFragment
import heven.holt.kcomponent.user.databinding.FragmentNetErrorHandlerBinding

class NetErrorHandlerFragment : BaseBindingFragment<FragmentNetErrorHandlerBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scopeNetLife {
            // 该请求是错误的路径会在控制台打印出错误信息
            Get<String>("/error").await()
        }.catch {
            // 重写该函数后，错误不会流到[NetConfig.onError]中的全局错误处理，在App.kt中可以自定义该全局处理，同时包含onStateError
            binding.tvFragment.text = it.message
        }
    }
}