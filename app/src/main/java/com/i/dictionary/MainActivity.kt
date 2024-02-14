package com.i.dictionary

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.i.dictionary.feature.presentation.WordItem
import com.i.dictionary.feature.presentation.WordViewModel
import com.i.dictionary.ui.theme.IDictionaryTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IDictionaryTheme {

                val viewModel: WordViewModel = hiltViewModel()
                val state = viewModel.state.value
                val snackBarHostState = remember { SnackbarHostState() }

                LaunchedEffect(key1 = true) {
                    viewModel.eventFlow.collectLatest {
                        when (it) {
                            is WordViewModel.UIEvent.ShowSnackBar -> snackBarHostState.showSnackbar(message = it.message)
                        }
                    }
                }

                Scaffold(
                    snackbarHost = { SnackbarHost(snackBarHostState) }
                ) {

                    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            OutlinedTextField(
                                value = viewModel.searchQuery.value,
                                onValueChange = { viewModel.onSearch(it) },
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = { Text(text = "Search..") },
                                singleLine = true,
                                maxLines = 1
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                                items(state.wordItems.size) { index ->
                                    val word = state.wordItems[index]
                                    if (index > 0)
                                        Spacer(modifier = Modifier.height(8.dp))
                                    WordItem(word = word)
                                    if (index < state.wordItems.size - 1) {
                                        Divider()
                                    }
                                }
                            }
                        }
                        if (state.isLoading) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }

            }
        }
    }
}