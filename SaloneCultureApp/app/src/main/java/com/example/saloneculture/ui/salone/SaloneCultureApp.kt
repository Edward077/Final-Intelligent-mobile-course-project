package com.example.saloneculture.ui.salone

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.saloneculture.R

private sealed class SaloneRoute(val route: String) {
    data object Home : SaloneRoute("home")
    data object Culture : SaloneRoute("culture")
    data object About : SaloneRoute("about")
}

private data class BottomItem(
    val route: String,
    val label: String,
    val icon: @Composable () -> Unit
)

@Composable
fun SaloneCultureApp() {
    val navController = rememberNavController()
    val bottomItems = listOf(
        BottomItem(SaloneRoute.Home.route, "Home") { Icon(Icons.Default.Home, contentDescription = "Home") },
        BottomItem(SaloneRoute.Culture.route, "Culture") { Icon(Icons.Default.Public, contentDescription = "Culture") },
        BottomItem(SaloneRoute.About.route, "About") { Icon(Icons.Default.Info, contentDescription = "About") },
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        topBar = { SaloneTopBar() },
        bottomBar = {
            NavigationBar {
                bottomItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = { navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        } },
                        icon = item.icon,
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { padding ->
        SaloneNavHost(navController = navController, modifier = Modifier.padding(padding))
    }
}

@Composable
private fun SaloneNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = SaloneRoute.Home.route,
        modifier = modifier.fillMaxSize()
    ) {
        composable(SaloneRoute.Home.route) { HomeScreen() }
        composable(SaloneRoute.Culture.route) { CultureScreen() }
        composable(SaloneRoute.About.route) { AboutScreen() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SaloneTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.sl_flag),
                    contentDescription = "Sierra Leone Flag",
                    modifier = Modifier.size(26.dp)
                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    )
}

private data class HomeCard(
    val title: String,
    val subtitle: String,
    val imageRes: Int
)

@Composable
private fun HomeScreen() {
    val cards = listOf(
        HomeCard("Traditional Music", "Explore the rhythms of Sierra Leone", R.drawable.img_music),
        HomeCard("Cultural Dance", "Energizing dance traditions", R.drawable.img_dance),
        HomeCard("Historic Heritage", "Discover Sierra Leone’s history", R.drawable.img_heritage),
        HomeCard("Local Cuisine", "Taste the flavors of Salone", R.drawable.img_cuisine)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Explore the Arts & Culture of\nSierra Leone",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        items(cards) { c ->
            ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = c.imageRes),
                        contentDescription = c.title,
                        modifier = Modifier
                            .width(140.dp)
                            .height(90.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                    ) {
                        Text(c.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(c.subtitle, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

@Composable
private fun CultureScreen() {
    val items = listOf(
        HomeCard("Traditional Music", "Explore the rhythms of Sierra Leone", R.drawable.img_music),
        HomeCard("Cultural Dance", "Energizing dance traditions", R.drawable.img_dance),
        HomeCard("Languages", "English, Krio, Mende and more", R.drawable.img_languages),
        HomeCard("Historic Heritage", "Discover Sierra Leone’s history", R.drawable.img_heritage)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Culture Options", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(items) { c ->
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column {
                        Image(
                            painter = painterResource(id = c.imageRes),
                            contentDescription = c.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                        )
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(c.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(c.subtitle, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AboutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("About", style = MaterialTheme.typography.headlineSmall)
        Text("Salone Culture App", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text("Developed by Edward David Thoronka", style = MaterialTheme.typography.bodyLarge)
        Text("This app showcases the arts and culture of Sierra Leone.", style = MaterialTheme.typography.bodyMedium)
    }
}
