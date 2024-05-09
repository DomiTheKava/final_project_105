package com.example.recyclerattempt2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class workout_details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_workout)

        val workoutName = intent.getStringExtra("workout_name")
        val record = intent.getStringExtra("record")

        findViewById<TextView>(R.id.workout_name).text = workoutName
        findViewById<TextView>(R.id.current_record).text = record
    }
}