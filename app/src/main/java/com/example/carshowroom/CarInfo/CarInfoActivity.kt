package com.example.carshowroom.CarInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carshowroom.CarData
import com.example.carshowroom.R
import java.util.stream.IntStream.range

class CarInfoActivity : AppCompatActivity() {


    private lateinit var  carRecyclerView: RecyclerView
    var adapter: CarInfoActivityImageAdapter? = null
    var isModerator : Boolean = true

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


        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = car.carName

        val imageList : ArrayList<String> = arrayListOf<String>()
        for (i in range(1, car.carImage?.size!!)) {
            imageList += car.carImage?.get(i)!!.toString()
        }

        carRecyclerView = findViewById(R.id.info_image_car_recycler_view)
        carRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        carRecyclerView.setHasFixedSize(true)

        adapter = CarInfoActivityImageAdapter(imageList)
        carRecyclerView.adapter = adapter
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        if (isModerator) {
            inflater.inflate(R.menu.car_info_toolbar, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.change_car_data -> {
            Toast.makeText(this@CarInfoActivity, "It's work!", Toast.LENGTH_SHORT).show()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}