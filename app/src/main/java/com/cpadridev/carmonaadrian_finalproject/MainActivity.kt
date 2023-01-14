package com.cpadridev.carmonaadrian_finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.cpadridev.carmonaadrian_finalproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_nav_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        drawerLayout = binding.drawerLayout

        binding.navView.setNavigationItemSelectedListener {
            var fragmentTransaction = false
            lateinit var fragment: Fragment

            when(it.itemId){
                R.id.menuNumbers -> {
                    fragment = WordFragment("numbers")
                    fragmentTransaction = true
                }
                R.id.menuDays -> {
                    fragment = WordFragment("days")
                    fragmentTransaction = true
                }
                R.id.menuColors -> {
                    fragment = WordFragment("colors")
                    fragmentTransaction = true
                }
                R.id.menuFavorites -> {
                    fragment = FavoriteFragment()
                    fragmentTransaction = true
                }
            }

            if(fragmentTransaction) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.contentFrame, fragment)
                    .addToBackStack(null)
                    .commit()

                it.isChecked = true

                title = it.title
            }

            drawerLayout.closeDrawer(GravityCompat.START)

            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            if(drawerLayout.isDrawerOpen(GravityCompat.START))
                drawerLayout.closeDrawer(GravityCompat.START)
            else
                drawerLayout.openDrawer(GravityCompat.START)

            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}