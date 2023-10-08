package com.example.storyapp.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.githubusernew.data.local.StoryDao
import com.example.githubusernew.data.local.StoryEntity
import com.example.storyapp.data.network.ApiService
import com.example.storyapp.data.response.detail.DetailListResponse
import com.example.storyapp.data.response.list.StoriesListResponse
import com.example.storyapp.data.response.list.StoriesResponse
import com.example.storyapp.data.response.login.LoginResponse
import com.example.storyapp.data.response.register.RegisterResponse
import com.example.storyapp.data.token.TokenPreference
import com.example.storyapp.utils.Result
import com.google.gson.Gson
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException

class StoryRepository(
    private val apiService: ApiService,
    private val storyDao: StoryDao,
    private val tokenPreference: TokenPreference
) {
    companion object {
        @Volatile
        private var instance: StoryRepository? = null
        fun getInstance(
            apiService: ApiService,
            storyDao: StoryDao,
            tokenPreference: TokenPreference
        ): StoryRepository =
            instance ?: synchronized(this) {
                instance ?: StoryRepository(apiService, storyDao,tokenPreference)
            }.also { instance = it }
    }

    fun postRegister(name: String, email: String, password: String): LiveData<Result<RegisterResponse>> = liveData{
        emit(Result.Loading)
        try {
            //get success message
            val responseMessage = apiService.postRegister(name,email,password)
            emit(Result.Success(responseMessage))
        } catch (e: HttpException) {
            //get error message
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, LoginResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage))
        }
    }

    fun postLogin(email: String, password: String): LiveData<Result<LoginResponse>> = liveData{
        emit(Result.Loading)
        try {
            //get success message
            val responseMessage = apiService.postLogin(email,password)
            emit(Result.Success(responseMessage))
        } catch (e: HttpException) {
            //get error message
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, LoginResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage))
        }
    }

    fun postStory(file: MultipartBody.Part, description: RequestBody): LiveData<Result<StoriesResponse>> = liveData {
        emit(Result.Loading)
        try {
            //get success message
            val responseMessage = apiService.postStory(file,description)
            emit(Result.Success(responseMessage))
        } catch (e: HttpException){
            //get error message
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, StoriesResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage))
        }
    }

    fun getStory(): LiveData<Result<ArrayList<StoriesListResponse>>> = liveData{
        emit(Result.Loading)
        try {
            //get success message
            val responseMessage = apiService.getStory()
            emit(Result.Success(responseMessage))
        } catch (e: HttpException){
            //get error message
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, StoriesResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage))
        }
    }

    fun getHeadlineNews(): LiveData<kotlin.Result<List<StoryEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getStory()
            val articles = response.articles
            val newsList = articles.map { article ->
                StoryEntity(
                    article.title,
                    article.publishedAt,
                    article.urlToImage,
                    article.url
                )
            }
            storyDao.deleteAll()
            storyDao.insertStory(newsList)
        } catch (e: Exception) {
            Log.d("NewsRepository", "getHeadlineNews: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
        val localData: LiveData<kotlin.Result<List<NewsEntity>>> = newsDao.getNews().map {Result.Success(it) }
        emitSource(localData)
    }

    fun getDetailStory(id: String): LiveData<Result<DetailListResponse>> = liveData {
        emit(Result.Loading)
        try {
            //get success message
            val responseMessage = apiService.getStoryDetail(id)
            emit(Result.Success(responseMessage))
        } catch (e: HttpException){
            //get error message
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, StoriesResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage))
        }
    }
}