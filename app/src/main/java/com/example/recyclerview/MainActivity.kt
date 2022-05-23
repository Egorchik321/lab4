package com.example.recyclerview

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


        data class ColorData(
            val colorName: String,
            val colorVal: Int
        )

        interface CellClickListener {
            fun onCellClickListener(data: ColorData)
        }

        class Adapter(private val context: Context,
                      private val list: ArrayList<ColorData>,
                      private val cellClickListener: CellClickListener): RecyclerView.Adapter<Adapter.ViewHolder>(){

            class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
                val color: View = view.findViewById(R.id.colorHex)
                val name: TextView = view.findViewById(R.id.colorName)
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                val view = LayoutInflater.from(context).inflate(R.layout.rview_item,parent,false)
                return ViewHolder(view)
            }

            override fun getItemCount(): Int {
                return list.count()
            }

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                val data = list[position]
                holder.color.setBackgroundColor(data.colorVal)
                holder.name.text = data.colorName

                holder.itemView.setOnClickListener{
                    cellClickListener.onCellClickListener(data)
                }
            }
        }
        class MainActivity : AppCompatActivity(), CellClickListener{
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

                val recyclerView: RecyclerView = findViewById(R.id.rView)
                val samples = arrayListOf<ColorData>(
                    ColorData("BLACK", Color.BLACK),
                    ColorData("WHITE", Color.WHITE),
                    ColorData("BLUE", Color.BLUE),
                    ColorData("RED", Color.RED),
                    ColorData("MAGENTA", Color.MAGENTA))

                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = Adapter(this, samples, this)
            }

            override fun onCellClickListener(data: ColorData) {
                Toast.makeText(this,"IT'S ${data.colorName}" , Toast.LENGTH_SHORT).show()
            }
        }