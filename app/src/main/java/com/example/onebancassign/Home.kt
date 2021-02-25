package com.example.onebancassign

import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_list_of_dishes.*
import kotlin.collections.ArrayList
import java.util.Locale


class Home : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    var cuisineData = ArrayList<CuisineData>()
    lateinit var locale: Locale
    var currentLanguage: String? = "en"
    var currentLang: String = "incoming"
    var dishes = ArrayList<DishesData>()


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


//        home_top_1.dish_item_add.setOnClickListener {
//            if(home_top_1.dish_item_quantity.text=="0"){
//                home_top_1.dish_item_add.visibility = View.GONE
//                home_top_1.dish_item_quantity_counter.visibility = View.VISIBLE
//                home_top_1.dish_item_quantity.text="1"
//            }else{
//
//            }
//
//        }

//        var loading = true
//        var pastVisiblesItems: Int
//        var visibleItemCount: Int
//        var totalItemCount: Int
//
//        home_cuisines_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                Log.d("positionXY",dx.toString()+"  " +dy.toString())
//                if (dx > 0) { //check for scroll down
//                    visibleItemCount = linearLayoutManager.childCount
//                    totalItemCount = linearLayoutManager.itemCount
//                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition()
//                    if (loading) {
//                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
//                            loading = false
//                            Log.v("...", "Last Item Wow !")
//                            // Do pagination.. i.e. fetch new data
//                            loading = true
//                        }
//                    }
//                }
//            }
//        })

        home_cart.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(applicationContext, R.color.pink))
        home_language_changer.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(applicationContext, R.color.pink))
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
        dishes.add(DishesData("Something1",100,0,0,1.9f))
        dishes.add(DishesData("Something2",100,0,0,4.3f))
        dishes.add(DishesData("Something3",100,0,0,5.0f))
        dishes.add(DishesData("Something4",100,0,0,2.4f))
        dishes.add(DishesData("Something5",100,0,0,5.0f))
        dishes.add(DishesData("Something6",100,0,0,1.8f))
        cuisineDishes["north indian"] = dishes

        dishes.clear()
        dishes.add(DishesData("Something1",100,0,0,4.5f))
        dishes.add(DishesData("Something2",100,0,0,4.8f))
        dishes.add(DishesData("Something3",100,0,0,4.7f))
        dishes.add(DishesData("Something4",100,0,0,4.7f))
        dishes.add(DishesData("Something5",100,0,0,4.7f))
        dishes.add(DishesData("Something6",100,0,0,4.7f))
        dishes.add(DishesData("Something7",100,0,0,4.7f))
        dishes.add(DishesData("Something8",100,0,0,4.7f))
        cuisineDishes["Top Dishes"] = dishes

    }

    private fun initializeCuisinesData(){
        with(cuisineData) {
            add(CuisineData("North Indian", 0))
            add(CuisineData("Chinese,", 0))
            add(CuisineData("Mexican,", 0))
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
        var cuisineDishes = mutableMapOf<String,ArrayList<DishesData>>()
    }
}
