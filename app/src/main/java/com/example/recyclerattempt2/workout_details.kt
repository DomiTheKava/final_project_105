package com.example.recyclerattempt2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerattempt2.workout_info_adapter.Workout_info
import com.example.recyclerattempt2.workout_info_adapter.workout_info_adapter

class workout_details : AppCompatActivity() {

    var extraInfo: ArrayList<Workout_info> = ArrayList()
    lateinit var workoutName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_workout)

        val rv = findViewById<RecyclerView>(R.id.more_details)
        rv.layoutManager = LinearLayoutManager(this)
        rv.hasFixedSize()

        workoutName = intent.getStringExtra("workout_name").toString()
        val record = intent.getStringExtra("record")

        findViewById<TextView>(R.id.workout_name).text = workoutName
        findViewById<TextView>(R.id.current_record).text = record

        var extra = intent.getParcelableArrayListExtra<Workout_info>("extras")
        val list_data: ArrayList<Workout_info> = ArrayList<Workout_info>()

        if (extra != null) {
            for (item in extra) {
                list_data += item
            }
        }
        update_recycler(rv, list_data)

        extra = list_data // need to return this on close

        extraInfo = list_data // set extra info and stuff

        val btn_add_data = findViewById<Button>(R.id.add_data)

        btn_add_data.setOnClickListener() { // TODO Make when this button is clicked set a date for last updated on screen somewhere
            list_data += Workout_info("", "")
            update_recycler(rv, list_data)
        }

        val btn_delete_workout = findViewById<Button>(R.id.delete_workout)

        btn_delete_workout.setOnClickListener() {
            val intent = Intent()

            intent.putExtra("delete", "true")
            intent.putExtra("name", workoutName)


            setResult(2, intent)

            finish()
        }

    }

    fun update_recycler(rv: RecyclerView,data: ArrayList<Workout_info>) {
        val adapter = workout_info_adapter(data)
        rv.adapter = adapter
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val intent = Intent()

        intent.putExtra("workOutData", extraInfo)
        intent.putExtra("name", workoutName)

        var pr = "0"
        for (item in extraInfo) {
            val record = item.data.toString().replace(Regex("[^0-9]"), "")
            if (!record.isBlank()) {
                if (record.toInt() > pr.replace(Regex("[^0-9]"), "").toInt()) {
                    pr = item.data.toString()
                }
            }
        }

        intent.putExtra("pr", pr)

        setResult(2, intent)

        finish()
    }

}