package heven.holt.kcomponent.base

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.xiaojinzi.component.anno.RouterAnno
import com.xiaojinzi.component.impl.RouterRequest

@RouterAnno(hostAndPath = RouterConfig.SYSTEM_APP_DETAIL)
fun appDetail(routerRequest: RouterRequest): Intent {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.data = Uri.parse("package:${routerRequest.context!!.packageName}")
    return intent
}