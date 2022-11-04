package com.example.carshowroom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carshowroom.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpNoAccount.setOnClickListener() {
            val intSignUpNoAccount = Intent(this, SignUpActivity::class.java)
            startActivity(intSignUpNoAccount)
        }

        firebaseAuth = FirebaseAuth.getInstance()
        binding.signInAccount.setOnClickListener() {
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        firebaseAuth.currentUser?.isEmailVerified?.let { verified ->
                            if (verified) {
                                val intent = Intent(this, UserActivity::class.java)
                                startActivity(intent)
                            }
                            else {
                                firebaseAuth.currentUser?.sendEmailVerification()
                                Toast.makeText(this, "Подтвердите адрес электронной почты", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else {
                Toast.makeText(this, "Empty Fields Are Not Allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}