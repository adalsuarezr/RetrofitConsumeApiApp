package com.example.retrofitconsumeapiapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import kotlin.random.Random.Default.nextInt

@Composable
fun MainScreen(navController: NavController, viewModel: MyViewModel) {
    val dogBreed: String by viewModel.dogBreed.observeAsState(initial = "")
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MyGoBackButton(Modifier, viewModel)
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
fun MyGoBackButton(modifier: Modifier, viewModel: MyViewModel) {
    var context = LocalContext.current
    val dogList: DogImagesResponse by viewModel.dogImageList.observeAsState(
        initial = DogImagesResponse(
            "",
            emptyList()
        )
    )

    if (!dogList.images.isEmpty()) {
        Card(
            modifier = modifier.padding(start = 16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            IconButton(
                onClick = { viewModel.onGoBackPress() },
                modifier.size(64.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = ""
                )
            }
        }
    }
}

@Composable
fun MySearchButton(modifier: Modifier, viewModel: MyViewModel) {
    var context = LocalContext.current

    Card(
        modifier = modifier.padding(start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        IconButton(
            onClick = { viewModel.getDogsByBreed(context) },
            modifier.size(64.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = ""
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyInputTextField(modifier: Modifier, dogBreed: String, onTextChanged: (String) -> Unit) {
    Card(
        modifier = modifier.padding(start = 16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        TextField(
            value = dogBreed,
            onValueChange = { onTextChanged(it) },
            placeholder = { Text(text = "Introduce Breed", textAlign = TextAlign.Center) },
            maxLines = 1,
            singleLine = true,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),

            )
    }
}

@Composable
fun MyRecyclerView(viewModel: MyViewModel) {
    var context = LocalContext.current

    val breedList: DogBreedsResponse by viewModel.dogBreedsList.observeAsState(
        initial = DogBreedsResponse(
            emptyMap<String, List<String>>(),
            ""
        )
    )

    val dogList: DogImagesResponse by viewModel.dogImageList.observeAsState(
        initial = DogImagesResponse(
            "",
            emptyList()
        )
    )

    viewModel.getDogBreedsList(context)

    if (dogList.images.isEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // dos columnas
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(breedList.message.keys.toList()) { key ->
                val random0 = nextInt(0, 360).toFloat()
                val random1 = nextInt(30, 100).toFloat() / 100
                val random2 = nextInt(30, 90).toFloat() / 100
                val randomColor = Color.hsv(random0, random1, random2)
                OutlinedButton(
                    onClick = {
                        viewModel.onDogBreedChanged(key)
                        viewModel.getDogsByBreed(context)
                    },
                    Modifier.padding(4.dp),
                    border = BorderStroke(2.dp, color = randomColor),
                    shape = RoundedCornerShape(50), // = 50% percent
                    // or shape = CircleShape
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = randomColor)
                ) {
                    Text(key)
                }
            }
        }
    } else {
        LazyColumn(Modifier.fillMaxSize()) {
            items(dogList.images) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .padding(8.dp)
                ) {
                    AsyncImage(
                        model = it,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}