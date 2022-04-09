package com.example.helloworld

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.helloworld.RunningMatches.MyAdapter
import com.example.helloworld.RunningMatches.RunningInterface
import com.example.helloworld.RunningMatches.RunningMatchesItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.rows.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.home_load)
        setContentView(R.layout.activity_main)




        recyclerId.setHasFixedSize(true) //para que o tamanho do recyclerview nao mude na inserção ou remoção

        linearLayoutManager = LinearLayoutManager(this)
        recyclerId.layoutManager = linearLayoutManager


        getRunningData()

    }

    private fun getRunningData() {
        val retrofitBuilder = Retrofit.Builder()  //pega os dados da api
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.pandascore.co/csgo/")
            .build()
            .create(RunningInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<RunningMatchesItem>?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<RunningMatchesItem>?>,response: Response<List<RunningMatchesItem>?>) {
                    val responseBody = response.body()!!  //coloca os dados dentro da variavel (forçando nao sendo null)

                    myAdapter = MyAdapter(baseContext, responseBody)
                    myAdapter.notifyDataSetChanged() //atualiza quando os dados mudam
                    recyclerId.adapter = myAdapter

            }

            override fun onFailure(call: Call<List<RunningMatchesItem>?>, t: Throwable) {
                d("MainActivity", "onFailure: " +t.message) //se chamada da api der errado mostra erro
            }
        })
    }
}