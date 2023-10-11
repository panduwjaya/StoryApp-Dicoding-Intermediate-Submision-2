package com.example.storyapp.data.token

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenPreference private constructor(private val dataStore: DataStore<Preferences>) {

    private val TOKEN_KEY = stringPreferencesKey("token_settings")
    private val NAME_KEY = stringPreferencesKey("name_key")
    private val USERID_KEY = stringPreferencesKey("userid_key")

    suspend fun saveToken(token: String,name: String,email: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
            preferences[NAME_KEY] = name
            preferences[USERID_KEY] = email
        }
    }

    fun readToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY] ?: ""
        }
    }

    fun readName(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[NAME_KEY] ?: ""
        }
    }

    fun readUserId(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[USERID_KEY] ?: ""
        }
    }

    suspend fun removeToken(){
        dataStore.edit { removeKey->
            removeKey.remove(TOKEN_KEY)
            removeKey.remove(NAME_KEY)
            removeKey.remove(USERID_KEY)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: TokenPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): TokenPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = TokenPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}