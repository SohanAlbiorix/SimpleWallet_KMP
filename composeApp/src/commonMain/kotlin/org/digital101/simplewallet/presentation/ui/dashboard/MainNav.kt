package org.digital101.simplewallet.presentation.ui.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.digital101.simplewallet.common.Context
import org.digital101.simplewallet.presentation.component.bottomBar
import org.digital101.simplewallet.presentation.navigation.BottomNavItem
import org.digital101.simplewallet.presentation.navigation.MainNavigation
import org.digital101.simplewallet.presentation.ui.dashboard.home.HomeScreen
import org.digital101.simplewallet.presentation.ui.dashboard.profile.ProfileScreen
import org.digital101.simplewallet.presentation.ui.dashboard.profile.viewmodel.ProfileViewModel
import org.koin.compose.koinInject

@Composable
fun MainNav(context: Context, logout: () -> Unit) {
    val navBottomBarController = rememberNavController()
    val profileViewModel: ProfileViewModel = koinInject()
    Scaffold(bottomBar = {
        bottomBar(navBottomBarController)
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                startDestination = BottomNavItem.Home.route,
                navController = navBottomBarController,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(route = BottomNavItem.Home.route) {
                    HomeScreen(navBottomBarController, context)
                }
                composable(route = BottomNavItem.Deposits.route) {
//                    WishlistNav()
                }
                composable(route = BottomNavItem.Finance.route) {
//                    CartNav()
                }
                composable(route = BottomNavItem.Card.route) {
//                    ProfileNav(logout = logout)
                }
                composable<MainNavigation.Profile> {
                    ProfileScreen(
                        viewModel = profileViewModel,
                        onBackClick = { navBottomBarController.popBackStack() }
                    )
                }
            }
        }

    }
}