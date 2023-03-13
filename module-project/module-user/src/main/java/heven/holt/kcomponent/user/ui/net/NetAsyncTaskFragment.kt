package heven.holt.kcomponent.user.ui.net

import android.os.Bundle
import android.view.View
import com.drake.net.utils.scope
import heven.holt.kcomponent.base.ui.BaseBindingFragment
import heven.holt.kcomponent.user.databinding.FragmentNetAsyncTaskBinding
import kotlinx.coroutines.*

class NetAsyncTaskFragment : BaseBindingFragment<FragmentNetAsyncTaskBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scope {
            binding.content.text = withContext(Dispatchers.IO) {
                asyncDownLoadFile().await()
                withDownloadFile()
                delay(2000)
                "结果"
            }
        }
    }

    private suspend fun withDownloadFile() = withContext(Dispatchers.IO) {
        delay(100)
        "结果"
    }

    private fun CoroutineScope.asyncDownLoadFile() = async {
        delay(200)
        "结果"
    }
}