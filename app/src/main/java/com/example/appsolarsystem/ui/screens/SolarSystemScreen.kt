package com.example.appsolarsystem.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.appsolarsystem.R
import com.example.appsolarsystem.component.PlanetCard
import com.example.appsolarsystem.model.Planet
import com.example.appsolarsystem.ui.views.PlanetViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SolarSystemScreen(planetViewModel: PlanetViewModel = viewModel(factory = PlanetViewModel.Factory),  onNextButtonClicked: (Planet) -> Unit) {
    val planets by planetViewModel.planets.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            WelcomeMessage()
        }

        itemsIndexed(planets) {index,  planet ->
            PlanetCard(planet, index){
                onNextButtonClicked(planet)
                planetViewModel.selectPlanet(planet)
            }
            Spacer(modifier = Modifier.height(156.dp))
        }
        item {
            GlideImage(
                model = R.drawable.astro_goodbye,
                contentDescription = null,
                modifier = Modifier
                    .size(500.dp),

                )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WelcomeMessage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Explore Our Solar System",
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Embark on a journey through the cosmos and discover the wonders of our solar system. Unveil the mysteries of distant planets, their unique landscapes, and the fascinating stories they hold.",

                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(10.dp))

            GlideImage(
                model = R.drawable.rocket_home ,
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp),

                )

            Image(
                painter = painterResource(id = R.drawable.baseline_keyboard_double_arrow_down_24),
                contentDescription = null,
                modifier = Modifier
                    .size(280.dp)
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
