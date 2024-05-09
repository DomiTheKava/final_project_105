package com.example.recyclerattempt2.fragment_add_workout

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.recyclerattempt2.R

class add_workout_fragment : Fragment() {


    private lateinit var viewModel: AddWorkoutFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_workout_fragment, container, false)

        val btn_cancel = view.findViewById<Button>(R.id.cancel)

        btn_cancel.setOnClickListener() {
            print("asd")
        }
        return view
    }




}