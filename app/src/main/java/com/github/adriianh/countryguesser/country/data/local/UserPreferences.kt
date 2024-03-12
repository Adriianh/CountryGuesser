package com.github.adriianh.countryguesser.country.data.local

import android.content.Context
import android.content.SharedPreferences
import com.github.adriianh.countryguesser.country.data.mapper.toJson
import com.github.adriianh.countryguesser.country.data.mapper.toUser
import com.github.adriianh.countryguesser.country.domain.model.User

class UserPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

    fun setSelectedUser(user: User) {
        sharedPreferences.edit().putString("selectedUser", user.toJson()).apply()
    }

    fun getSelectedUser(): User? {
        return sharedPreferences.getString("selectedUser", null)?.toUser()
    }

    fun setScore(score: Int) {
        sharedPreferences.edit().putInt("score", score).apply()
    }

    fun getScore(): Int {
        return sharedPreferences.getInt("score", 0)
    }
}