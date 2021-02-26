package com.example.onebancassign.home.cuisine

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.onebancassign.R
import com.example.onebancassign.home.Home
import com.example.onebancassign.home.cuisine.CuisineFragment


class cuisineAdapter(var fm: FragmentManager, var context: Home?): FragmentPagerAdapter(fm),ViewPager.PageTransformer {
    private val cur: cuisineLinearLayout? = null
    private val next: cuisineLinearLayout? = null
    private var scale = 0f


    override fun getItem(position: Int): Fragment {
        var position = position
        scale = if (position == Home.FIRST_PAGE) BIG_SCALE else SMALL_SCALE
        position = position % Home.PAGES

        return CuisineFragment().newInstance(context, position, scale)
    }


    override fun getCount(): Int {

        return Home.PAGES * Home.LOOPS
    }

    override fun transformPage(page: View, position: Float) {

        val myLinearLayout: cuisineLinearLayout = page.findViewById<View>(R.id.root) as cuisineLinearLayout
        var scale = BIG_SCALE
        scale = if (position > 0) {
            scale - position * DIFF_SCALE
        } else {
            scale + position * DIFF_SCALE
        }
        if (scale < 0) scale = 0f
        myLinearLayout.setScaleBoth(scale)
    }
    companion object{
        val BIG_SCALE = 1.0f
        val SMALL_SCALE = 0.9f
        val DIFF_SCALE = BIG_SCALE - SMALL_SCALE
    }
}