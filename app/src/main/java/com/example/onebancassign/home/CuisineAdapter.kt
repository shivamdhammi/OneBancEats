package com.example.onebancassign.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onebancassign.cuisineMenu.ListOfDishes
import com.example.onebancassign.R
import com.example.onebancassign.model.CuisineData
import kotlinx.android.synthetic.main.cuisine_item.view.*


class CuisineAdapter(
    var cuisines: ArrayList<CuisineData>
): RecyclerView.Adapter<CuisineAdapter.CuisineAdapterViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuisineAdapterViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.cuisine_item, parent, false)
        return CuisineAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CuisineAdapterViewHolder, position: Int) {
        if(holder is CuisineAdapterViewHolder){
            holder.bind(cuisines[position])
        }

        holder.itemView.setOnClickListener(View.OnClickListener { view ->
            val intent = Intent(view.context, ListOfDishes::class.java)
            intent.putExtra("CuisineName", cuisines[position].name)
            view.context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return cuisines.size
    }

    inner class CuisineAdapterViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view){
        fun bind(cuisines: CuisineData){
            with(itemView){
                Cuisine_Adapter_Name.text = cuisines.name
                setOnClickListener {
                    val intent = Intent(it.context, ListOfDishes::class.java)
                    intent.putExtra("CuisineName", cuisines.name)

                }

            }
        }
    }

}
