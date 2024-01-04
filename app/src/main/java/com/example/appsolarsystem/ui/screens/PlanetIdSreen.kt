package com.example.appsolarsystem.ui.screens

import android.annotation.SuppressLint
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
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
fun PlanetIdScreen() {

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    val quickFactViewModel: QuickFactViewModel = viewModel(factory = QuickFactViewModel.Factory)
    val quickFact by quickFactViewModel.quickFacts.collectAsState()

    val planetInfoViewModel: PlanetInfoViewModel = viewModel(factory = PlanetInfoViewModel.Factory)
    val planetInfos by planetInfoViewModel.planetInfos.collectAsState()

    val moonViewModel: MoonViewModel = viewModel(factory = MoonViewModel.Factory)
    val moon by moonViewModel.moons.collectAsState()


    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Quick Facts", modifier = Modifier.testTag("facts")) },
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

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            if (!planetInfos.isNullOrEmpty()) {
                items(planetInfos) { planetInfo ->


                    planetInfo.intro?.let { it1 -> Text(text = it1, style = MaterialTheme.typography.headlineSmall) }
                    Spacer(modifier = Modifier.height(26.dp))
                    planetInfo.textplanet?.let { it1 -> Text(text = it1) }
                    Spacer(modifier = Modifier.height(26.dp))


                    if (!planetInfo.formation.isNullOrEmpty()){
                    Text(
                        text = stringResource(id = R.string.formation),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineLarge

                    )}

                    Spacer(modifier = Modifier.height(16.dp))
                    planetInfo.formation?.let { it1 -> Text(text = it1) }
                    Spacer(modifier = Modifier.height(26.dp))

                    if(!planetInfo.distance.isNullOrEmpty()){
                    Text(
                        text = stringResource(id = R.string.distance),
                        color = MaterialTheme.colorScheme.primary,

                        style = MaterialTheme.typography.headlineLarge
                    )}

                    Spacer(modifier = Modifier.height(16.dp))
                    planetInfo.distance?.let { it1 -> Text(text = it1) }
                    Spacer(modifier = Modifier.height(26.dp))

                    if(!planetInfo.orbit.isNullOrEmpty()){
                    Text(
                        text = stringResource(id = R.string.orbit),

                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary
                    )}

                    Spacer(modifier = Modifier.height(16.dp))
                    planetInfo.orbit?.let { it1 -> Text(text = it1) }
                    Spacer(modifier = Modifier.height(26.dp))
                }
            }
        }
    }
}