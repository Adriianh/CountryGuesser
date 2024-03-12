package com.github.adriianh.countryguesser.di

import android.app.Application
import androidx.room.Room
import com.github.adriianh.countryguesser.country.data.local.UserDatabase
import com.github.adriianh.countryguesser.util.Constant.USER_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideUserDatabase(application: Application): UserDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = UserDatabase::class.java,
            name = USER_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase) = userDatabase.userDao
}