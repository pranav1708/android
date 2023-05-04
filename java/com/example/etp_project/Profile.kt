package com.example.etp_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.w3c.dom.Text

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        var contact = intent.getStringExtra("contact").toString()
        var details = findViewById<TextView>(R.id.textView6)
        var logout = findViewById<Button>(R.id.logout)
        var footer = findViewById<BottomNavigationView>(R.id.footer)

        details.setText("Contact No. $contact")
        logout.setOnClickListener(){
            var intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
        }
        footer.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    var intent = Intent(this, HomePage::class.java)
                    startActivity(intent)
                    true
                }
                R.id.profile -> {
                    var intent = Intent(this, Profile::class.java)
                    startActivity(intent)
                    true
                }
                else -> true
            }
        }
    }
}