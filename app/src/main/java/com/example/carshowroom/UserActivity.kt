package com.example.carshowroom

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.carshowroom.auth.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import com.example.carshowroom.databinding.ActivityUserBinding
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class UserActivity : AppCompatActivity() {

    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var firebaseUser : FirebaseUser

    private lateinit var reference: DatabaseReference

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)

        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        firebaseUser = firebaseAuth.currentUser!!

        reference = FirebaseDatabase.getInstance().getReference("Users")

        val userID : String = firebaseUser.uid

        reference.child(userID).addListenerForSingleValueEvent(object  : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val userProfile : UserData = snapshot.getValue(UserData::class.java)!!
                    binding.userName.text = userProfile.fullName
                    binding.userAge.text = userProfile.age.toString()
                    binding.userEmail.text = userProfile.email
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@UserActivity, "Что-то пошло не так", Toast.LENGTH_SHORT).show()
            }

        })


        binding.logOut.setOnClickListener() {
            firebaseAuth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }
}