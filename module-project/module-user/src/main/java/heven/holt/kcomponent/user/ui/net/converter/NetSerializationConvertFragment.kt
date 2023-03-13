package heven.holt.kcomponent.user.ui.net.converter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.drake.net.Get
import com.drake.net.utils.scopeNetLife
import heven.holt.kcomponent.user.api.Api
import heven.holt.kcomponent.user.converter.SerializationConverter
import heven.holt.kcomponent.user.databinding.FragmentNetCustomConvertBinding
import heven.holt.kcomponent.user.model.net.NetGameModel

class NetSerializationConvertFragment : BaseNetConvertFragment<FragmentNetCustomConvertBinding>() {
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvConvertTip.text = """
            1. kotlin官方出品, 推荐使用 
            2. kotlinx.serialization 是Kotlin上是最完美的序列化工具 
            3. 支持多种反序列化数据类型Pair/枚举/Map...
            4. 多配置选项
            5. 支持动态解析
            6. 支持ProtoBuf/CBOR/JSON等数据
        """.trimIndent()

        scopeNetLife {
            val data = Get<NetGameModel>(Api.GAME) {
                // 该转换器直接解析JSON中的data字段, 而非返回的整个JSON字符串
                converter = SerializationConverter()
            }.await()
            binding.tvFragment.text = data.list?.firstOrNull()?.name
        }
    }
}