package heven.holt.kcomponent.lib.logan

import android.view.View
import androidx.core.view.WindowInsetsCompat

fun View.showKeyboard() = windowInsetsControllerCompat?.show(WindowInsetsCompat.Type.ime())

fun View.hideKeyboard() = windowInsetsControllerCompat?.hide(WindowInsetsCompat.Type.ime())