package com.example.etp_project

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.time.Year
import java.util.*

class DateAndTime : AppCompatActivity() {
    lateinit var sharedPreference: SharedPreferences
    var myFile = "delivery_pickup details"
    var dateDetails = "Date"
    var timeDetails = "Time"
    lateinit var dateInput : EditText
    lateinit var timeInput : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_and_time)

        var schedule = findViewById<Button>(R.id.schedule)
        sharedPreference = getSharedPreferences(myFile, Context.MODE_PRIVATE)
        dateInput = findViewById(R.id.date)
        timeInput = findViewById(R.id.time)


        schedule.setOnClickListener(){
            if (dateInput.text.toString().isEmpty() || timeInput.text.toString().isEmpty()){
                Toast.makeText(this, "Please enter all the fields", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Opening Menu", Toast.LENGTH_SHORT).show()
                var date_details = dateInput.text.toString()
                var time_details = timeInput.text.toString()
                var edit = sharedPreference.edit()
                edit.putString(dateDetails, date_details)
                edit.putString(timeDetails, time_details)
                edit.apply()
                var intent = Intent(this, HomePage::class.java)
                startActivity(intent)
            }
        }
    }
    fun showDatePickerDialog(view : View){
        var calendar = Calendar.getInstance()
        dateInput = findViewById(R.id.date)
        var date = calendar[Calendar.DAY_OF_MONTH]
        var month = calendar[Calendar.MONTH]
        var year = calendar[Calendar.YEAR]
        var datePickerDialog : DatePickerDialog
        datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, yearSelected, monthOfYear, dayOfMonth ->
            dateInput.setText("$dayOfMonth/${monthOfYear+1}/$yearSelected")}, year, month, date)
        datePickerDialog.show()
    }
    fun showTimePickerDialog(view : View){
        timeInput = findViewById(R.id.time)
        var calendar = Calendar.getInstance()
        var hour = calendar[Calendar.HOUR_OF_DAY]
        var minute = calendar[Calendar.MINUTE]
        var timePickerDialog : TimePickerDialog
        timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minuteOfDay ->
            if (minuteOfDay<10){
                timeInput.setText("$hourOfDay:0$minuteOfDay")
            }
            else{
                timeInput.setText("$hourOfDay:$minuteOfDay")
            }
        }, hour, minute, false)
        timePickerDialog.show()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.basic_options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        return when(id){
            R.id.logout -> {
                Toast.makeText(this, "Logging out", Toast.LENGTH_SHORT).show()
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