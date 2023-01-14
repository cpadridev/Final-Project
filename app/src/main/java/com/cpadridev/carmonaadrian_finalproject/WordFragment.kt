package com.cpadridev.carmonaadrian_finalproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class WordFragment(var string: String) : Fragment() {
    private var retrofit: Retrofit? = null
    private var wordAdapter: WordAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recycler_fragment, container, false)

        val recycler: RecyclerView = view.findViewById(R.id.recyclerView)

        recycler.setHasFixedSize(true)

        recycler.addItemDecoration(DividerItemDecoration(context, 1))

        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        wordAdapter = WordAdapter()

        recycler.adapter = wordAdapter

        retrofit = Client.getClient()

        getData()

        return view
    }

    private fun getData() {
        val api: Api? = retrofit?.create(Api::class.java)

        when(string) {
            "numbers" -> {
                api?.getNumbers()?.enqueue(object : Callback<ArrayList<Word>> {
                    override fun onResponse(call: Call<ArrayList<Word>>, response: Response<ArrayList<Word>>) {
                        if (response.isSuccessful) {
                            val wordsList = response.body()

                            if (wordsList != null) {
                                wordAdapter?.addToList(wordsList)
                            }
                        } else
                            Toast.makeText(context, getString(R.string.error_response), Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<ArrayList<Word>>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
            }
            "days" -> {
                api?.getDays()?.enqueue(object : Callback<ArrayList<Word>> {
                    override fun onResponse(call: Call<ArrayList<Word>>, response: Response<ArrayList<Word>>) {
                        if (response.isSuccessful) {
                            val wordsList = response.body()

                            if (wordsList != null) {
                                wordAdapter?.addToList(wordsList)
                            }
                        } else
                            Toast.makeText(context, getString(R.string.error_response), Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<ArrayList<Word>>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
            }
            "colors" -> {
                api?.getColors()?.enqueue(object : Callback<ArrayList<Word>> {
                    override fun onResponse(call: Call<ArrayList<Word>>, response: Response<ArrayList<Word>>) {
                        if (response.isSuccessful) {
                            val wordsList = response.body()

                            if (wordsList != null) {
                                wordAdapter?.addToList(wordsList)
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
}