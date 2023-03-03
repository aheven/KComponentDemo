package heven.holt.kcomponent.user.ui.coil

import android.os.Bundle
import android.widget.ImageView
import coil.load
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.xiaojinzi.component.anno.RouterAnno
import heven.holt.kcomponent.base.RouterConfig
import heven.holt.kcomponent.base.ui.BaseActivity
import heven.holt.kcomponent.user.R

@RouterAnno(hostAndPath = RouterConfig.USER_COIL)
class CoilActivity : BaseActivity(R.layout.activity_coil) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbar("Coil")

        val imageView = findViewById<ImageView>(R.id.imageView)

        imageView.load("https://img0.baidu.com/it/u=981218435,2998857702&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=675") {
            crossfade(true)
            transformations(RoundedCornersTransformation(10f))
        }
    }
}