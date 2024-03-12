package com.github.adriianh.countryguesser.country.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.adriianh.countryguesser.country.domain.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}