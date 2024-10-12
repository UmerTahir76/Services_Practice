package com.example.services_practice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import kotlin.random.Random

class RandomNumberService : Service() {

    // Binder given to clients
    private val binder = LocalBinder()

    // Class used for the client Binder
    inner class LocalBinder : Binder() {
        // Return this instance of RandomNumberService so clients can call public methods
        fun getService(): RandomNumberService = this@RandomNumberService
    }

    // Method for clients to retrieve a random number
    fun getRandomNumber(): Int {
        return Random.nextInt(1, 101) // Return a random number between 1 and 100
    }

    // When a client binds to the service, return the IBinder
    override fun onBind(intent: Intent?): IBinder {
        return binder
    }
}
