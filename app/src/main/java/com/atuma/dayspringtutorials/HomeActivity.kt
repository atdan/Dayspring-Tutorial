package com.atuma.dayspringtutorials

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.atuma.dayspringtutorials.util.UserSession
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var prefManager: UserSession? = null

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        if(auth.currentUser == null){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }else{
//            Toast.makeText(this, "Already logged in", Toast.LENGTH_LONG).show()
        }
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        prefManager = UserSession(this)
        PreferenceManager.getDefaultSharedPreferences(this).apply {
            //check to see if to go to home or login
            if (getBoolean(FIRST_TIME, true)){
                PreferenceManager.getDefaultSharedPreferences(this@HomeActivity).edit().apply(){
                    putBoolean(FIRST_TIME, false)
                    apply()
                }
                startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        navView.setOnClickListener { item ->
            when(item.id){
                R.id.nav_logout -> {
                    logoutUser()
                }
            }
        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_leaderboard, R.id.nav_forum,
                R.id.nav_campus_news, R.id.nav_accomodation, R.id.nav_subscription
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        prefManager!!.isFirstTimeLaunch = false


    }

    private fun logoutUser() {

        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    private fun launchCreateUserScreen() {
        Log.d(TAG, "Check isFirstTimeLaunch: ${prefManager?.isFirstTimeLaunch}")

        prefManager?.isFirstTimeLaunch = false
        Log.d(TAG, "Set isFirstTimeLaunch to false: ${prefManager?.isFirstTimeLaunch}")
        val i = Intent(this, LoginActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
    }

    override fun onResume() {
        super.onResume()

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun getSupportFragmentManager(): FragmentManager {
        return super.getSupportFragmentManager()

    }

    companion object{
        const val TAG = "HomeActivity"
        const val FIRST_TIME = "first-launch"
        const val LOGGED_IN = "logged-in"
    }
}
