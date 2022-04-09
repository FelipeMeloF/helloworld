package com.example.helloworld.RunningMatches

import retrofit2.Call
import retrofit2.http.GET



interface RunningInterface {
    @GET("matches?filter[status]=not_started,running&sort=&page=1&per_page=25&sort=begin_at&token=kbrMDh24tctwsGOduatlM_S5L22SUIy2eXCkMoVCoML5bAMheNg")
    fun getData(): Call<List<RunningMatchesItem>>
}