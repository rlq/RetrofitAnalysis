package com.lq.he.sum.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.shape.ShapeAppearancePathProvider
import com.lq.he.sum.R

// 自定义View
class MaskedCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet ?= null,
    defStyle: Int = R.attr.materialCardViewStyle
): MaterialCardView(context, attrs, defStyle) {

    // provider是干嘛用的
    @SuppressLint("RestrictedApi")
    private val pathProvider = ShapeAppearancePathProvider()

    private val path: Path = Path()

    private val shapeAppearance: ShapeAppearanceModel
            = ShapeAppearanceModel(context, attrs, defStyle, R.style.Widget_MaterialComponents_CardView)

    private val rectF = RectF(0f, 0f, 0f, 0f)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.clipPath(path)
    }

    @SuppressLint("RestrictedApi")
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF.right = w.toFloat()
        rectF.bottom = h.toFloat()
        pathProvider.calculatePath(shapeAppearance, 1f, rectF, path)
    }

}