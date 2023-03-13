package heven.holt.kcomponent.user.ui.net

import android.os.Bundle
import android.view.View
import com.drake.net.Get
import com.drake.net.utils.scopeNetLife
import heven.holt.kcomponent.base.ui.BaseBindingFragment
import heven.holt.kcomponent.user.databinding.FragmentNetExceptionTraceBinding

class NetExceptionTraceFragment : BaseBindingFragment<FragmentNetExceptionTraceBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scopeNetLife {
            // 这是一个错误的地址, 请查看LogCat的错误信息, 在[initNet]函数中的[onError]回调中你也可以进行自定义错误信息打印
            binding.tvFragment.text =
                Get<String>("https://githuberror.com/liangjingkanji/Net/").await()
        }
    }
}