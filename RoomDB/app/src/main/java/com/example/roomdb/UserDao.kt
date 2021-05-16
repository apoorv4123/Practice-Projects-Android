package com.example.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.util.*

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User) //we use suspend here to make use of coroutines

    @Insert
    fun insertAll(list: List<User>)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM User")
    suspend fun getAllUser(): List<User>

    @Query("Select * FROM User WHERE age >= :age")
    fun getUserWithAge(age: Int): List<User>
}