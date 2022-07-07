package com.example.cookbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class Adapter (val yemekListesi : ArrayList<String>, val idListesi : ArrayList<Int>) : RecyclerView.Adapter<Adapter.YemekHolder>() {

    class YemekHolder (itemView: View) : RecyclerView.ViewHolder (itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row, parent, false)
        return YemekHolder(view)

    }

    override fun onBindViewHolder(holder: YemekHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.recycler_row_text).text = yemekListesi[position]
        holder.itemView.setOnClickListener {

            val action = ListeFragmentDirections.actionListeFragmentToTarifFragment("recyclerdangeldim", idListesi[position])
            Navigation.findNavController(it).navigate(action)

        }
    }

    override fun getItemCount(): Int {
        return yemekListesi.size
    }
}