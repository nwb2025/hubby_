package com.example.hubby.data.api

import androidx.lifecycle.LiveData
import com.example.core.domain.models.HabitDomainLayer
import com.example.hubby.data.api.HabitResponse
import com.example.hubby.utils.Constants
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*


interface APIHabitService
{
    // this interface will be used by the retrofit library to fetch data from API

    @GET(Constants._URL)
    fun getHabits(@Header("Authorization") token:String) : Flow<List<HabitDomainLayer>>


    @PUT(Constants._URL)
    suspend fun  putHabit(@Header("Authorization") token: String,
                         @Body habitResponse: HabitResponse
    )


    // TODO : we need to return something to check it later
    @DELETE(Constants._URL)
    suspend fun  deleteHabit(@Header("Authorization") token:String ,
                             uid:String)

    @POST(Constants.POST_URL)
    suspend fun postHabit(@Header("Authorization") token:String ,
                          date:String, uid:String)


}