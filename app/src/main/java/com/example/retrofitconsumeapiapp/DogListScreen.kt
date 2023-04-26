package com.example.retrofitconsumeapiapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun DogListScreen(viewModel: MyViewModel) {
    val dogBreed: String by viewModel.dogBreed.observeAsState(initial = "")
    Column(Modifier.fillMaxSize().padding(10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().background(Color.LightGray),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MyInputTextField(
                Modifier.weight(1f),
                dogBreed
            ) { viewModel.onDogBreedChanged(it) }
            MySearchButton(Modifier, viewModel)
        }
        MyRecyclerView(viewModel)
    }
}

@Composable
fun MySearchButton(modifier: Modifier, viewModel: MyViewModel) {
    var context=LocalContext.current
    IconButton(
        onClick = { viewModel.getDogsByBreed(context) },
        modifier.size(54.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = "")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyInputTextField(modifier: Modifier, dogBreed: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = dogBreed,
        onValueChange = { onTextChanged(it) },
        placeholder = { Text(text = "Introduce Breed") },
        maxLines = 1,
        singleLine = true,
        modifier = modifier
    )
}

@Composable
fun MyRecyclerView(viewModel: MyViewModel) {

    val dogList: DogResponse by viewModel.dogList.observeAsState(
        initial = DogResponse(
            "",
            emptyList()
        )
    )

    LazyColumn(Modifier.fillMaxSize()) {
        items(dogList.images) {
            Box(modifier = Modifier.height(200.dp).padding(8.dp)) {
                AsyncImage(
                    model = it,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}