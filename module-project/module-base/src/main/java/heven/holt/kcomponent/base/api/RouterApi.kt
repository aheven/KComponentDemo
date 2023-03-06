package heven.holt.kcomponent.base.api

import android.content.Context
import androidx.annotation.UiContext
import com.xiaojinzi.component.anno.router.HostAndPathAnno
import com.xiaojinzi.component.anno.router.RouterApiAnno
import heven.holt.kcomponent.base.RouterConfig

@RouterApiAnno
interface RouterApi {
    @HostAndPathAnno(RouterConfig.USER_LOAD_STATE_VIEW)
    fun pageToLoadStateView(@UiContext context: Context)

    @HostAndPathAnno(RouterConfig.USER_BRV_ADAPTER)
    fun pageToBrv(@UiContext context: Context)

    @HostAndPathAnno(RouterConfig.USER_NET)
    fun pageToNet(@UiContext context: Context)
}