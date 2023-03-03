package heven.holt.kcomponent.user.ui.loadstate.widget

import android.content.Context
import android.util.SparseArray

internal object LoadingRendererFactory {
    private val LOADING_RENDERERS = SparseArray<Class<out LoadingRenderer>>()
    @JvmStatic
    @Throws(Exception::class)
    fun createLoadingRenderer(context: Context?, loadingRendererId: Int): LoadingRenderer {
        val loadingRendererClazz: Class<*> = LOADING_RENDERERS[loadingRendererId]
        val constructors = loadingRendererClazz.declaredConstructors
        for (constructor in constructors) {
            val parameterTypes = constructor.parameterTypes
            if (parameterTypes.size == 1 && parameterTypes[0] == Context::class.java) {
                constructor.isAccessible = true
                return constructor.newInstance(context) as LoadingRenderer
            }
        }
        throw InstantiationException()
    }

    init {
        LOADING_RENDERERS.put(9, ElectricFanLoadingRenderer::class.java)
    }
}