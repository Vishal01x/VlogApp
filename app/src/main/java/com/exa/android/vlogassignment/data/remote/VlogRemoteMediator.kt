package com.exa.android.vlogassignment.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.exa.android.vlogassignment.data.local.VlogDatabase
import com.exa.android.vlogassignment.data.local.VlogEntity
import com.exa.android.vlogassignment.data.mappers.toVlogEntity
import com.exa.android.vlogassignment.data.remote.api.VlogApi
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class VlogRemoteMediator(
    private val vlogDb: VlogDatabase,
    private val vlogApi: VlogApi
): RemoteMediator<Int, VlogEntity>() {

    @ExperimentalPagingApi
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, VlogEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val vlogs = vlogApi.getVlogs(
                page = loadKey,
                pageCount = state.config.pageSize
            )

            vlogDb.withTransaction { // for making it atomic so either both completed or nothing
                if(loadType == LoadType.REFRESH) {
                    vlogDb.dao.clearAll()
                }
                val vlogEntities = vlogs.map { it.toVlogEntity() }
                vlogDb.dao.upsertAll(vlogEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = vlogs.isEmpty()
            )
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}