package com.example.etp_project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomePage : AppCompatActivity() {
    lateinit var sharedPreference : SharedPreferences
    var myFile = "Order Details"
    var food = "Order Details"
    private var items : String = ""
    private var amount : Int = 0
    lateinit var footer : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        footer = findViewById(R.id.footer)
        var btn1 = findViewById<Button>(R.id.btn1)
        var btn2 = findViewById<Button>(R.id.btn2)
        var btn3 = findViewById<Button>(R.id.btn3)
        var btn4 = findViewById<Button>(R.id.btn4)
        var btn5 = findViewById<Button>(R.id.btn5)
        var btn6 = findViewById<Button>(R.id.btn6)
        var btn7 = findViewById<Button>(R.id.btn7)
        var btn8 = findViewById<Button>(R.id.btn8)
        var btn9 = findViewById<Button>(R.id.btn9)
        var btn10 = findViewById<Button>(R.id.btn10)
        var btn11 = findViewById<Button>(R.id.btn11)
        var btn12 = findViewById<Button>(R.id.btn12)
        var btn13 = findViewById<Button>(R.id.btn13)
        var btn14 = findViewById<Button>(R.id.btn14)
        var checkout = findViewById<Button>(R.id.checkout)
        var contact = intent.getStringExtra("contact").toString()
        sharedPreference = getSharedPreferences(myFile, Context.MODE_PRIVATE)


        footer.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    var intent = Intent(this, HomePage::class.java)
                    startActivity(intent)
                    true
                }
                R.id.profile -> {
                    var intent = Intent(this, Profile::class.java)
                    intent.putExtra("contact", contact)
                    startActivity(intent)
                    true
                }
                else -> true
            }
        }
        btn1.setOnClickListener(){
            items += "Chicken Biryani            -- Rs.249/-\n"
            amount += 249
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
        }
        btn2.setOnClickListener(){
            items += "Mutton Biryani            -- Rs.299/-\n"
            amount += 299
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
        }
        btn3.setOnClickListener(){
            items += "Veg Biryani                   -- Rs.219/-\n"
            amount += 219
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
        }
        btn4.setOnClickListener(){
            items += "Paneer Biryani                -- Rs.239/-\n"
            amount += 239
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
        }
        btn5.setOnClickListener(){
            items += "Paneer Tikka                  -- Rs.209/-\n"
            amount += 209
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
        }
        btn6.setOnClickListener(){
            items += "Chicken Tandoori          -- Rs.229/-\n"
            amount += 229
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
        }
        btn7.setOnClickListener(){
            items += "Butter Naan                   -- Rs.69/-\n"
            amount += 69
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
        }
        btn8.setOnClickListener(){
            items += "Tandoori Roti                 -- Rs.19/-\n"
            amount += 19
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
        }
        btn9.setOnClickListener(){
            items += "Kadhai Chicken                -- Rs.269/-\n"
            amount += 269
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
        }
        btn10.setOnClickListener(){
            items += "Butter Chicken                -- Rs.269/-\n"
            amount += 269
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
        }
        btn11.setOnClickListener(){
            items += "Kadhai Paneer             -- Rs.249/-\n"
            amount += 249
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
        }
        btn12.setOnClickListener(){
            items += "Paneer Butter Masala  -- Rs.269/-\n"
            amount += 269
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
        }
        btn13.setOnClickListener(){
            items += "Water Bottle                  -- Rs.20/-\n"
            amount += 20
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
        }
        btn14.setOnClickListener(){
            items += "Cool Drink                    -- Rs.20/-\n"
            amount += 20
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
        }

        checkout.setOnClickListener(){
            if (items.isEmpty() == true){
                Toast.makeText(this, "Select at least 1 item to place order", Toast.LENGTH_SHORT).show()
            }
            else {
                var edit = sharedPreference.edit()
                edit.putString(food, items)
                edit.apply()
                Toast.makeText(this, "Proceeding to checkout", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, Checkout::class.java)
                intent.putExtra("items", items)
                intent.putExtra("amount", amount)
                intent.putExtra("contact", contact)
                startActivity(intent)
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