package com.example.etp_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast

class Pick : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick)

        var dinein = findViewById<Button>(R.id.dinein)
        var delivery = findViewById<Button>(R.id.delivery)
        var pickup = findViewById<Button>(R.id.pickup)
        var contact = intent.getStringExtra("contact")

        dinein.setOnClickListener(){
            Toast.makeText(this, "Opening Dine-In menu", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, HomePage::class.java)
            intent.putExtra("contact", contact)
            startActivity(intent)
        }
        delivery.setOnClickListener(){
            Toast.makeText(this, "Opening Delivery menu", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, Schedule::class.java)
            intent.putExtra("contact", contact)
            startActivity(intent)
        }
        pickup.setOnClickListener(){
            Toast.makeText(this, "Opening Takeaway menu", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, Schedule::class.java)
            intent.putExtra("contact", contact)
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
                finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}