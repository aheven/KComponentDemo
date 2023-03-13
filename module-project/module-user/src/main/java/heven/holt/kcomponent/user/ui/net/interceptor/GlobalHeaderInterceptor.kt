package heven.holt.kcomponent.user.ui.net.interceptor

import com.drake.net.interceptor.RequestInterceptor
import com.drake.net.request.BaseRequest
import heven.holt.kcomponent.user.constants.UserConfig

class GlobalHeaderInterceptor : RequestInterceptor {
    override fun interceptor(request: BaseRequest) {
        request.setHeader("client", "Android")
        request.setHeader("token", UserConfig.token)
    }
}