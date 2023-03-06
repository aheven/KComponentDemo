package heven.holt.kcomponent.user.ui.brv

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.drake.brv.utils.linear
import com.drake.brv.utils.setup
import heven.holt.kcomponent.base.ui.BaseBindingFragment
import heven.holt.kcomponent.user.R
import heven.holt.kcomponent.user.databinding.FragmentBrvSimpleBinding
import heven.holt.kcomponent.user.ui.brv.model.BrvSimpleModel

class BrvSimpleFragment : BaseBindingFragment<FragmentBrvSimpleBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.linear().setup {
            addType<BrvSimpleModel>(R.layout.brv_item_simple_text)
            onBind {
                findView<TextView>(R.id.brv_tv_simple).text = getModel<BrvSimpleModel>().name
            }
            R.id.brv_tv_simple.onClick {
                Toast.makeText(itemView.context, "点击文本", Toast.LENGTH_SHORT).show()
            }
        }.models = getData()
    }

    private fun getData(): MutableList<Any> {
        // 在Model中也可以绑定数据
        return mutableListOf<Any>().apply {
            for (i in 0..9) add(BrvSimpleModel())
        }
    }
}