package com.example.onebancassign

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_home.*

class Cart : AppCompatActivity() {
    var selectedButton = "Delivery"
    private lateinit var linearLayoutManager: LinearLayoutManager
    var dishes = ArrayList<DishesData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)


        initializeButtons()
        initializeCartList()
    }

    private fun initializeButtons(){
        cart_pickup_button.setBackgroundColor(Color.GRAY)
        cart_pickup_button.setOnClickListener {
            if(selectedButton=="Delivery"){
                cart_delivery_button.setBackgroundColor(Color.GRAY)
                cart_pickup_button.setBackgroundColor( resources.getColor(R.color.pink))
                cart_delivery_address.visibility = View.GONE
                selectedButton = "PickUp"
            }

        }
        cart_delivery_button.setOnClickListener {
            if(selectedButton=="PickUp"){
                cart_pickup_button.setBackgroundColor(Color.GRAY)
                cart_delivery_button.setBackgroundColor( resources.getColor(R.color.pink))
                cart_delivery_address.visibility = View.VISIBLE
                selectedButton = "Delivery"

            }

        }
    }

    private fun initializeCartList(){
        dishes.add(DishesData("Chole Bhature",100,0,0,1.9f))
        dishes.add(DishesData("Pizza",100,0,0,4.3f))
        dishes.add(DishesData("Burger",100,0,0,5.0f))
        dishes.add(DishesData("Pasta",100,0,0,2.4f))
        linearLayoutManager = LinearLayoutManager(this)
        cart_list_of_dishes.layoutManager = linearLayoutManager
        cart_list_of_dishes.adapter = CartAdapter(dishes)
    }
}