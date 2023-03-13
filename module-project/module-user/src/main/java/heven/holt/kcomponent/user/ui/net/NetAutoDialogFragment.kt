package heven.holt.kcomponent.user.ui.net

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.drake.net.Post
import com.drake.net.convert.JSONConvert
import com.drake.net.utils.scopeDialog
import heven.holt.kcomponent.base.ui.BaseBindingFragment
import heven.holt.kcomponent.user.api.Api
import heven.holt.kcomponent.user.databinding.FragmentNetAutoDialogBinding
import kotlin.coroutines.cancellation.CancellationException

class NetAutoDialogFragment : BaseBindingFragment<FragmentNetAutoDialogBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scopeDialog {
            binding.tvFragment.text = Post<String>(Api.DELAY) {
                param("username", "你的账号")
                param("password", "123456")
            }.await()
        }.finally {
            // 关闭对话框后执行的异常
            if (it is CancellationException) {
                Toast.makeText(requireContext(), "对话框被关闭, 网络请求自动取消", Toast.LENGTH_SHORT).show()
            }
        }
    }
}