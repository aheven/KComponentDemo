package heven.holt.kcomponent.user.ui.loadstate.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import heven.holt.kcomponent.lib.logan.hideKeyboard
import heven.holt.kcomponent.lib.view.loading.state.LoadingStateView
import heven.holt.kcomponent.user.R

class SearchHeaderViewDelegate(
    private val onSearchListener: OnSearchListener
) : LoadingStateView.ViewDelegate(SearchHeaderViewDelegate::class) {
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup): View {
        val view = inflater.inflate(R.layout.load_state_search_header, parent, false)
        val editSearch = view.findViewById<EditText>(R.id.edt_search)
        editSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                view.hideKeyboard()
                onSearchListener.onSearch(editSearch.text.trim().toString())
                true
            } else {
                false
            }
        }
        return view
    }

    interface OnSearchListener {
        fun onSearch(keyword: String)
    }
}