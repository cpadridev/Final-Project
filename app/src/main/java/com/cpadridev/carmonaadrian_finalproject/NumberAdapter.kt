package com.cpadridev.carmonaadrian_finalproject

import com.cpadridev.carmonaadrian_finalproject.model.Number
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView

class NumberAdapter : RecyclerView.Adapter<NumberAdapter.MyViewHolder>() {
    private var list: ArrayList<Number> = ArrayList()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txvSpanish: TextView
        val txvEnglish: TextView
        val btnFavorite: Button

        init {
            txvSpanish = view.findViewById(R.id.txvSpanish)
            txvEnglish = view.findViewById(R.id.txvEnglish)
            btnFavorite = view.findViewById(R.id.btnFavorite)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.elements_list, viewGroup, false)

        view.setOnClickListener {

        }

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        viewHolder.txvSpanish.text = list[position].spanish
        viewHolder.txvEnglish.text = list[position].english
        viewHolder.btnFavorite.setOnClickListener {

        }
    }

    override fun getItemCount() = list.size

    fun addToList(list_: ArrayList<Number>){
        list.clear()
        list.addAll(list_)

        notifyDataSetChanged()
    }

    fun addToList(number: Number){
        list.add(number)

        notifyDataSetChanged()
    }

    fun updateList(pos: Int, number: Number){
        list[pos] = number

        notifyDataSetChanged()
    }

    fun deleteFromList(pos: Int){
        list.removeAt(pos)

        notifyDataSetChanged()
    }
}