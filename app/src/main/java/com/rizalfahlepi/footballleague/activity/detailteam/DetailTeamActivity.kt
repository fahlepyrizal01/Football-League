package com.rizalfahlepi.footballleague.activity.detailteam

import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.rizalfahlepi.footballleague.R
import com.rizalfahlepi.footballleague.activity.search.SearchMatchLeagueActivity
import com.rizalfahlepi.footballleague.adapter.MatchViewPagerAdapter
import com.rizalfahlepi.footballleague.api.ApiRepository
import com.rizalfahlepi.footballleague.db.FavoriteTeam
import com.rizalfahlepi.footballleague.db.MyDatabaseOpenHelper.Companion.database
import com.rizalfahlepi.footballleague.model.response.Event
import com.rizalfahlepi.footballleague.model.response.Team
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailTeamActivity : AppCompatActivity(), DetailTeamView {

    private var isDoneLastMatchLeague = false
    private var isDoneNextMatchLeague = false
    private var listLastMatchLeague = ArrayList<Event>()
    private var listNextMatchLeague = ArrayList<Event>()
    private var idTeam: String?  = ""
    private var isFavorite = false
    private lateinit var team: Team
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolBar.setNavigationOnClickListener { finish() }

        val presenter = DetailTeamPresenter(this, ApiRepository(), Gson())

        if (intent.hasExtra("idTeam")){
            idTeam = intent.getStringExtra("idTeam")
            favoriteState()
            presenter.getDetailsTeam(idTeam)
            presenter.getListLastMatch(idTeam)
            presenter.getListNextMatch(idTeam)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_search -> {
                startActivity(Intent(this, SearchMatchLeagueActivity::class.java))
                true
            }

            R.id.action_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showDetailsTeam(data: List<Team>) {
        team = data[0]

        Glide.with(this).load(data[0].strTeamBadge ?: "")
            .placeholder(R.drawable.ic_launcher_foreground)
            .dontAnimate().into(imageViewLogo)
        textViewValueID.text = data[0].idTeam ?: "-"
        textViewValueName.text = data[0].strTeam ?: "-"
        textViewValueStadium.text = data[0].strStadium ?: "-"
        textViewValueFormedYear.text = data[0].intFormedYear ?: "-"
        textViewValueLeague.text = data[0].strLeague ?: "-"
        textViewValueGender.text = data[0].strGender ?: "-"
        textViewValueCountry.text = data[0].strCountry ?: "-"
        textViewValueWebsite.text = data[0].strWebsite ?: "-"
        textViewValueFaceBook.text = data[0].strFacebook ?: "-"
        textViewValueTwitter.text = data[0].strTwitter ?: "-"
        textViewValueYoutube.text = data[0].strYoutube ?: "-"
    }

    override fun showListLastMatchLeague(data: List<Event>) {
        isDoneLastMatchLeague = true
        listLastMatchLeague = data as ArrayList<Event>
        checkIsDoneLoadData()
    }

    override fun showListNextMatchLeague(data: List<Event>) {
        isDoneNextMatchLeague = true
        listNextMatchLeague = data as ArrayList<Event>
        checkIsDoneLoadData()
    }

    private fun checkIsDoneLoadData(){
        if (isDoneLastMatchLeague && isDoneNextMatchLeague){
            val pagerAdapter = MatchViewPagerAdapter(this, listLastMatchLeague, listNextMatchLeague, supportFragmentManager)
            tabLayoutMatch.setupWithViewPager(viewpagerMatch)
            viewpagerMatch.adapter = pagerAdapter
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                .whereArgs("(TEAM_ID = {id})",
                    "id" to (idTeam ?: "")
                )
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                    FavoriteTeam.TEAM_ID to team.idTeam,
                    FavoriteTeam.TEAM_NAME to team.strTeam,
                    FavoriteTeam.TEAM_LOGO to team.strTeamBadge)
            }
            Toast.makeText(this, getString(R.string.text_added_to_favorite), Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})",
                    "id" to (idTeam ?: ""))
            }
            Toast.makeText(this, getString(R.string.text_remove_to_favorite), Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_added)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_add)
    }
}
