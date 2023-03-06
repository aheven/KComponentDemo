package heven.holt.kcomponent.user.ui.net

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import heven.holt.kcomponent.base.ui.BaseBindingFragment
import heven.holt.kcomponent.user.databinding.FragmentNetViewModelRequestBinding
import heven.holt.kcomponent.user.ui.net.vm.UserViewModel

class NetViewModelRequestFragment : BaseBindingFragment<FragmentNetViewModelRequestBinding>() {

    private val userViewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.m = userViewModel

        binding.btnFetchUserinfo.setOnClickListener {
            userViewModel.fetchUserInfo()
        }
    }
}