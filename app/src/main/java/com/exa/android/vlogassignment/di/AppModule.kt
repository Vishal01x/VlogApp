package com.exa.android.vlogassignment.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.exa.android.vlogassignment.data.local.VlogDatabase
import com.exa.android.vlogassignment.data.local.VlogEntity
import com.exa.android.vlogassignment.data.remote.VlogRemoteMediator
import com.exa.android.vlogassignment.data.remote.api.VlogApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideVlogDatabase(@ApplicationContext context: Context): VlogDatabase {
        return Room.databaseBuilder(
            context,
            VlogDatabase::class.java,
            "beers.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideVlogApi(): VlogApi {
        return Retrofit.Builder()
            .baseUrl(VlogApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideVlogPager(vlogDb: VlogDatabase, vlogApi: VlogApi): Pager<Int, VlogEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = VlogRemoteMediator(
                vlogDb = vlogDb,
                vlogApi = vlogApi
            ),
            pagingSourceFactory = {
                vlogDb.dao.pagingSource()
            }
        )
    }
}