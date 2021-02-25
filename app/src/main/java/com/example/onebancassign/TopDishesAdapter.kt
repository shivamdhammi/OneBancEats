package com.example.onebancassign

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dish_item.view.*
import java.util.ArrayList

class TopDishesAdapter(var listOfDishes: ArrayList<DishesData?>):RecyclerView.Adapter<TopDishesAdapter.TopDishesAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopDishesAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dish_item, parent, false)
        return TopDishesAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopDishesAdapterViewHolder, position: Int) {
        if(holder is TopDishesAdapter.TopDishesAdapterViewHolder){
            holder.bind(listOfDishes[position])
        }
    }

    override fun getItemCount(): Int {
        return listOfDishes.size
    }

    inner class TopDishesAdapterViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view){
        fun bind(listOfDishes: DishesData?){
            with(itemView){
                dish_item_name.text = listOfDishes?.name
                dish_item_rating.text = listOfDishes?.rating.toString()
                dish_item_price.text = "₹ " + listOfDishes?.price.toString()
                dish_item_add.setOnClickListener {
                    dish_item_quantity.text = "1"
                    dish_item_add.visibility = View.GONE
                    dish_item_quantity_counter.visibility = View.VISIBLE
                }
                dish_item_quantity_minus.setOnClickListener {
                    dish_item_quantity.text = (dish_item_quantity.text.toString().toInt() - 1).toString()
                    if(dish_item_quantity.text.toString()== "0"){
                        dish_item_add.visibility = View.VISIBLE
                        dish_item_quantity_counter.visibility = View.GONE
                    }
                }
                dish_item_quantity_plus.setOnClickListener {
                    dish_item_quantity.text = (dish_item_quantity.text.toString().toInt() + 1).toString()
                }


            }

        }
    }
}