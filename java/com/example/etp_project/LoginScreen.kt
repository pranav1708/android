package com.example.etp_project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginScreen : AppCompatActivity() {
    lateinit var sharedPreferences : SharedPreferences
    var myFile = "Login Details"
    var number = "Contact number"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        var mobile = findViewById<EditText>(R.id.mobile)
        var login = findViewById<Button>(R.id.login)
        sharedPreferences = getSharedPreferences(myFile, Context.MODE_PRIVATE)

        login.setOnClickListener(){
            if (mobile.text.toString().isEmpty()){
                Toast.makeText(this, "Please Enter the Mobile Number", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Logging In", Toast.LENGTH_SHORT).show()
                var contact = mobile.text.toString()
                var edit = sharedPreferences.edit()
                edit.putString(number, contact)
                edit.apply()
                var intent = Intent(this, Pick::class.java)
                intent.putExtra("contact", contact)
                startActivity(intent)
            }
        }
    }
}