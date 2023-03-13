package heven.holt.kcomponent.lib.logan

import android.app.Application
import android.content.Context
import androidx.startup.Initializer

class AppInitializer : Initializer<Unit> {

    private var started = 0

    override fun create(context: Context) {
        application = context as Application
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}