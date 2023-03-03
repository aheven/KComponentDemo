package heven.holt.kcomponent.user.ui.loadstate.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import heven.holt.kcomponent.lib.view.loading.state.LoadingStateView
import heven.holt.kcomponent.lib.view.loading.state.ViewType
import heven.holt.kcomponent.user.R

class CustomHeaderViewDelegate(
    private val onMessageClickListener: View.OnClickListener,
    private val firstDrawableId: Int,
    private val onFirstBtnClickListener: View.OnClickListener,
    private val secondDrawableId: Int,
    private val onSecondBtnClickListener: View.OnClickListener
) : LoadingStateView.ViewDelegate(ViewType.TITLE) {
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup): View {
        val view = inflater.inflate(R.layout.load_state_custom_header, parent, false)
        val messageBtn = view.findViewById<View>(R.id.btn_message)
        val firstBtn = view.findViewById<ImageView>(R.id.btn_first)
        val secondBtn = view.findViewById<ImageView>(R.id.btn_second)
        firstBtn.setImageResource(firstDrawableId)
        secondBtn.setImageResource(secondDrawableId)
        messageBtn.setOnClickListener(onMessageClickListener)
        firstBtn.setOnClickListener(onFirstBtnClickListener)
        secondBtn.setOnClickListener(onSecondBtnClickListener)
        return view
    }
}