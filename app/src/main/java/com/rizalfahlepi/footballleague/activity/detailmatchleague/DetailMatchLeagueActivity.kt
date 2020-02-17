package com.rizalfahlepi.footballleague.activity.detailmatchleague

import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.rizalfahlepi.footballleague.R
import com.rizalfahlepi.footballleague.activity.search.SearchMatchLeagueActivity
import com.rizalfahlepi.footballleague.api.ApiRepository
import com.rizalfahlepi.footballleague.db.FavoriteMatch
import com.rizalfahlepi.footballleague.db.MyDatabaseOpenHelper.Companion.database
import com.rizalfahlepi.footballleague.model.response.Event
import com.rizalfahlepi.footballleague.model.response.Team
import kotlinx.android.synthetic.main.activity_detail_match_league.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.text.SimpleDateFormat
import java.util.*

class DetailMatchLeagueActivity : AppCompatActivity(), DetailMatchLeagueView {

    private lateinit var event: Event
    private lateinit var presenter: DetailMatchLeaguePresenter
    private var idEvent: String?  = ""
    private var isLastMatch: String? = ""
    private var isFavorite = false
    private var menuItem: Menu? = null
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    private val simpleDateFormatView = SimpleDateFormat("yyyy-MM-dd HH:mm:ss ZZZZZ", Locale.getDefault())
    private var date: Date? = null
    private var league = ""
    private var season = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match_league)

        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolBar.setNavigationOnClickListener { finish() }

        presenter = DetailMatchLeaguePresenter(this, ApiRepository(), Gson())


        if (intent.hasExtra("idEvent") && intent.hasExtra("position")){
            idEvent = intent.getStringExtra("idEvent")
            isLastMatch = intent.getStringExtra("position")
            favoriteState()
            presenter.getDetailsMatchLeague(idEvent)
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

    override fun showDetailsMatchLeague(data: List<Event>) {
        event = data[0]
        presenter.getDetailsTeamHomeLeague(data[0].idHomeTeam)
        presenter.getDetailsTeamAwayLeague(data[0].idAwayTeam)

        textViewValueTeamHome.text = data[0].strHomeTeam ?: "-"
        textViewValueTeamAway.text = data[0].strAwayTeam ?: "-"
        textViewValueScoreHome.text = data[0].intHomeScore ?: "0"
        textViewValueScoreAway.text = data[0].intAwayScore ?: "0"
        textViewValueSport.text = data[0].strSport ?: "-"
        league = "${data[0].strLeague}(${data[0].idLeague})"
        textViewValueLeagueNameID.text = league
        season = "${getString(R.string.text_season)} ${data[0].strSeason}"
        textViewValueSeason.text = season
        textViewValueDate.text = try {
            simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
            date = simpleDateFormat.parse("${data[0].dateEvent}T${data[0].strTime}")
            simpleDateFormatView.timeZone = TimeZone.getDefault()
            simpleDateFormatView.format(date ?: Date())
        }catch (e: Exception){
            "-"
        }
        textViewValueIDHome.text = data[0].idHomeTeam ?: "-"
        textViewValueIDAway.text = data[0].idAwayTeam ?: "-"
        textViewValueNameHome.text = data[0].strHomeTeam ?: "-"
        textViewValueNameAway.text = data[0].strAwayTeam ?: "-"
        textViewValueScoreHomeBody.text = data[0].intHomeScore ?: "-"
        textViewValueScoreAwayBody.text = data[0].intAwayScore ?: "-"
        textViewValueFormationHome.text = data[0].strHomeFormation ?: "-"
        textViewValueFormationAway.text = data[0].strAwayFormation ?: "-"
        textViewValueShotHome.text = if (data[0].intHomeShots != null) data[0].intHomeShots.toString() else "-"
        textViewValueShotAway.text = if (data[0].intAwayShots != null) data[0].intAwayShots.toString() else "-"
        textViewValueYellowCardHome.text = if (data[0].strHomeYellowCards == null || data[0].strHomeYellowCards?.replace(" ", "")?.isEmpty() == true) "-" else data[0].strHomeYellowCards
        textViewValueYellowCardAway.text = if (data[0].strAwayYellowCards == null || data[0].strAwayYellowCards?.replace(" ", "")?.isEmpty() == true) "-" else data[0].strAwayYellowCards
        textViewValueRedCardHome.text = if (data[0].strHomeRedCards == null || data[0].strHomeRedCards?.replace(" ", "")?.isEmpty() == true) "-" else data[0].strHomeRedCards
        textViewValueRedCardAway.text = if (data[0].strAwayRedCards == null || data[0].strAwayRedCards?.replace(" ", "")?.isEmpty() == true) "-" else data[0].strAwayRedCards
        textViewValueGoalsHome.text = data[0].strHomeGoalDetails ?: "-"
        textViewValueGoalsAway.text = data[0].strAwayGoalDetails ?: "-"
    }

    override fun showDetailsTeamHomeLeague(data: List<Team>) {
        Glide.with(this).load(data[0].strTeamBadge).into(imageViewLogoTeamHome)
    }

    override fun showDetailsTeamAwayLeague(data: List<Team>) {
        Glide.with(this).load(data[0].strTeamBadge).into(imageViewLogoTeamAway)
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
                .whereArgs("(EVENT_ID = {id})",
                    "id" to (idEvent ?: "")
                )
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(FavoriteMatch.TABLE_FAVORITE_MATCH,
                    FavoriteMatch.EVENT_ID to event.idEvent,
                    FavoriteMatch.HOME_TEAM_NAME to event.strHomeTeam,
                    FavoriteMatch.AWAY_TEAM_NAME to event.strAwayTeam,
                    FavoriteMatch.HOME_TEAM_SCORE to event.intHomeScore,
                    FavoriteMatch.AWAY_TEAM_SCORE to event.intAwayScore,
                    FavoriteMatch.DATE to event.dateEvent,
                    FavoriteMatch.TIME to event.strTime,
                    FavoriteMatch.IS_LAST_MATCH to isLastMatch)
            }
            Toast.makeText(this, getString(R.string.text_added_to_favorite), Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteMatch.TABLE_FAVORITE_MATCH, "(EVENT_ID = {id})",
                    "id" to (idEvent ?: ""))
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
