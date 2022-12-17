package com.example.carshowroom.ui.car

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carshowroom.CarData
import com.example.carshowroom.CarInfo.CarInfoActivity
import com.example.carshowroom.R

class ListCarFragmentAdapter(
    var c: Context, private val carList : ArrayList<CarData>,) : RecyclerView.Adapter<ListCarFragmentAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {


        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.car_list_item,
        parent, false)


        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = carList[position]
        Glide.with(holder.itemView).load(carList[position].carImage?.get(1)).into(holder.carImage)
        holder.carImage.clipToOutline = true
        holder.carName.text = currentItem.carName
        holder.carPrice.text = currentItem.carPrice
        holder.carMileage.text = currentItem.carMileage
        holder.ownerCount.text = currentItem.ownerCount
        holder.carEngine.text = currentItem.carEngine
        holder.itemView.setOnClickListener() {
            val bundle: Bundle = Bundle()
            bundle.putSerializable("car", currentItem);
            val mIntent = Intent(holder.itemView.context, CarInfoActivity::class.java)
            mIntent.putExtras(bundle)
            c.startActivity(mIntent)
        }
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