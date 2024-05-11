package com.example.recyclerattempt2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerattempt2.fragment_add_workout.add_workout_fragment
import com.example.recyclerattempt2.workout_info_adapter.Workout_info
import com.example.recyclerattempt2.workout_info_adapter.workout_info_adapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class MainActivity : AppCompatActivity() {

    var data: ArrayList<dataClass> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.rv)
        val lm = LinearLayoutManager(this)
//        lm.reverseLayout = true
//        lm.stackFromEnd = true
        rv.layoutManager = lm
        rv.hasFixedSize()

//        val detail_workout = findViewById<View>(R.layout.detailed_workout)

//        val fr1 = add_workout_fragment()


//        // exampleDATA
//        val list1: ArrayList<Workout_info> = ArrayList<Workout_info>()
//        list1.add(Workout_info("test", "1"))
//        list1.add(Workout_info("test2", "2"))
//
//        val list2: ArrayList<Workout_info> = ArrayList<Workout_info>()
//        list2.add(Workout_info("test", "1"))
//        list2.add(Workout_info("test2", "2"))
//        list2.add(Workout_info("test", "1"))
//        list2.add(Workout_info("test2", "2"))
//        list2.add(Workout_info("test", "1"))
//        list2.add(Workout_info("test2", "2"))
//
//        data += dataClass("pushups", "20", list1)
//        data += dataClass("pullups", "10", list2)
//        data += dataClass("deadlift", "200", list1)
//        data += dataClass("situps", "20", list1)
//
//        // End ExampleDATA

        val adapter = recyclerViewAdapter(data) {

            val intent = Intent(this, workout_details::class.java)

            intent.putExtra("workout_name", it.one)
            intent.putExtra("record", it.two)
            intent.putExtra("type", it.type)
            intent.putParcelableArrayListExtra("extras", it.recyclerViewItems)

            startActivityForResult(intent, 2)

        }

        rv.adapter = adapter

        val btn_add_workout = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        btn_add_workout.setOnClickListener() {
            val intent = Intent(this, workout_add::class.java)

//            intent.putExtra("test", "testing")

            startActivityForResult(intent, 1)
        }

    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityResult(requestCode: Int, resultCode: Int, dataIntent: Intent?) {

        super.onActivityResult(requestCode, resultCode, dataIntent)

        if (requestCode == 1 && resultCode == 1) { // add a new workout
            val workoutName = dataIntent?.getStringExtra("workout_name")
            if (workoutName != null) {

                data += dataClass(workoutName, "0")

            }
            val rv = findViewById<RecyclerView>(R.id.rv)
            val adapter = rv.adapter as? recyclerViewAdapter
            adapter?.notifyDataSetChanged()
            Toast.makeText(this, "Added $workoutName", Toast.LENGTH_SHORT).show()
        }

        if (requestCode == 2 && resultCode == 2) { // save or delete workout

            val workoutData = dataIntent?.getParcelableArrayListExtra<Workout_info>("workOutData")
            val workOutName = dataIntent?.getStringExtra("name")
            val deleteWorkOut = dataIntent?.getStringExtra("delete")
            val pr = dataIntent?.getStringExtra("pr")
            val type = dataIntent?.getStringExtra("type")

            val item = data.find { it.one == workOutName }

            if (item != null) {
                if (type != null) {
                    item.type = type
                }
            }

            if (pr != null) {
                if (item != null) {
                    item.two = pr.toString()
                    val rv = findViewById<RecyclerView>(R.id.rv)
                    val adapter = rv.adapter as? recyclerViewAdapter
                    adapter?.notifyDataSetChanged()
                }
            }

            if (deleteWorkOut != "true") {
                if (item != null) {
                    if (workoutData != null) {
                        item.recyclerViewItems = workoutData
                    }
                }
            } else { // delete workout
                if (item != null) {
                    data.remove(item)

                    val rv = findViewById<RecyclerView>(R.id.rv)
                    val adapter = rv.adapter as? recyclerViewAdapter
                    adapter?.notifyDataSetChanged()
                }
            }

        }

    }

    override fun onStop() { // write data
        super.onStop()

        try {
            val fileOutputStream: FileOutputStream = openFileOutput("workoutData.txt", MODE_PRIVATE)
            val objectOutputStream = ObjectOutputStream(fileOutputStream)

            objectOutputStream.writeObject(data)

            objectOutputStream.close()
            fileOutputStream.close()
        } catch (e: FileNotFoundException) {
            data = ArrayList<dataClass>()

        }

    }
    override fun onStart() {
        super.onStart()

        try {
            val fileInputStream: FileInputStream = openFileInput("workoutData.txt")
            val objectInputStream = ObjectInputStream(fileInputStream)

            data.clear()

            val collected_data = objectInputStream.readObject() as ArrayList<dataClass>

            data += collected_data

            data.sortBy { it.one }

//            Toast.makeText(this, data.toString(), Toast.LENGTH_LONG).show()

            objectInputStream.close()
            fileInputStream.close()
        } catch (e: FileNotFoundException) {
            data = ArrayList<dataClass>()

        }
    }

}