package com.example.appsolarsystem.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.appsolarsystem.R
import com.example.appsolarsystem.component.MoonCard
import com.example.appsolarsystem.component.QuickFactBottomSheet
import com.example.appsolarsystem.data.GlobalPlanet
import com.example.appsolarsystem.ui.views.MoonViewModel
import com.example.appsolarsystem.ui.views.PlanetInfoViewModel
import com.example.appsolarsystem.ui.views.QuickFactViewModel



/**
 *
 * Composable function for displaying information about a specific planet, including its moons and details.
 *
 * @param moonViewModel The ViewModel responsible for managing moon data.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PlanetIdScreen(moonViewModel: MoonViewModel = viewModel(factory = MoonViewModel.Factory)) {

    val planetInfoViewModel: PlanetInfoViewModel = viewModel(factory = PlanetInfoViewModel.Factory)
    val quickFactViewModel: QuickFactViewModel = viewModel(factory = QuickFactViewModel.Factory)
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    val moons by moonViewModel.moons.collectAsState()
    val planetInfos by planetInfoViewModel.planetInfos.collectAsState()
    val quickFact by quickFactViewModel.quickFacts.collectAsState()
    val moonviewModel: MoonViewModel = viewModel(factory = MoonViewModel.Factory)

    val moon by moonviewModel.moons.collectAsState()

    Log.d("moon", "${moons}")

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Quick Facts") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = {
                    showBottomSheet = true
                }
            )
        }
    ) {
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                LazyColumn()
                {
                    if (quickFact.isNotEmpty()) {

                        items(quickFact) { qf ->
                            QuickFactBottomSheet(qf)
                        }

                    }
                }
            }
        }


        LazyColumn(
            modifier = Modifier
                .clip(MaterialTheme.shapes.extraLarge)
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {

                Text(
                    text = GlobalPlanet.planet!!.name,
                    fontSize = 54.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                GlideImage(
                    model = GlobalPlanet.planet!!.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            if (moon.isNotEmpty()) {
                items(moon) { moon ->
                    MoonCard(moon)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            } else {
                item {
                    Text("no moon")
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            if (planetInfos.isNotEmpty()) {
                items(planetInfos) { planetInfo ->


                    Text(text = planetInfo.intro, style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(26.dp))
                    Text(text = planetInfo.textplanet)
                    Spacer(modifier = Modifier.height(26.dp))

                    Text(
                        text = stringResource(id = R.string.formation),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineLarge

                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = planetInfo.formation)
                    Spacer(modifier = Modifier.height(26.dp))

                    Text(
                        text = stringResource(id = R.string.distance),
                        color = MaterialTheme.colorScheme.primary,

                        style = MaterialTheme.typography.headlineLarge
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = planetInfo.distance)
                    Spacer(modifier = Modifier.height(26.dp))

                    Text(
                        text = stringResource(id = R.string.orbit),

                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = planetInfo.orbit)
                    Spacer(modifier = Modifier.height(26.dp))
                }
            }
        }
    }
}