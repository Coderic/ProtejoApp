package org.coderic.protective.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.coderic.protective.mobile.model.Routes
import org.coderic.protective.mobile.presentation.getStarted.GetStartedScreen
import org.coderic.protective.mobile.presentation.getStarted.GetStartedViewModel
import org.coderic.protective.mobile.presentation.login.LoginScreen
import org.coderic.protective.mobile.presentation.login.LoginViewModel
import org.coderic.protective.mobile.ui.theme.CocoAppTheme

class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CocoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface (
                    modifier = Modifier
                        .fillMaxSize(),
                    contentColor = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Routes.GetStarted.route
                    ) {
                        composable( Routes.InicioSesion.route ) {
                            val loginViewModel = LoginViewModel( this@StartActivity )
                            LoginScreen( loginViewModel )
                        }
                        composable( Routes.GetStarted.route ) {
                            val getStartedViewModel = GetStartedViewModel( navController )
                            GetStartedScreen( getStartedViewModel = getStartedViewModel )
                        }
                    }
                }
            }
        }
    }
}