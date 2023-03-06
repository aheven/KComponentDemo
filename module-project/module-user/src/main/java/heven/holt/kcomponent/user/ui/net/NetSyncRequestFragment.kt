package heven.holt.kcomponent.user.ui.net

import android.os.Bundle
import android.view.View
import com.drake.net.Net
import heven.holt.kcomponent.base.ui.BaseBindingFragment
import heven.holt.kcomponent.user.api.Api
import heven.holt.kcomponent.user.databinding.FragmentNetSyncRequestBinding
import kotlin.concurrent.thread

class NetSyncRequestFragment : BaseBindingFragment<FragmentNetSyncRequestBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        thread {
            val result = try {
                Net.post(Api.TEST).execute()
            } catch (e: Exception) {
                "请求错误 = ${e.message}"
            }

            binding.content.post {
                binding.content.text = result
            }
        }
    }
}