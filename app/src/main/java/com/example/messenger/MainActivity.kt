package com.example.messenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.sign

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if(user == null) Log.d(LOG_TAG,"Not Authorized")
        else Log.d(LOG_TAG,"Authorized ${user.uid}")

       signInUser("newUser@gmail.com","0000000")


    }


    private fun signInUser(email: String,password: String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                Log.d(LOG_TAG,"You logged in to app")
            }
            .addOnFailureListener{
                Log.w(LOG_TAG,"A problem: ${it.message} " )
            }

    }

    private fun registerUser(email: String, password: String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                val user = auth.currentUser
                if(user == null){
                    Log.d(LOG_TAG, "Not authorized")
                }else {
                    Log.d(LOG_TAG, "Authorized")
                }
            }
            .addOnFailureListener{
                Log.w(LOG_TAG,it.message,it.cause)
            }

    }


    companion object {
        private const val LOG_TAG  = "Main Activity"
    }
}