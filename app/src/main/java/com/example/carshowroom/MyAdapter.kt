package com.example.carshowroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(private val carList : ArrayList<Car>,) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {


        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.car_list_item,
        parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = carList[position]
        Glide.with(holder.itemView).load(carList[position].carImage).into(holder.carImage)
        holder.carImage.clipToOutline = true
        holder.carName.text = currentItem.carName
        holder.carPrice.text = currentItem.carPrice
        holder.carMileage.text = currentItem.carMileage
        holder.ownerCount.text = currentItem.ownerCount
        holder.carEngine.text = currentItem.carEngine

    }

    override fun getItemCount(): Int {

        return carList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val carName : TextView = itemView.findViewById(R.id.name_car)
        val carPrice : TextView = itemView.findViewById(R.id.price)
        val carMileage : TextView = itemView.findViewById(R.id.mileage)
        val ownerCount : TextView = itemView.findViewById(R.id.count_owner)
        val carEngine : TextView = itemView.findViewById(R.id.engine)
        val carImage : ImageView = itemView.findViewById(R.id.image_car)
    }
}