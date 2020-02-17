package com.rizalfahlepi.footballleague.activity.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.rizalfahlepi.footballleague.R
import com.rizalfahlepi.footballleague.activity.search.SearchMatchLeagueActivity
import com.rizalfahlepi.footballleague.fragment.favoritematch.FavoriteMatchFragment
import com.rizalfahlepi.footballleague.fragment.favoriteteam.FavoriteTeamFragment
import com.rizalfahlepi.footballleague.fragment.league.LeagueFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolBar)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.leagues -> {
                    loadLeaguesFragment(savedInstanceState)
                }

                R.id.favoritesTeams -> {
                    loadFavoritesTeamFragment(savedInstanceState)
                }

                R.id.favoritesMatch -> {
                    loadFavoritesMatchFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.leagues
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_search) {
            startActivity(Intent(this, SearchMatchLeagueActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }


    private fun loadLeaguesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, LeagueFragment(), LeagueFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoritesTeamFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoriteTeamFragment(), FavoriteTeamFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoritesMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoriteMatchFragment(), FavoriteMatchFragment::class.java.simpleName)
                .commit()
        }
    }
}
