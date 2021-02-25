package com.example.onebancassign.home.cuisine

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.LinearLayout

class cuisineLinearLayout(context: Context?, attrs: AttributeSet?): LinearLayout(context,attrs){

    private var scale: Float = cuisineAdapter.BIG_SCALE

    fun setScaleBoth(scale: Float) {
        this.scale = scale
        this.invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        // The main mechanism to display scale animation, you can customize it
        // as your needs
        val w = this.width
        val h = this.height
        canvas.scale(scale, scale, w / 2.toFloat(), h / 2.toFloat())
        super.onDraw(canvas)
    }

}