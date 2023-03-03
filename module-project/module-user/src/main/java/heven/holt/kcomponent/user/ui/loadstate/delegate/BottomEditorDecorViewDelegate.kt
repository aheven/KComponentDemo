package heven.holt.kcomponent.user.ui.loadstate.delegate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import heven.holt.kcomponent.lib.logan.hideKeyboard
import heven.holt.kcomponent.lib.view.loading.state.LoadingStateView
import heven.holt.kcomponent.user.R

class BottomEditorDecorViewDelegate(
    private val onSendListener: OnSendListener
) : LoadingStateView.DecorViewDelegate() {
    override fun onCreateDecorView(context: Context, inflater: LayoutInflater): View {
        val view = inflater.inflate(R.layout.load_state_bottom_editor, null)
        val edtContent = view.findViewById<EditText>(R.id.edt_content)
        view.findViewById<View>(R.id.btn_send).setOnClickListener {
            onSendListener.onSend(edtContent.text.trim().toString())
            edtContent.setText("")
            edtContent.hideKeyboard()
        }
        return view
    }

    override fun getContentParent(decorView: View): ViewGroup {
        return decorView.findViewById(R.id.content_parent)
    }

    interface OnSendListener {
        fun onSend(content: String)
    }
}