package com.example.onebancassign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_list_of_dishes.*
import java.util.ArrayList

class ListOfDishes : AppCompatActivity() {

    var listOfDishes = ArrayList<DishesData?>()
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_dishes)

        val cuisineName = intent.getStringExtra("CuisineName")
        list_of_dishes_name.text = cuisineName
        addDishesToList()
        addListToRecyclerView()

    }

    private fun addListToRecyclerView(){
        linearLayoutManager = LinearLayoutManager(this)
        list_of_dishes_view.layoutManager = linearLayoutManager
        list_of_dishes_view.adapter = ListOfDishesAdapter(listOfDishes)
        Log.d("listdish",listOfDishes.toString())
    }


    private fun addDishesToList(){
        if(Home.cuisineDishes["north indian"]!!.isNotEmpty()){
            for(i in 0..Home.cuisineDishes["north indian"]!!.size-1){
                listOfDishes.add(Home.cuisineDishes["north indian"]?.get(i))
            }
        }
    }

}