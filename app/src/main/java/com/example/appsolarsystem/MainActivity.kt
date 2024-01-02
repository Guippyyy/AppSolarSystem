package com.example.appsolarsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.appsolarsystem.navigation.AppNavigation
import com.example.appsolarsystem.ui.SolarSystemApp
import com.example.appsolarsystem.ui.screens.SolarSystemScreen
import com.example.appsolarsystem.ui.theme.AppSolarSystemTheme


/**
 * The main activity of the Solar System app.
 */
class MainActivity : ComponentActivity() {

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     * this Bundle contains the data it most recently supplied in [onSaveInstanceState].
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppSolarSystemTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    SolarSystemApp()
                }
            }
        }

        // Ensure that the status bar color follows the app's theme.
        WindowCompat.setDecorFitsSystemWindows(window, true)
    }
}
