package heven.holt.kcomponent.user.mock

import android.util.Log
import heven.holt.kcomponent.lib.logan.application
import heven.holt.kcomponent.user.R
import heven.holt.kcomponent.user.api.Api
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.buffer
import okio.source
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class MockDispatcher : Dispatcher() {

    companion object {
        fun initialize() {
            val srv = MockWebServer()
            srv.dispatcher = MockDispatcher()
            thread {
                try {
                    srv.start(8091)
                } catch (e: Exception) {
                    Log.e("日志", "MOCK服务启动失败", e)
                }
            }
        }
    }

    override fun dispatch(request: RecordedRequest): MockResponse {
        var path = request.path
        if (path != null) {
            path = path.substringBefore("?")// 剔除URL参数
        }
        return when (path) {
            Api.TEST -> MockResponse().setHeader("Content-Type", "text/plain")
                .setBody("Request Success：${request.method}")
            Api.DELAY -> MockResponse().setBodyDelay(2, TimeUnit.SECONDS)
                .setHeader("Content-Type", "text/plain")
                .setBody("Request Success：${request.method}")
            Api.GAME -> getRawResponse(R.raw.net_game)
            else -> MockResponse().setResponseCode(404)
        }
    }

    private fun getRawResponse(rawId: Int, delay: Long = 500): MockResponse {
        val buf = application.resources.openRawResource(rawId).source().buffer().readUtf8()
        return MockResponse().setHeader("Content-Type", "application/json; charset=utf-8")
            .setBodyDelay(delay, TimeUnit.MILLISECONDS)
            .setBody(buf)
    }
}
