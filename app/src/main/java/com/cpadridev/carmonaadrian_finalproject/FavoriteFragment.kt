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
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class FavoriteFragment : Fragment() {
    private var retrofit: Retrofit? = null
    private var favoriteAdapter: FavoriteAdapter? = null
    private lateinit var sharedPref: SharedPreferences
    private lateinit var recycler: RecyclerView
    private lateinit var text: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recycler_fragment, container, false)

        recycler = view.findViewById(R.id.recyclerView)
        text = view.findViewById(R.id.txvNoFavorites)

        recycler.setHasFixedSize(true)

        recycler.addItemDecoration(DividerItemDecoration(context, 1))

        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        favoriteAdapter = FavoriteAdapter()

        sharedPref = requireContext().getSharedPreferences("like_button_preferences", Context.MODE_PRIVATE)

        retrofit = Client.getClient()

        getData()

        recycler.adapter = favoriteAdapter

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