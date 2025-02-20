package com.exa.android.vlogassignment.presentation.VlogScreen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.exa.android.vlogassignment.domain.Vlog
import com.exa.android.vlogassignment.presentation.VlogScreen.component.VlogItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VlogScreen(
    vlogs: LazyPagingItems<Vlog>,
    onClick: (String?) -> Unit
) {
    val context = LocalContext.current

    // Handle errors
    LaunchedEffect(vlogs.loadState) {
        val errorState = vlogs.loadState.refresh as? LoadState.Error
        errorState?.let {
            Toast.makeText(
                context,
                "Error: ${it.error.localizedMessage ?: "Unknown error"}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Scaffold(
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                vlogs.loadState.refresh is LoadState.Loading -> LoadingIndicator()
                vlogs.itemCount == 0 && vlogs.loadState.refresh is LoadState.NotLoading -> {
                    Toast.makeText(context, "No data available", Toast.LENGTH_SHORT).show()
                }
                else -> VlogList(vlogs, onClick)
            }
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun VlogList(vlogs: LazyPagingItems<Vlog>, onClick: (String?) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            Text("VlogApp", style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
        item{
            Spacer(Modifier.height(8.dp))
        }


        items(vlogs.itemSnapshotList.items) { vlog ->
            vlog?.let {
                VlogItem(
                    vlog = it,
                    modifier = Modifier.fillMaxWidth()
                ) { url -> onClick(url) }
            }
        }

        item {
            if (vlogs.loadState.append is LoadState.Loading) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
        }
    }
}
