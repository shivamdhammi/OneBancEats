package com.example.onebancassign.home

import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.onebancassign.R
import com.example.onebancassign.cart.Cart
import com.example.onebancassign.home.cuisine.cuisineAdapter
import com.example.onebancassign.model.CuisineData
import com.example.onebancassign.model.DishesData
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*
import kotlin.collections.ArrayList


class Home : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    var cuisineData = ArrayList<CuisineData>()
    lateinit var locale: Locale
    var currentLanguage: String? = "en"
    var currentLang: String = "incoming"
    var dishes = ArrayList<DishesData>()

    var adapter: cuisineAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        currentLanguage = intent.getStringExtra(currentLang)

        initializeCuisinesData()
        initializeLanguageChanger()
        initializeDishesData()
        initializeRecyclerView()
        addListToRecyclerView()
        initializeCartButton()

        home_cart.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                applicationContext,
                R.color.pink
            )
        )
        home_language_changer.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                applicationContext,
                R.color.pink
            )
        )

        ////////////////////////////////////////////////////

        adapter = cuisineAdapter(this.supportFragmentManager, this)
        home_cuisines_view_pager?.setAdapter(adapter)
        home_cuisines_view_pager?.setPageTransformer(false, adapter)

        home_cuisines_view_pager?.setCurrentItem(FIRST_PAGE)
        home_cuisines_view_pager?.setOffscreenPageLimit(4)
        home_cuisines_view_pager?.setPageMargin(-100)
    }

    private fun setLocale(localeName: String) {
        locale = Locale(localeName)
        val res: Resources = resources
        val dm: DisplayMetrics = res.getDisplayMetrics()
        val conf: Configuration = res.getConfiguration()
        conf.locale = locale
        res.updateConfiguration(conf, dm)
        val refresh = Intent(this, Home::class.java)
        refresh.putExtra(currentLang, localeName)
        startActivity(refresh)

    }

    private fun initializeDishesData(){
        dishes = ArrayList(emptyList<DishesData>())
        dishes.add(DishesData("Chole Bhature", 100, 0, 0, 1.9f))
        dishes.add(DishesData("Rajma Chawal", 100, 0, 0, 4.3f))
        dishes.add(DishesData("Prantha", 100, 0, 0, 5.0f))
        cuisineDishes["north indian"] = dishes

        dishes = ArrayList(emptyList<DishesData>())
        dishes.add(DishesData("Panner Momos", 100, 0, 0, 4.5f))
        dishes.add(DishesData("Tandoori Pizza", 100, 0, 0, 4.8f))
        dishes.add(DishesData("Masala Fries", 100, 0, 0, 4.7f))
        cuisineDishes["Top Dishes"] = dishes

    }

    private fun initializeCuisinesData(){
        with(cuisineData) {
            add(CuisineData("North Indian", R.drawable.north_indian))
            add(CuisineData("Chinese", 0))
            add(CuisineData("Mexican", 0))
            add(CuisineData("South Indian", 0))
            add(CuisineData("Italian", 0))
        }
    }

    private fun initializeLanguageChanger(){
        home_language_changer.setOnClickListener {
            if (currentLanguage == "en")
                setLocale("hi")
            else
                setLocale("en")
        }
    }

    private fun initializeCartButton(){
        home_cart.setOnClickListener {
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)
        }
    }

    private fun initializeRecyclerView(){
        linearLayoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        home_cuisines_view.layoutManager = linearLayoutManager
        home_cuisines_view.adapter = CuisineAdapter(cuisineData)
    }

    private fun addListToRecyclerView(){
        linearLayoutManager = LinearLayoutManager(this)
        home_top_3_dishes.layoutManager = linearLayoutManager
        var listOfDishes = ArrayList<DishesData?>()
        if(cuisineDishes["Top Dishes"]!!.isNotEmpty()){
            for(i in 0..cuisineDishes["Top Dishes"]!!.size-1){
                listOfDishes.add(cuisineDishes["Top Dishes"]?.get(i))
            }
        }
        home_top_3_dishes.adapter = TopDishesAdapter(listOfDishes)
    }

    companion object{
        var cuisineDishes = mutableMapOf<String, ArrayList<DishesData>>()
        var cartList = mutableMapOf<String?, DishesData?>()
        val PAGES = 4
        val LOOPS = 1000
        val FIRST_PAGE = PAGES * LOOPS / 2
    }
}
