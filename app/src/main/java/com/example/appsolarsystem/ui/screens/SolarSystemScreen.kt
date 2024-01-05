package com.example.appsolarsystem.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.appsolarsystem.R
import com.example.appsolarsystem.component.PlanetCard
import com.example.appsolarsystem.model.Planet
import com.example.appsolarsystem.ui.views.PlanetViewModel



/**
 *
 * Displays the solar system screen using Jetpack Compose.
 *
 * @param planetViewModel The ViewModel responsible for managing planet data.
 * @param onNextButtonClicked Callback to be invoked when the next button is clicked on a planet card.
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SolarSystemScreen(
    planetViewModel: PlanetViewModel = viewModel(factory = PlanetViewModel.Factory),
    onNextButtonClicked: (Planet) -> Unit
) {
    val planets by planetViewModel.planets.collectAsState()

    LazyColumn(
        modifier = Modifier
            .testTag("scrolldown")
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        item {
            WelcomeMessage()
        }

        items(planets) { planet ->
            PlanetCard(planet) {
               planetViewModel.selectPlanet(planet)
                onNextButtonClicked(planet)
            }
            Spacer(modifier = Modifier.height(100.dp))
        }

        item {
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

/**
 * Displays a welcome message at the top of the solar system screen.
 */
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
                text = stringResource(R.string.title),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.intro),
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(10.dp))
            GlideImage(
                model = R.drawable.rocket_home,
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

/**
 * Displays the Voyager One animation with a satellite image.
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun VoyagerOneAnimation() {

    val animatable = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
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
