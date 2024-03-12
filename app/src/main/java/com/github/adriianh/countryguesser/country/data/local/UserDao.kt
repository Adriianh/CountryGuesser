package com.github.adriianh.countryguesser.country.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.github.adriianh.countryguesser.country.domain.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT score FROM user WHERE id = :userId")
    suspend fun getScore(userId: Int): Int?

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserById(id: Int): LiveData<User>

    @Query("SELECT * FROM user WHERE username = :name")
    fun getUserByName(name: String): LiveData<User>

    @Query("SELECT * FROM user")
    fun getAllUsers(): LiveData<List<User>>
}