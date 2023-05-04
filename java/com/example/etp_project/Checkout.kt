package com.example.etp_project

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.roundToInt

class Checkout : AppCompatActivity() {
    private var total_price : Int = 0
    lateinit var notificationChannel : NotificationChannel
    lateinit var notificationManager : NotificationManager
    lateinit var notificationBuilder : Notification.Builder
    lateinit var pendingintent : PendingIntent
    lateinit var soundUri : Uri
    lateinit var audioAttr : AudioAttributes
    private val channelId = "My channel Id"
    private val description = "Total amount Payable "
    private val title = "Order Placed"
    val notificationId = 1234
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        var items = intent.getStringExtra("items")
        var amount = intent.getIntExtra("amount", 0)
        var contact = intent.getStringExtra("contact").toString()
        var txt1 = findViewById<TextView>(R.id.textView2)
        var txt2 = findViewById<TextView>(R.id.textView3)
        var cancel = findViewById<Button>(R.id.cancel)
        var order = findViewById<Button>(R.id.order)
        var footer  = findViewById<BottomNavigationView>(R.id.footer)
        var value1 = amount*0.025
        var value2 = amount*0.025
        var sgst = value1.roundToInt()
        var cgst = value2.roundToInt()
        txt1.setText(items)

        total_price = amount + sgst + cgst
        var invoice = "Amount                  --Rs.$amount\n" +
                "SGST 2.5%              --Rs.$sgst\n" +
                "CGST 2.5%              --Rs.$cgst\n" +
                "Total Payable          --Rs.$total_price"
        txt2.setText(invoice)
        cancel.setOnClickListener(){
            Toast.makeText(this, "Cancelling", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, HomePage::class.java)
            intent.putExtra("contact", contact)
            startActivity(intent)
        }
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        order.setOnClickListener(){
            var intent = Intent(this, Rate::class.java)
            intent.putExtra("contact", contact)
            startActivity(intent)
            var notify_intent = Intent(this, HomePage::class.java)
            pendingintent = PendingIntent.getActivity(this, 0, notify_intent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) PendingIntent.FLAG_MUTABLE
            else PendingIntent.FLAG_UPDATE_CURRENT)
            soundUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + applicationContext.packageName + "/" + R.raw.notification)
            audioAttr = AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
            myNotificationChannel()
            notificationManager.notify(notificationId, notificationBuilder.build())
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
                    intent.putExtra("contact", contact)
                    startActivity(intent)
                    true
                }
                else -> true
            }
        }
    }
    private fun myNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GRAY
            notificationChannel.enableVibration(true)
            notificationChannel.setSound(soundUri, audioAttr)
            notificationManager.createNotificationChannel(notificationChannel)

            notificationBuilder = Notification.Builder(this, channelId)
                .setSmallIcon(R.drawable.logo1)
                .setContentTitle(title)
                .setContentText(description + "Rs." + total_price + "/-")
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.logo1))
                .setContentIntent(pendingintent)
                .setAutoCancel(true)
        }
        else {
            notificationBuilder = Notification.Builder(this)
                .setSmallIcon(R.drawable.logo1)
                .setContentTitle(title)
                .setContentText(description)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.logo1))
                .setContentIntent(pendingintent)
                .setAutoCancel(true)
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