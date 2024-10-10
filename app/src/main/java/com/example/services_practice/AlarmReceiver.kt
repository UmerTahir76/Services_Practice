package com.example.services_practice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {

        Toast.makeText(context, "Alarm is ringing!", Toast.LENGTH_SHORT).show()

        val mediaPlayer = MediaPlayer.create(context, R.raw.alarm_beep)
        mediaPlayer.start()

        mediaPlayer.setOnCompletionListener {
            mediaPlayer.release()
        }
    }
}
