package com.example.onebancassign.cuisineMenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onebancassign.R
import com.example.onebancassign.cart.Cart
import com.example.onebancassign.home.Home
import com.example.onebancassign.model.DishesData
import kotlinx.android.synthetic.main.activity_list_of_dishes.*
import java.util.ArrayList

class ListOfDishes : AppCompatActivity() {

    private var listOfDishes = ArrayList<DishesData?>()
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_dishes)

        val cuisineName = intent.getStringExtra("CuisineName")
        list_of_dishes_name.text = cuisineName
        addDishesToList(cuisineName!!.toString())
        addListToRecyclerView()
        initializeCartButton()

    }

    private fun addListToRecyclerView() {
        linearLayoutManager = LinearLayoutManager(this)
        list_of_dishes_view.layoutManager = linearLayoutManager
        list_of_dishes_view.adapter = ListOfDishesAdapter(listOfDishes)
    }


    private fun addDishesToList(cuisineName: String) {
        if (Home.cuisineDishes[cuisineName]!!.isNotEmpty()) {
            for (i in 0 until Home.cuisineDishes[cuisineName]!!.size) {
                listOfDishes.add(Home.cuisineDishes[cuisineName]?.get(i))
            }
        }
    }

    private fun initializeCartButton() {
        list_of_dishes_cart.setOnClickListener {
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)
        }
    }


}