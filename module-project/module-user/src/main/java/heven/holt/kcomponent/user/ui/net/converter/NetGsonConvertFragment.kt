package heven.holt.kcomponent.user.ui.net.converter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.drake.net.Get
import com.drake.net.utils.scopeNetLife
import heven.holt.kcomponent.user.api.Api
import heven.holt.kcomponent.user.converter.GsonConverter
import heven.holt.kcomponent.user.databinding.FragmentNetCustomConvertBinding
import heven.holt.kcomponent.user.model.net.NetGameModel

class NetGsonConvertFragment : BaseNetConvertFragment<FragmentNetCustomConvertBinding>() {
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvConvertTip.text = """
            1. Google官方出品
            2. Json解析库Java上的老牌解析库
            3. 不支持Kotlin构造参数默认值
            4. 支持动态解析
        """.trimIndent()

        scopeNetLife {
            val data = Get<NetGameModel>(Api.GAME) {
                // 单例转换器, 此时会忽略全局转换器, 在Net中可以直接解析List等嵌套泛型数据
                converter = GsonConverter()
            }.await()
            binding.tvFragment.text = data.list?.firstOrNull()?.name
        }
    }
}