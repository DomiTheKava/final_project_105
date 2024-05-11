package com.example.recyclerattempt2

import com.example.recyclerattempt2.workout_info_adapter.Workout_info
import java.io.Serializable

class dataClass(val one: String,
                var two: String,
                var recyclerViewItems: ArrayList<Workout_info> = ArrayList(),
                var type: String = "0"
): Serializable {

}