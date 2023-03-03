package heven.holt.kcomponent.lib.logan

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

fun Context.asActivity(): Activity? =
    this as? Activity ?: (this as? ContextWrapper)?.baseContext?.asActivity()