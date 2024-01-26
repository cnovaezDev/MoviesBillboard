package cnovaez.dev.moviesbillboard.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cnovaez.dev.moviesbillboard.domain.models.Routes
import cnovaez.dev.moviesbillboard.ui.components.MovieFullDetails
import cnovaez.dev.moviesbillboard.ui.components.MoviesScreen
import cnovaez.dev.moviesbillboard.ui.theme.MoviesBillboardTheme
import cnovaez.dev.moviesbillboard.ui.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mainActivityViewModel: MainActivityViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigationController = rememberNavController()
            NavHost(
                navController = navigationController,
                startDestination = Routes.MoviesList.route
            ) {

                composable(Routes.MoviesList.route) {
                    MoviesScreen(mainActivityViewModel, navigationController, this@MainActivity)
                }
                composable(Routes.MovieFullDetails.route) {
                    MovieFullDetails(mainActivityViewModel, navHost = navigationController)
                }
            }

        }
    }
}
