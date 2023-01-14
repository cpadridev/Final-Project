package com.cpadridev.carmonaadrian_finalproject

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cpadridev.carmonaadrian_finalproject.model.Word


class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {
    var list: ArrayList<Word> = ArrayList()
    private lateinit var sharedPref: SharedPreferences

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txvSpanish: TextView
        val txvEnglish: TextView
        val btnFavorite: com.like.LikeButton

        init {
            txvSpanish = view.findViewById(R.id.txvSpanish)
            txvEnglish = view.findViewById(R.id.txvEnglish)
            btnFavorite = view.findViewById(R.id.btnFavorite)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.elements_list, viewGroup, false)

        sharedPref = viewGroup.context.getSharedPreferences("like_button_preferences", Context.MODE_PRIVATE)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        viewHolder.txvSpanish.text = list[position].spanish
        viewHolder.txvEnglish.text = list[position].english
        viewHolder.btnFavorite.isLiked = list[position].liked
        viewHolder.btnFavorite.setOnClickListener {
            list[position].liked = false
            notifyItemChanged(list.indexOf(list[position]))
            val editor = sharedPref.edit()
            editor.putBoolean("like_button_state_${list[position].spanish}", false)
            editor.apply()
            list.remove(list[position])
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = list.size

    fun addToList(word: Word){
        list.add(word)

        notifyDataSetChanged()
    }
}