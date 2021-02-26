package com.example.onebancassign.home.cuisine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.onebancassign.R
import com.example.onebancassign.cuisineMenu.ListOfDishes
import com.example.onebancassign.home.Home
import com.example.onebancassign.model.CuisineData
import kotlinx.android.synthetic.main.cuisine_item.view.*
import kotlinx.android.synthetic.main.cuisine_item_infinite.view.*

class CuisineFragment: Fragment(){
    var cuisineData = ArrayList<CuisineData>()




    fun newInstance(context: Home?, pos: Int, scale: Float): Fragment {
        val b = Bundle()
        b.putInt("pos", pos)
        b.putFloat("scale", scale)
        return instantiate(
            context!!,
            CuisineFragment::class.java.name, b
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        with(cuisineData) {
            add(CuisineData(getString(R.string.north_indian), R.drawable.north_indian))
            add(CuisineData(getString(R.string.chinese), R.drawable.chinese))
            add(CuisineData(getString(R.string.mexican), R.drawable.mexican))
            add(CuisineData(getString(R.string.south_indian), R.drawable.southindian))
            add(CuisineData(getString(R.string.italian), R.drawable.pizza))
        }
        if (container == null) {
            return null
        }

        val l = inflater.inflate(R.layout.cuisine_item_infinite, container, false) as LinearLayout
        val pos = this.arguments!!.getInt("pos")

        l.cuisine_item_view_pager.Cuisine_Adapter_Name.text = cuisineData[pos].name
        l.cuisine_item_view_pager.Cuisine_Adapter_Image.setImageResource(cuisineData[pos].image)
        l.setOnClickListener {
            val intent = Intent(it.context, ListOfDishes::class.java)
            intent.putExtra("CuisineName", cuisineData[pos].name)
            startActivity(intent)
        }

        val root: cuisineLinearLayout = l.findViewById<View>(R.id.root) as cuisineLinearLayout
        val scale = this.arguments!!.getFloat("scale")
        root.setScaleBoth(scale)
        return l
    }

}