package com.cpadridev.carmonaadrian_finalproject

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cpadridev.carmonaadrian_finalproject.connection.Api
import com.cpadridev.carmonaadrian_finalproject.connection.Client
import com.cpadridev.carmonaadrian_finalproject.model.Word
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class FavoriteFragment : Fragment() {
    private var retrofit: Retrofit? = null
    private var favoriteAdapter: FavoriteAdapter? = null
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recycler_fragment, container, false)

        val recycler: RecyclerView = view.findViewById(R.id.recyclerView)
        val txv: TextView = view.findViewById(R.id.txvNoFavorites)

        recycler.setHasFixedSize(true)

        recycler.addItemDecoration(DividerItemDecoration(context, 1))

        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        favoriteAdapter = FavoriteAdapter()

        sharedPref = requireContext().getSharedPreferences("like_button_preferences", Context.MODE_PRIVATE)

        retrofit = Client.getClient()

        getData()

        recycler.adapter = favoriteAdapter
/*
        if (favoriteAdapter?.itemCount != 0) {
            txv.text = getString(R.string.information_no_favorites)
            txv.visibility = View.VISIBLE
            recycler.visibility = View.GONE
        } else {
            txv.visibility = View.GONE
            recycler.visibility = View.VISIBLE
        }

        favoriteAdapter?.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                super.onItemRangeChanged(positionStart, itemCount)
                if (itemCount == 0) {
                    txv.text = getString(R.string.information_no_favorites)
                    txv.visibility = View.VISIBLE
                    recycler.visibility = View.GONE
                } else {
                    txv.visibility = View.GONE
                    recycler.visibility = View.VISIBLE
                }
            }
        })*/

        return view
    }

    private fun getData() {
        val api: Api? = retrofit?.create(Api::class.java)

        val words = arrayListOf("numbers", "days", "colors")

        for (w in words) {
            api?.getAll(w)?.enqueue(object : Callback<ArrayList<Word>> {
                override fun onResponse(call: Call<ArrayList<Word>>, response: Response<ArrayList<Word>>) {
                    if (response.isSuccessful) {
                        val wordsList = response.body()

                        if (wordsList != null) {
                            for (word in wordsList) {
                                word.liked = sharedPref.getBoolean("like_button_state_${word.spanish}", false)
                                if (word.liked) {
                                    favoriteAdapter?.addToList(word)
                                }
                            }
                        }
                    } else
                        Toast.makeText(context, getString(R.string.error_response), Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<ArrayList<Word>>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}