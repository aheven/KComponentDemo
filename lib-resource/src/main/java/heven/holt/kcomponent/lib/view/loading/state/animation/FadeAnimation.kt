package heven.holt.kcomponent.lib.view.loading.state.animation

import android.view.View
import heven.holt.kcomponent.lib.view.loading.state.LoadingStateView

class FadeAnimation(
    private val duration: Long = 500
) : LoadingStateView.Animation {
    override fun onStartShowAnimation(view: View, viewType: Any) {
        view.alpha = 0f
        view.animate().alpha(1f).duration = duration
    }

    override fun onStartHideAnimation(view: View, viewType: Any) {
        view.alpha = 1f
        view.animate().alpha(0f).setDuration(duration).withEndAction {
            view.alpha = 1f
            view.visibility = View.GONE
        }
    }
}