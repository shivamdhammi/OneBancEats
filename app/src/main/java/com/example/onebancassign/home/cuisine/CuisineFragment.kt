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

//    var mResources = intArrayOf(
//        R.drawable.intro_1,
//        R.drawable.intro_2,
//        R.drawable.intro_3,
//        R.drawable.intro_4,
//        R.drawable.intro_5
//    )


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
        Log.d("Boken","1")
        with(cuisineData) {
            add(CuisineData("North Indian", 0))
            add(CuisineData("Chinese", 0))
            add(CuisineData("Mexican", 0))
            add(CuisineData("South Indian", 0))
            add(CuisineData("Italian", 0))
        }
        if (container == null) {
            return null
        }
        Log.d("Boken","2")

        val l = inflater.inflate(R.layout.cuisine_item_infinite, container, false) as LinearLayout
        val pos = this.arguments!!.getInt("pos")

        l.cuisine_item_view_pager.Cuisine_Adapter_Name.text = cuisineData[pos].name
        Log.d("Dhammi","1")
        l.setOnClickListener {
            Log.d("Dhammi","2")
            val intent = Intent(it.context, ListOfDishes::class.java)
            intent.putExtra("CuisineName", cuisineData[pos].name)
            startActivity(intent)
        }
        Log.d("Dhammi","3")


        Log.d("Boken", cuisineData[pos].name)
        val root: cuisineLinearLayout = l.findViewById<View>(R.id.root) as cuisineLinearLayout
        val scale = this.arguments!!.getFloat("scale")
        root.setScaleBoth(scale)
        return l
    }

}