package com.example.recyclerattempt2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class recyclerViewAdapter(var data: ArrayList<dataClass>,
                            val clickListener: (dataClass) -> Unit): RecyclerView.Adapter<recyclerViewAdapter.ViewHolderClass>() {

    class ViewHolderClass(view: View, clickAtPosition: (Int) -> Unit): RecyclerView.ViewHolder(view) {
        val one = view.findViewById<TextView>(R.id.textView)
        val two = view.findViewById<TextView>(R.id.textView2)
//        val moreDataHolder =

        init {
            itemView.setOnClickListener {
                clickAtPosition(adapterPosition)
//                clickListener("${adapterPosition}")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val view = ViewHolderClass(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_layout, parent, false))
        {
            clickListener(data[it])
        }


        return view
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val current_item = data[position]
        holder.one.text = current_item.one
        holder.two.text = current_item.two

//        holder.itemView.setOnClickListener {
//            clickListener("${position}")
//        }
    }

}