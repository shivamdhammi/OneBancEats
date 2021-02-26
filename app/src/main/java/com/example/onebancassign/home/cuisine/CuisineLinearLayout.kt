package com.example.onebancassign.home.cuisine

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.LinearLayout

class CuisineLinearLayout(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private var scale: Float = CuisineAdapter.BIG_SCALE

    fun setScaleBoth(scale: Float) {
        this.scale = scale
        this.invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        val w = this.width
        val h = this.height
        canvas.scale(scale, scale, w / 2.toFloat(), h / 2.toFloat())
        super.onDraw(canvas)
    }

}