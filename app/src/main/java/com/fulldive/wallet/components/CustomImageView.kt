package com.fulldive.wallet.components

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.ceil

/* This ImageView keep aspect ratio for scaled images */
class CustomImageView : AppCompatImageView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas) {
        if (scaleType == ScaleType.FIT_XY) {
            super.onDraw(canvas)
        } else {
            if (drawable == null) {
                return
            }

            val drawableWidth = drawable.intrinsicWidth
            val drawableHeight = drawable.intrinsicHeight

            if (drawableWidth == 0 || drawableHeight == 0) {
                return
            }
            val viewWidth = width
            val viewHeight = height
            var width = viewWidth
            var height = ceil(
                (width.toFloat() * drawableHeight.toFloat() / drawableWidth.toFloat()).toDouble()
            ).toInt()
            if (height < viewHeight) {
                height = viewHeight
                width = ceil(
                    (height.toFloat() * drawableWidth.toFloat() / drawableHeight.toFloat()).toDouble()
                ).toInt()
            }

            var l = 0
            var r = width
            var t = 0
            var b = height
            when (scaleType) {
                ScaleType.FIT_START -> {
                    l = (viewWidth - width) / 2
                    r = l + width
                }
                ScaleType.FIT_CENTER -> {
                    l = (viewWidth - width) / 2
                    r = l + width
                    t = (viewHeight - height) / 2
                    b = t + height
                }
                else -> Unit
            }
            drawable.setBounds(l, t, r, b)
            drawable.draw(canvas)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (scaleType == ScaleType.FIT_XY) {
            drawable?.let { drawable ->
                val width: Int
                val height: Int
                if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
                    height = MeasureSpec.getSize(heightMeasureSpec)
                    width = ceil(
                        (height.toFloat() * drawable.intrinsicWidth.toFloat() / drawable.intrinsicHeight.toFloat()).toDouble()
                    ).toInt()
                } else {
                    width = MeasureSpec.getSize(widthMeasureSpec)
                    height = ceil(
                        (width.toFloat() * drawable.intrinsicHeight.toFloat() / drawable.intrinsicWidth.toFloat()).toDouble()
                    ).toInt()
                }
                setMeasuredDimension(width, height)
                return
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}
