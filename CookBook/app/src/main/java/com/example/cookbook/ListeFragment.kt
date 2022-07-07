package com.example.cookbook

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookbook.databinding.FragmentListeBinding
import java.lang.Exception

class ListeFragment : Fragment() {

    var yemekIsimListesi = ArrayList<String>()
    var yemekIdListesi = ArrayList<Int>()
    private lateinit var listeAdapter : Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private var _binding : FragmentListeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_liste, container, false)

        _binding = FragmentListeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listeAdapter = Adapter(yemekIsimListesi, yemekIdListesi)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = listeAdapter

        sqlVeriAlma()
    }


    fun sqlVeriAlma(){
        try {

            activity?.let {
                val database = it.openOrCreateDatabase("YEMEKLER", Context.MODE_PRIVATE, null)

                val cursor = database.rawQuery("SELECT * FROM yemekler", null)

                val yemekIsmiIndex = cursor.getColumnIndex("yemekismi")
                val yemekIdIndex = cursor.getColumnIndex("id")

                yemekIsimListesi.clear()
                yemekIdListesi.clear()

                while (cursor.moveToNext()){
                   /* println(cursor.getString(yemekIsmiIndex))
                    println(cursor.getInt(yemekIdIndex))*/

                    yemekIsimListesi.add(cursor.getString(yemekIsmiIndex))
                    yemekIdListesi.add(cursor.getInt(yemekIdIndex))
                }

                listeAdapter.notifyDataSetChanged()

                cursor.close()

            }

        }
        catch (e : Exception){
            e.printStackTrace()
        }
    }



}