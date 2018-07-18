package me.wenchieh.shadowlayout

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build.VERSION_CODES
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

/**
 * @author Wenchieh.Lu  2018/6/9
 * 带阴影的父布局
 */
@TargetApi(VERSION_CODES.HONEYCOMB)
class ShadowLayout @JvmOverloads constructor(context: Context, set: AttributeSet? = null,
    def: Int = 0,
    // 阴影左边宽度
    var shadowLeft: Float = 0f,
    // 阴影右边宽度
    var shadowRight: Float = 0f,
    // 阴影上边宽度
    var shadowTop: Float = 0f,
    // 阴影下边宽度
    var shadowBottom: Float = 0f,
    // 阴影四周的圆角
    var radius: Float = 0f,
    // 阴影的模糊半径 越大越模糊
    var blur: Float = 0f) : FrameLayout(context, set, def) {

  // 阴影的颜色
  var color: Int = Color.parseColor("#E1E3E9")
    set(value) {
      paint.setShadowLayer(blur, 0f, 0f, value)
    }

  private val defValue = dp2px(5f)
  private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

  private val rectF = RectF()

  init {
    shadowLeft = defValue
    shadowRight = defValue
    shadowTop = defValue
    shadowBottom = defValue
    radius = dp2px(6f)
    blur = dp2px(15f)
    initAttrs(context, set, def)
    paint.apply {
      color = Color.WHITE
      // 关闭硬件加速
      setLayerType(View.LAYER_TYPE_SOFTWARE, null)
      style = Paint.Style.FILL
      setShadowLayer(blur, 0f, 0f, this@ShadowLayout.color)
    }
    setBackgroundColor(Color.WHITE)
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    if (childCount > 1) {
      throw IllegalArgumentException("childCount must is 1")
    }

    var maxHeight = 0
    var maxWidth = 0
    var childState = 0
    val child = getChildAt(0)
    if (child.visibility != View.GONE) {
      measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0)
      val lp = child.layoutParams as LayoutParams
      maxWidth = Math.max(maxWidth,
          child.measuredWidth + lp.leftMargin + lp.rightMargin)
      maxHeight = Math.max(maxHeight,
          child.measuredHeight + lp.topMargin + lp.bottomMargin)
      childState = View.combineMeasuredStates(childState, child.measuredState)
    }

    // Account for padding too
    maxWidth += paddingLeft + paddingRight + shadowLeft.toInt() + shadowRight.toInt()
    maxHeight += paddingTop + paddingBottom + shadowTop.toInt() + shadowBottom.toInt()

    // Check against our minimum height and width
    maxHeight = Math.max(maxHeight, suggestedMinimumHeight)
    maxWidth = Math.max(maxWidth, suggestedMinimumWidth)


    setMeasuredDimension(View.resolveSizeAndState(maxWidth, widthMeasureSpec, childState),
        View.resolveSizeAndState(maxHeight, heightMeasureSpec,
            childState shl View.MEASURED_HEIGHT_STATE_SHIFT))

    val lp = child.layoutParams as ViewGroup.MarginLayoutParams
    if ((lp as LayoutParams).gravity != Gravity.CENTER) {
      lp.gravity = Gravity.CENTER
    }
    val childWidthMeasureSpec = if (lp.width == LayoutParams.MATCH_PARENT) {
      val width = Math.max(0f, measuredWidth
          - paddingLeft - paddingRight - shadowLeft - shadowRight
          - lp.leftMargin - lp.rightMargin)
      View.MeasureSpec.makeMeasureSpec(
          width.toInt(), View.MeasureSpec.EXACTLY)
    } else {
      ViewGroup.getChildMeasureSpec(widthMeasureSpec,
          (paddingLeft + paddingRight + shadowLeft + shadowRight).toInt() +
              lp.leftMargin + lp.rightMargin,
          lp.width)
    }

    val childHeightMeasureSpec = if (lp.height == LayoutParams.MATCH_PARENT) {
      val height = Math.max(0f, measuredHeight
          - paddingTop - paddingBottom - shadowTop - shadowBottom
          - lp.topMargin - lp.bottomMargin)
      View.MeasureSpec.makeMeasureSpec(
          height.toInt(), View.MeasureSpec.EXACTLY)
    } else {
      ViewGroup.getChildMeasureSpec(widthMeasureSpec,
          (paddingTop + paddingBottom + shadowTop + shadowBottom).toInt() +
              lp.topMargin + lp.bottomMargin,
          lp.height)
    }

    child.measure(childWidthMeasureSpec, childHeightMeasureSpec)
  }

  override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    layoutChildren(left, top, right, bottom)
  }

  private fun layoutChildren(left: Int, top: Int, right: Int, bottom: Int) {
    val childLeft: Int
    val childTop: Int
    val child = getChildAt(0)
    if (child.visibility == View.GONE) return
//    if (layoutDirection == )
    val lp = child.layoutParams as LayoutParams
    val width = child.measuredWidth
    val height = child.measuredHeight

    childLeft = (paddingLeft + shadowLeft + lp.leftMargin).toInt()
    childTop = (paddingTop + shadowTop + lp.topMargin).toInt()

    child.layout(childLeft, childTop, childLeft + width, childTop + height)
  }


  private fun initAttrs(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
    val array = context.obtainStyledAttributes(attrs, R.styleable.ShadowLayout,
        defStyleAttr, 0)

    (0..array.indexCount).asSequence()
        .map {
          array.getIndex(it)
        }
        .forEach {
          when (it) {
            R.styleable.ShadowLayout_shadowLeft -> {
              shadowLeft = array.getDimension(it, defValue)
            }
            R.styleable.ShadowLayout_shadowRight -> {
              shadowRight = array.getDimension(it, defValue)
            }
            R.styleable.ShadowLayout_shadowTop -> {
              shadowTop = array.getDimension(it, defValue)
            }
            R.styleable.ShadowLayout_shadowBottom -> {
              shadowBottom = array.getDimension(it, defValue)
            }
            R.styleable.ShadowLayout_radius -> {
              radius = array.getDimension(it, defValue)
            }
            R.styleable.ShadowLayout_blur -> {
              blur = array.getDimension(it, defValue)
            }
          }
        }
    array.recycle()
  }

  private fun dp2px(value: Float) =
      TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
          context.resources.displayMetrics)

  override fun onDraw(canvas: Canvas) {
    rectF.set(shadowLeft, shadowTop, width.toFloat() - shadowRight, height.toFloat() - shadowBottom)
    canvas.drawRoundRect(rectF, radius, radius, paint)
    super.onDraw(canvas)
  }

  companion object {
    private const val TAG = "ShadowLayout"
  }
}




