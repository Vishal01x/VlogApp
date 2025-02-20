package com.exa.android.vlogassignment.presentation.VlogScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.exa.android.vlogassignment.data.local.VlogEntity
import com.exa.android.vlogassignment.data.mappers.toVlog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class VlogViewModel @Inject constructor(
    pager: Pager<Int, VlogEntity>
): ViewModel() {

    val vlogPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toVlog() }
        }
        .cachedIn(viewModelScope)
}