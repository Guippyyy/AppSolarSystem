package com.example.appsolarsystem.ui.screens

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
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

    Log.d("planetshere", "${planets}")
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        try{
            Log.d("planettest", "did i get here before message?")
        item {
            WelcomeMessage()
        }
            Log.d("planettest", "did i get here? after message")
        itemsIndexed(planets) {index,  planet ->
            Log.d("planettest", "did i get here ? in itemsindexrd")
            PlanetCard(planet, index){
                onNextButtonClicked(planet)
                planetViewModel.selectPlanet(planet)
            }
          //  Spacer(modifier = Modifier.height(156.dp))
        }}
        catch(e : Exception) {
            Log.d("planetNothere", "${e.message}")
            e.message
        }
        item{
            VoyagerOneAnimation()
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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun VoyagerOneAnimation() {
    var offset by remember { mutableStateOf(0f) }
    val animatable = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        // Animate the offset from left to right
        animatable.animateTo(
            targetValue = 4.8f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 2000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {



        GlideImage(
            model = R.drawable.satellite,
            contentDescription = "Voyager One",
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(translationX = animatable.value * 200)
        )
    }
}