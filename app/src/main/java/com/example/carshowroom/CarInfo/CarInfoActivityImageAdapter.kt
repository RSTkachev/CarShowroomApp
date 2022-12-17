package com.example.carshowroom.CarInfo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carshowroom.R

class CarInfoActivityImageAdapter(
    private val imageList : ArrayList<String>,)
    : RecyclerView.Adapter<CarInfoActivityImageAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {


        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.car_info_image_recyclerview_item,
        parent, false)


        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = imageList[position]
        Glide.with(holder.itemView).load(currentItem).into(holder.carImage)
        holder.carImage.clipToOutline = true

    }

    override fun getItemCount(): Int {

        return imageList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carImage : ImageView = itemView.findViewById(R.id.car_info_image)
    }
}