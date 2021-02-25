package com.example.onebancassign.cart

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onebancassign.model.DishesData
import com.example.onebancassign.R
import com.example.onebancassign.home.Home
import kotlinx.android.synthetic.main.activity_cart.*

class Cart : AppCompatActivity() {
    var selectedButton = "Delivery"
    private lateinit var linearLayoutManager: LinearLayoutManager
    var dishes = ArrayList<DishesData>()
    var totalPrice = 0.0f

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
        for(item in Home.cartList){
            var name = item.value?.name
            var price = item.value?.price
            var image = item.value?.image
            var quantity = item.value?.quantity
            var rating = item.value?.rating
            dishes.add(DishesData(name,price, image, quantity, rating))
            totalPrice += price!!.toFloat()*quantity!!.toFloat()
        }

        cart_sub_total.text = totalPrice.toString()
        cart_cgst.text = (totalPrice*(0.025f)).toString()
        cart_gst.text = (totalPrice*(0.025f)).toString()
        cart_grand_total.text = (totalPrice + 2 *((totalPrice*(0.025f)))).toString()
        linearLayoutManager = LinearLayoutManager(this)
        cart_list_of_dishes.layoutManager = linearLayoutManager
        cart_list_of_dishes.adapter = CartAdapter(dishes)
    }
}