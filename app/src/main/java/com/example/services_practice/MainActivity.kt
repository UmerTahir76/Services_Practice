package com.example.services_practice

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var timePicker: TimePicker
    private lateinit var setAlarmButton: Button
    private lateinit var shiftButton: Button
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timePicker = findViewById(R.id.timePicker)
        setAlarmButton = findViewById(R.id.setAlarmButton)
        shiftButton = findViewById(R.id.shiftScreenButton)
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        setAlarmButton.setOnClickListener {
            setAlarm()
        }
        shiftButton.setOnClickListener{
            startActivity(Intent(this,HandleMusic::class.java))
        }

    }

    @SuppressLint("ScheduleExactAlarm")
    private fun setAlarm() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.hour)
        calendar.set(Calendar.MINUTE, timePicker.minute)
        calendar.set(Calendar.SECOND, 0)

        val intent = Intent(this, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }


    //if i use the below code it make an issue , app kill from bg --- no alarm ring


//        val currentTime = System.currentTimeMillis()
//        val alarmTime = calendar.timeInMillis
//        if (alarmTime <= currentTime) {
//            Toast.makeText(this, "Set time for future!", Toast.LENGTH_SHORT).show()
//        } else {
//            val delay = alarmTime - currentTime
//
//            // Create a Handler to simulate an alarm after the specified delay
//            android.os.Handler().postDelayed({
//                playAlarmBeep()
//            }, delay)
//            Toast.makeText(this, "Alarm set!", Toast.LENGTH_SHORT).show()
//        }
//    }
//    private fun playAlarmBeep() {
//        Toast.makeText(this, "Alarm is ringing!", Toast.LENGTH_SHORT).show()
//    }
}
