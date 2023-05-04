package com.example.etp_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast

class Schedule : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        var now = findViewById<Button>(R.id.now)
        var later = findViewById<Button>(R.id.later)

        now.setOnClickListener(){
            Toast.makeText(this, "Opening Menu", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
        later.setOnClickListener(){
            Toast.makeText(this, "Opening scheduling page", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, DateAndTime::class.java)
            startActivity(intent)
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