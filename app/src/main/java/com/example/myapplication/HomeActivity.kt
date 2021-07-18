package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    lateinit var userDetails: AppCompatTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        userDetails = findViewById(R.id.tv_user_details)
        getCurrentUserInfo()

        var btnSignOut = findViewById<AppCompatButton>(R.id.btn_sign_out)
        btnSignOut.setOnClickListener {
            signOutUser()
        }

    }

    private fun getCurrentUserInfo() {
        val user = Firebase.auth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val uid = user.uid
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl
            val emailVerified = user.isEmailVerified
            userDetails.text = user.email
        }
    }

    private fun signOutUser(){
        Firebase.auth.signOut()
        Toast.makeText(
            baseContext, "User Signed out.",
            Toast.LENGTH_SHORT
        ).show()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}