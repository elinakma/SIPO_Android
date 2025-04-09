package com.example.sipo_reka.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.currentBackStackEntryAsState

// Data class untuk item navigasi
data class BottomNavItem(
    val icon: ImageVector,
    val route: String
)

@Composable
fun BottomNavBar(
    navController: NavController,
    modifier: Modifier = Modifier
    ) {
    val navItems = listOf(
        BottomNavItem(Icons.Filled.Home, "dashboard"),
        BottomNavItem(Icons.Filled.Notifications, "notification"),
        BottomNavItem(Icons.Filled.Person, "profile")
    )

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    NavigationBar(
        modifier = modifier
            .width(300.dp)
            .height(65.dp) // Batasi tinggi navbar
            .clip(RoundedCornerShape(50.dp))
            .background(Color(0xFF1E4178)),
        tonalElevation = 5.dp,
        containerColor = Color(0xFF1E4178)
    ) {
        navItems.forEach { item ->
            val isSelected = item.route == currentRoute
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            // Optional: Hindari duplicate
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = if (isSelected) Color.White else Color.Transparent,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            modifier = Modifier.size(25.dp),
                            tint = if (isSelected) Color(0xFF1E4178) else Color.White
                        )
                    }
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                ),
                modifier = Modifier.padding(vertical = 0.dp)
            )
        }
    }
}
