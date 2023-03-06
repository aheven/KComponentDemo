package heven.holt.kcomponent.user.ui.net

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.drake.net.*
import com.drake.net.utils.scopeNetLife
import heven.holt.kcomponent.base.ui.BaseBindingFragment
import heven.holt.kcomponent.user.R
import heven.holt.kcomponent.user.api.Api
import heven.holt.kcomponent.user.databinding.FragmentNetRequestMethodBinding

class NetRequestMethodFragment : BaseBindingFragment<FragmentNetRequestMethodBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_net_request_method, menu)
    }

    private fun GET() {
        scopeNetLife {
            binding.content.text = Get<String>(Api.TEST).await()
        }
    }

    private fun POST() {
        scopeNetLife {
            binding.content.text = Post<String>(Api.TEST).await()
        }
    }

    private fun HEAD() {
        scopeNetLife {
            binding.content.text = Head<String>(Api.TEST).await()
        }
    }

    private fun PUT() {
        scopeNetLife {
            binding.content.text = Put<String>(Api.TEST).await()
        }
    }

    private fun PATCH() {
        scopeNetLife {
            binding.content.text = Patch<String>(Api.TEST).await()
        }
    }

    private fun DELETE() {
        scopeNetLife {
            binding.content.text = Delete<String>(Api.TEST).await()
        }
    }

    private fun TRACE() {
        scopeNetLife {
            binding.content.text = Trace<String>(Api.TEST).await()
        }
    }

    private fun OPTIONS() {
        scopeNetLife {
            binding.content.text = Options<String>(Api.TEST).await()
        }
    }

    private fun JSON() {
        val name = "金城武"
        val age = 29
        val measurements = listOf(100, 100, 100)
        scopeNetLife {
            binding.content.text = Post<String>(Api.TEST) {
                json("name" to name, "age" to age, "measurements" to measurements)
            }.await()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.get -> GET()
            R.id.post -> POST()
            R.id.head -> HEAD()
            R.id.trace -> TRACE()
            R.id.options -> OPTIONS()
            R.id.delete -> DELETE()
            R.id.put -> PUT()
            R.id.patch -> PATCH()
            R.id.json -> JSON()
        }
        return super.onOptionsItemSelected(item)
    }
}