package com.example.etp_project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class Rate : AppCompatActivity() {
    lateinit var sharedPreference: SharedPreferences
    var myFile = "Rating"
    var ratingDetails = "Rating"
    var remarksDetails = "Remarks"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate)

        var ratingbar = findViewById<RatingBar>(R.id.rating)
        var remarks = findViewById<EditText>(R.id.remarks)
        val exit = findViewById<Button>(R.id.exit)
        var footer = findViewById<BottomNavigationView>(R.id.footer)
        sharedPreference = getSharedPreferences(myFile, Context.MODE_PRIVATE)
        ratingbar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Toast.makeText(this, "Rating $rating", Toast.LENGTH_SHORT).show()
        }
        exit.setOnClickListener(){
            if (remarks.text.toString().isEmpty()){
                Toast.makeText(this, "Please provide remarks", Toast.LENGTH_SHORT).show()
            }
            else{
                var rating = ratingbar.rating.toString()
                var remarks_details = remarks.text.toString()
                var edit = sharedPreference.edit()
                edit.putString(ratingDetails, rating)
                edit.putString(remarksDetails, remarks_details)
                edit.apply()
                Toast.makeText(this, "Thank you for $rating star Rating", Toast.LENGTH_SHORT).show()
                finishAffinity()
            }
        }
        footer.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    var intent = Intent(this, HomePage::class.java)
                    startActivity(intent)
                    true
                }
                else -> true
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.basic_options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        return when(id){
            R.id.logout -> {
                Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, LoginScreen::class.java)
                startActivity(intent)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}