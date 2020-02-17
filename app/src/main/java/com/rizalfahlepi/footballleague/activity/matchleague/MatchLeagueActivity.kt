package com.rizalfahlepi.footballleague.activity.matchleague

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.rizalfahlepi.footballleague.R
import com.rizalfahlepi.footballleague.activity.search.SearchMatchLeagueActivity
import com.rizalfahlepi.footballleague.adapter.MatchViewPagerAdapter
import com.rizalfahlepi.footballleague.api.ApiRepository
import com.rizalfahlepi.footballleague.model.response.Event
import com.rizalfahlepi.footballleague.model.response.League
import kotlinx.android.synthetic.main.activity_match_league.*

class MatchLeagueActivity : AppCompatActivity(), MatchLeagueView {

    private var isDoneLastMatchLeague = false
    private var isDoneNextMatchLeague = false
    private var listLastMatchLeague = ArrayList<Event>()
    private var listNextMatchLeague = ArrayList<Event>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_league)

        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolBar.setNavigationOnClickListener { finish() }

        val presenter = MatchLeaguePresenter(this, ApiRepository(), Gson())

        if (intent.hasExtra("idLeague")){
            presenter.getDetailsLeague(intent.getStringExtra("idLeague"))
            presenter.getListLastMatch(intent.getStringExtra("idLeague"))
            presenter.getListNextMatch(intent.getStringExtra("idLeague"))
        }
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

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showDetailsLeague(data: List<League>) {
        Glide.with(this).load(data[0].strBadge ?: "")
            .placeholder(R.drawable.ic_launcher_foreground)
            .dontAnimate().into(imageViewLogo)
        textViewValueID.text = data[0].idLeague ?: "-"
        textViewValueName.text = data[0].strLeague ?: "-"
        textViewValueSport.text = data[0].strSport ?: "-"
        textViewValueFormedYear.text = data[0].intFormedYear ?: "-"
        textViewValueDateFirstEvent.text = data[0].dateFirstEvent ?: "-"
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
}
