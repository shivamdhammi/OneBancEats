package com.example.onebancassign.home.cuisine

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.onebancassign.R
import com.example.onebancassign.home.Home


class CuisineAdapter(var fm: FragmentManager, var context: Home?) : FragmentPagerAdapter(fm),
    ViewPager.PageTransformer {
    private val next: CuisineLinearLayout? = null
    private var scale = 0f


    override fun getItem(position: Int): Fragment {
        scale = if (position == Home.FIRST_PAGE) BIG_SCALE else SMALL_SCALE
        return CuisineFragment().newInstance(context, position % Home.PAGES, scale)
    }


    override fun getCount(): Int {
        return Home.PAGES * Home.LOOPS
    }

    override fun transformPage(page: View, position: Float) {
        val myLinearLayout: CuisineLinearLayout =
            page.findViewById<View>(R.id.root) as CuisineLinearLayout
        var scale = BIG_SCALE
        scale = if (position > 0) {
            scale - position * DIFF_SCALE
        } else {
            scale + position * DIFF_SCALE
        }
        if (scale < 0) scale = 0f
        myLinearLayout.setScaleBoth(scale)
    }

    companion object {
        const val BIG_SCALE = 1.0f
        const val SMALL_SCALE = 0.9f
        const val DIFF_SCALE = BIG_SCALE - SMALL_SCALE
    }
}