package heven.holt.kcomponent.base.ui.dialog

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.RotateDrawable
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StyleRes
import com.drake.net.utils.runMain
import heven.holt.kcomponent.base.R

/**
 * iOS风格的加载对话框
 * @param title 加载对话框的标题
 */
class BubbleDialog @JvmOverloads constructor(
    context: Context,
    var title: String = context.getString(R.string.bubble_loading_title),
    @StyleRes themeResId: Int = R.style.BubbleDialog,
) : Dialog(context, themeResId) {

    private lateinit var tvTitle: TextView
    private lateinit var ivLoading: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_bubble_dialog)
        tvTitle = findViewById(R.id.tv_title)
        ivLoading = findViewById(R.id.iv_loading)
        tvTitle.text = title
        val rotateDrawable = ivLoading.background as RotateDrawable
        ObjectAnimator.ofInt(rotateDrawable, "level", 0, 10000).apply {
            duration = 2000
            repeatCount = ValueAnimator.INFINITE
            interpolator = LinearInterpolator()
            start()
        }
    }

    override fun show() {
        runMain {
            super.show()
        }
    }

    /**
     * 更新标题文本
     */
    fun updateTitle(text: String) {
        if (isShowing) {
            runMain {
                tvTitle.text = text
            }
        } else {
            title = text
        }
    }
}