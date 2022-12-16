package com.example.carshowroom.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carshowroom.MainActivity
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

        binding.forgotPassword.setOnClickListener() {
            val intForgotPassword = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intForgotPassword)
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
                                val intent = Intent(this, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                startActivity(intent)
                            }
                            else {
                                firebaseAuth.currentUser?.sendEmailVerification()
                                firebaseAuth.signOut()
                                Toast.makeText(this, "Подтвердите адрес электронной почты", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    else {
                        Log.i("firebaseAuth", it.exception.toString())
                        if (it.exception.toString().equals("com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: " +
                                    "The password is invalid or the user does not have a password.") or
                            (it.exception.toString().equals("com.google.firebase.auth.FirebaseAuthInvalidUserException: " +
                                    "There is no user record corresponding to this identifier. The user may have been deleted."))) {
                            Toast.makeText(this, "Неверный email или пароль", Toast.LENGTH_LONG).show()
                        }
                        if (it.exception.toString().equals("com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: " +
                                    "The email address is badly formatted.")) {
                            Toast.makeText(this, "Некорректный email", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            else {
                Toast.makeText(this, "Заполните поля", Toast.LENGTH_SHORT).show()
            }
        }
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }
}