package com.example.carshowroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class CarInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_info)


        val price : TextView = findViewById(R.id.info_price)
        val mileage : TextView = findViewById(R.id.info_mileage)
        val owners : TextView = findViewById(R.id.info_count_owner)
        val engine : TextView = findViewById(R.id.info_engine)
        val description : TextView = findViewById(R.id.info_description)
        val image : ImageView = findViewById(R.id.info_image_car)
        val b = intent.extras
        price.text = b!!.getString("price")
        mileage.text = b.getString("mileage")
        owners.text = b.getString("owner")
        engine.text = b.getString("engine")
        description.text = b.getString("description")

        val actionBar = supportActionBar

        actionBar!!.title = b.getString("name")
        actionBar.setDisplayHomeAsUpEnabled(true)

        image.clipToOutline = true
        Glide.with(this).load(b.getString("image")).into(image)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}