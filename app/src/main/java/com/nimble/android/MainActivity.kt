package com.nimble.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.nimble.android.preference.AppSharedPreferences
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        if (navHost != null) {
            val navController = navHost.navController
            val navInflater = navController.navInflater
            val graph = navInflater.inflate(R.navigation.main_nav_graph)
            val destinationId = if(AppSharedPreferences.getAccessToken().isNullOrEmpty()){
                R.id.loginFragment
            } else {
                R.id.homeFragment
            }
            graph.setStartDestination(destinationId)
            navController.setGraph(graph, null)
        }

    }

}