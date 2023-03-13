package heven.holt.kcomponent.lib.adapter.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation

@BindingAdapter("avatar")
fun setAvatar(imageView: ImageView, any: Any?) {
    imageView.load(any) {
        crossfade(true)
        transformations(CircleCropTransformation())
    }
}