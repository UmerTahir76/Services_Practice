package com.example.services_practice

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Bound : AppCompatActivity() {

    private var randomNumberService: RandomNumberService? = null
    private var bound: Boolean = false
    private lateinit var shiftButton: Button


    // Defines callbacks for service binding, passed to bindService()
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // We've bound to RandomNumberService, cast the IBinder and get LocalService instance
            val binder = service as RandomNumberService.LocalBinder
            randomNumberService = binder.getService()
            bound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            bound = false
        }
    }

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound)

        val button = findViewById<Button>(R.id.getNumberButton)
        val textView = findViewById<TextView>(R.id.randomNumberText)
        shiftButton = findViewById(R.id.shiftScreenButton)

        shiftButton.setOnClickListener{
            startActivity(Intent(this,HandleMusic::class.java))
        }
        // Button click listener to get a random number from the service
        button.setOnClickListener {
            if (bound) {
                // Get a random number from the service and display it
                val randomNumber = randomNumberService?.getRandomNumber()
                textView.text = "Random Number: $randomNumber"
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // Bind to the service
        Intent(this, RandomNumberService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        // Unbind from the service
        if (bound) {
            unbindService(connection)
            bound = false
        }
    }
}
