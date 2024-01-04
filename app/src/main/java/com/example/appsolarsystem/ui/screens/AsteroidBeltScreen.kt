package com.example.appsolarsystem.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.appsolarsystem.R


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AsteroidBeltScreen(){

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
                text = stringResource(id = R.string.drawerTheAsteroidBelt),
                modifier = Modifier.testTag(stringResource(id = R.string.drawerTheAsteroidBelt)),
                fontSize = 54.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            GlideImage(
                model = "https://nineplanets.org/wp-content/uploads/2019/10/asteroid-belt-diagram-965x1024.png" ,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )



            Text(
                text = stringResource(id = R.string.AsteroidBeltWhatTitle),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = stringResource(id = R.string.AsteroidBeltWhatContent),
            )
            Spacer(modifier = Modifier.height(26.dp))


            Text(
                text = stringResource(id = R.string.AsteroidBeltHowTitle),
                color = MaterialTheme.colorScheme.primary,

                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = stringResource(id = R.string.AsteroidBeltHowContent),
            )
        }
    }
}
