package com.example.appsolarsystem.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.appsolarsystem.component.MoonCard
import com.example.appsolarsystem.component.PlanetCard
import com.example.appsolarsystem.model.Moon
import com.example.appsolarsystem.ui.views.MoonUiState
import com.example.appsolarsystem.ui.views.MoonViewModel
import com.example.appsolarsystem.ui.views.PlanetViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlanetIdScreen(planetID: String, planetName: String, planetImage: String, planetDiameter: String, planetMass: String, planetGravity: String, onNextButtonClicked: () -> Unit){

    val moonViewModel: MoonViewModel = viewModel(factory = MoonViewModel.Factory )


    // Use LaunchedEffect to trigger fetching moons when the planetId changes
    LaunchedEffect(planetID) {
        moonViewModel.getMoonsByPlanetId(3)
    }

    // Collect moons as a state
    val moons by  moonViewModel.moons.collectAsState()

    Log.d("moon", "$moons")


    LazyColumn(
        modifier = Modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = planetName,
                fontSize = 54.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            GlideImage(
                model = planetImage,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Text(
                text = "Diameter: " +planetDiameter,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Mass: " + planetMass,
                fontSize = 24.sp,
                fontWeight =  FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Gravity: " + planetGravity,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(100.dp))

        }
        if (moons.isNotEmpty()) {
            items(moons) { moon ->
                MoonCard(moon)
                Spacer(modifier = Modifier.height(16.dp))
            }
        } else {
            item {
                Text(
                    text = "This planet has no moons.",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
