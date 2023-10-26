package com.example.storyapp.data.network

import com.example.storyapp.data.response.detail.DetailListResponse
import com.example.storyapp.data.response.detail.DetailResponse
import com.example.storyapp.data.response.list.ListStory
import com.example.storyapp.data.response.list.StoryListReponses
import com.example.storyapp.data.response.location.StoryLocationResponse
import com.example.storyapp.data.response.login.LoginResponse
import com.example.storyapp.data.response.register.RegisterResponse
import com.example.storyapp.data.response.upload.FileUploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    suspend fun postRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @Multipart
    @POST("stories")
    suspend fun postStory(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
    ): FileUploadResponse

    @GET("stories")
    suspend fun getStoryPaging(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ) : StoryListReponses

    @GET("stories")
    suspend fun getStoryLocation(
        @Query("location") location: Int,
    ) : StoryLocationResponse

    @GET("stories/{id}")
    suspend fun getStoryDetail(
        @Path("id") id: String
    ): DetailResponse
}