package com.example.recyclerattempt2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerattempt2.fragment_add_workout.add_workout_fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.rv)

//        val detail_workout = findViewById<View>(R.layout.detailed_workout)

        val fr1 = add_workout_fragment()

        rv.layoutManager = LinearLayoutManager(this)
        rv.hasFixedSize()

        var data: ArrayList<dataClass> = ArrayList()

        data += dataClass("pushups", "20")
        data += dataClass("pullups", "10")
        data += dataClass("deadlift", "200")
        data += dataClass("situps", "20")

        val adapter = recyclerViewAdapter(data) {
//            Toast.makeText(this, it.two, Toast.LENGTH_SHORT).show()

            val intent = Intent(this, workout_details::class.java)

            intent.putExtra("workout_name", it.one)
            intent.putExtra("record", it.two)

            startActivity(intent)

        }

        rv.adapter = adapter


    }

}