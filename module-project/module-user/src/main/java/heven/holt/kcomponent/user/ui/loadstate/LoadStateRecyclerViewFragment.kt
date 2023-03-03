package heven.holt.kcomponent.user.ui.loadstate

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.request.ImageRequest
import heven.holt.kcomponent.base.ui.BaseFragment
import heven.holt.kcomponent.lib.view.loading.state.LoadingStateView
import heven.holt.kcomponent.user.R
import heven.holt.kcomponent.user.ui.loadstate.delegate.CoolLoadingViewDelegate

class LoadStateRecyclerViewFragment : BaseFragment(R.layout.fragment_load_state_recycler_view) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as LoadStateViewActivity).updateToolbar("RecyclerView(cool loading)")

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = ImageAdapter()
    }
}

private class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    class ImageViewHolder(
        private val loadingStateView: LoadingStateView
    ) : RecyclerView.ViewHolder(loadingStateView.decorView) {
        private val imageView = itemView.findViewById<ImageView>(R.id.imageView)

        init {
            loadingStateView.register(CoolLoadingViewDelegate())
        }

        fun showImage(url: String) {
            Log.e("TAG", "showImage: ${url}/${imageView.measuredWidth}x${imageView.measuredHeight}")
            loadingStateView.showLoadingView()
            val imageRequest = ImageRequest.Builder(itemView.context)
                .crossfade(true)
                .data("${url}/${imageView.width}x${imageView.height}")
                .target {
                    loadingStateView.showContentView()
                    imageView.setImageDrawable(it)
                }.build()
            imageView.context.imageLoader.enqueue(imageRequest)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_load_state_recycler, parent, false)
        val loadingStateView = LoadingStateView(view)
        return ImageViewHolder(loadingStateView)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val url = "https://source.unsplash.com/collection/${(0..100).random()}"
        holder.itemView.post {
            holder.showImage(url)
        }

    }

    override fun getItemCount(): Int = 10
}