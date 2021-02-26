package com.example.onebancassign.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onebancassign.R
import com.example.onebancassign.model.DishesData
import kotlinx.android.synthetic.main.dish_item.view.*
import java.util.ArrayList

class TopDishesAdapter(private var listOfDishes: ArrayList<DishesData?>) :
    RecyclerView.Adapter<TopDishesAdapter.TopDishesAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopDishesAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dish_item, parent, false)
        return TopDishesAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopDishesAdapterViewHolder, position: Int) {
        holder.bind(listOfDishes[position])
    }

    override fun getItemCount(): Int {
        return listOfDishes.size
    }

    inner class TopDishesAdapterViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        fun bind(listOfDishes: DishesData?) {
            with(itemView) {
                dish_item_name.text = listOfDishes?.name
                dish_item_rating.text = listOfDishes?.rating.toString()
                dish_item_price.text = context.getString(R.string.rs_d, listOfDishes?.price)
                if (checkForQuantity(listOfDishes)) {
                    dish_item_add.visibility = View.GONE
                    dish_item_quantity_counter.visibility = View.VISIBLE
                    dish_item_quantity.text = Home.cartList[listOfDishes?.name]?.quantity.toString()
                    Log.d("quantityCheck", Home.cartList[listOfDishes?.name]?.quantity.toString())
                }
                dish_item_add.setOnClickListener {
                    dish_item_quantity.text = "1"
                    dish_item_add.visibility = View.GONE
                    dish_item_quantity_counter.visibility = View.VISIBLE
                    addDishToCart(listOfDishes, dish_item_quantity.text.toString())
                }
                dish_item_quantity_minus.setOnClickListener {
                    dish_item_quantity.text =
                        (dish_item_quantity.text.toString().toInt() - 1).toString()
                    if (dish_item_quantity.text.toString() == "0") {
                        dish_item_add.visibility = View.VISIBLE
                        dish_item_quantity_counter.visibility = View.GONE
                    }
                    addDishToCart(listOfDishes, dish_item_quantity.text.toString())
                }
                dish_item_quantity_plus.setOnClickListener {
                    dish_item_quantity.text =
                        (dish_item_quantity.text.toString().toInt() + 1).toString()
                    addDishToCart(listOfDishes, dish_item_quantity.text.toString())
                }

            }

        }
    }

    private fun addDishToCart(listOfDishes: DishesData?, quantity: String?) {
        if (Home.cartList.containsKey(listOfDishes?.name)) {
            if (quantity == "0") {
                Home.cartList.remove(listOfDishes?.name)
            } else {
                Home.cartList[listOfDishes?.name] =
                    DishesData(
                        listOfDishes?.name, listOfDishes?.price,
                        listOfDishes?.image, quantity!!.toInt(), listOfDishes?.rating
                    )
            }
        } else {
            Home.cartList[listOfDishes?.name.toString()] =
                DishesData(
                    listOfDishes?.name, listOfDishes?.price,
                    listOfDishes?.image, 1, listOfDishes?.rating
                )

        }
    }

    private fun checkForQuantity(listOfDishes: DishesData?): Boolean {
        return Home.cartList.containsKey(listOfDishes?.name)
    }

}