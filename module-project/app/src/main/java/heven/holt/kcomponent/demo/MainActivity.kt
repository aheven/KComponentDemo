package heven.holt.kcomponent.demo

import android.os.Bundle
import android.view.View
import com.xiaojinzi.component.anno.RouterAnno
import com.xiaojinzi.component.impl.routeApi
import heven.holt.kcomponent.base.RouterConfig
import heven.holt.kcomponent.base.api.RouterApi
import heven.holt.kcomponent.base.ui.BaseActivity
import heven.holt.kcomponent.lib.view.loading.state.ktx.NavBtnType

@RouterAnno(
    hostAndPath = RouterConfig.HOME_MAIN
)
class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbar("主页", NavBtnType.NONE)
    }

    fun pageToLoadStateView(view: View) {
        RouterApi::class.routeApi().pageToLoadStateView(view.context)
    }

    fun pageToCoil(view: View) {
        RouterApi::class.routeApi().pageToCoil(view.context)
    }
}