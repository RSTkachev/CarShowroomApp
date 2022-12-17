package com.example.carshowroom.CarInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carshowroom.CarData
import com.example.carshowroom.R
import java.util.stream.IntStream.range

class CarInfoActivity : AppCompatActivity() {


    private lateinit var  carRecyclerView: RecyclerView
    var adapter: CarInfoActivityImageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_info)


        val bundle = intent.extras
        val car : CarData = bundle!!.getSerializable("car") as CarData

        findViewById<TextView>(R.id.info_price).text = car.carPrice
        findViewById<TextView>(R.id.info_mileage).text = car.carMileage
        findViewById<TextView>(R.id.info_count_owner).text = car.ownerCount
        findViewById<TextView>(R.id.info_engine).text = car.carEngine
        findViewById<TextView>(R.id.info_description).text = car.carDescription

        val actionBar = supportActionBar
        actionBar!!.title = car.carName
        actionBar.setDisplayHomeAsUpEnabled(true)
        val imageList : ArrayList<String> = arrayListOf<String>()
        for (i in range(1, car.carImage?.size!!)) {
            imageList += car.carImage?.get(i)!!.toString()
        }

        carRecyclerView = findViewById(R.id.info_image_car_recycler_view)
        carRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        carRecyclerView.setHasFixedSize(true)

        val scrollToPosition = 50
        adapter = CarInfoActivityImageAdapter(imageList)
        carRecyclerView.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}