package com.example.onebancassign.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onebancassign.model.DishesData
import com.example.onebancassign.R
import kotlinx.android.synthetic.main.item_cart.view.*

class CartAdapter(var listOfDishes: ArrayList<DishesData>) :
    RecyclerView.Adapter<CartAdapter.CartAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartAdapterViewHolder, position: Int) {
        holder.bind(listOfDishes[position])
    }

    override fun getItemCount(): Int {
        return listOfDishes.size
    }

    inner class CartAdapterViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        fun bind(listOfDishes: DishesData?) {
            with(itemView) {
                cart_item_dish_name.text = listOfDishes?.name
                cart_item_dish_quantity.text =
                    context.getString(R.string.x_d, listOfDishes?.quantity)
                val price = listOfDishes?.price!!.toInt() * listOfDishes.quantity!!.toInt()
                cart_item_dish_price.text = context.getString(R.string.rs_f, price)
            }
        }
    }
}
