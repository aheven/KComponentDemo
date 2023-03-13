package heven.holt.kcomponent.user.ui.net

import android.os.Bundle
import android.view.View
import com.drake.net.Get
import com.drake.net.utils.scopeNetLife
import heven.holt.kcomponent.base.ui.BaseBindingFragment
import heven.holt.kcomponent.user.api.Api
import heven.holt.kcomponent.user.databinding.FragmentNetInterceptorBinding

class NetInterceptorFragment : BaseBindingFragment<FragmentNetInterceptorBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scopeNetLife {
            binding.tvFragment.text = Get<String>(Api.TEST) {
                // 拦截器只支持全局, 无法单例, 请查看[com.drake.net.sample.interceptor.NetInterceptor]
            }.await()
        }
    }
}