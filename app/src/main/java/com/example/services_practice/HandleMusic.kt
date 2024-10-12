package com.example.services_practice

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HandleMusic : AppCompatActivity() {

    private lateinit var startMusicButton: Button
    private lateinit var stopMusicButton: Button
    private lateinit var shiftButton: Button
    private lateinit var nextButton: Button



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handle_music)

        startMusicButton = findViewById(R.id.startMusicButton)
        stopMusicButton = findViewById(R.id.stopMusicButton)
        shiftButton = findViewById(R.id.shiftScreenButton)
        nextButton = findViewById(R.id.nextScreenBtn)


        shiftButton.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
        nextButton.setOnClickListener{
            startActivity(Intent(this,Bound::class.java))
        }
        startMusicButton.setOnClickListener {
            val intent = Intent(this, MusicService::class.java)
            startService(intent)

        }
        stopMusicButton.setOnClickListener {
            val intent = Intent(this, MusicService::class.java)
            stopService(intent)
        }
    }
}
