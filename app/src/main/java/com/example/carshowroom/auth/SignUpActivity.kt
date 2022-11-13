package com.example.carshowroom.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.carshowroom.UserData
import com.example.carshowroom.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signInExistingAccount.setOnClickListener() {
            val intSignInExistingAccount = Intent(this, SignInActivity::class.java)
            startActivity(intSignInExistingAccount)
        }
        binding.signUpAccount.setOnClickListener() {
            val name = binding.fullName.text.toString()
            val age : Int? = binding.age.text.toString().toIntOrNull()
            val email = binding.emailNew.text.toString()
            val pass = binding.passwordNew.text.toString()

            if (name.isNotEmpty() && binding.age.text.toString().isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty()) {
                if (age != null && age > 0) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val user: UserData = UserData(name, age, email)

                            firebaseAuth.currentUser?.sendEmailVerification()
                            FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().currentUser!!.uid)
                                .setValue(user)

                            Toast.makeText(
                                this,
                                "Для окончания регистрации подтвердите адрес электронной почти",
                                Toast.LENGTH_SHORT
                            ).show()

                            firebaseAuth.signOut()
                            val intSignIn = Intent(this, SignInActivity::class.java)
                            startActivity(intSignIn)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else {
                    Toast.makeText(this, "Введен некорректный возраст", Toast.LENGTH_SHORT).show()
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
        onBackPressed()
        return true
    }
}