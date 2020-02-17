package com.rizalfahlepi.footballleague.activity.teamleague

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.rizalfahlepi.footballleague.R
import com.rizalfahlepi.footballleague.activity.detailteam.DetailTeamActivity
import com.rizalfahlepi.footballleague.activity.search.SearchMatchLeagueActivity
import com.rizalfahlepi.footballleague.adapter.TeamRecyclerViewAdapter
import com.rizalfahlepi.footballleague.api.ApiRepository
import com.rizalfahlepi.footballleague.model.response.Team
import kotlinx.android.synthetic.main.activity_team_league.*

class TeamLeagueActivity : AppCompatActivity(), TeamLeagueView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_league)

        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolBar.setNavigationOnClickListener { finish() }

        val presenter = TeamLeaguePresenter(this, ApiRepository(), Gson())

        if (intent.hasExtra("idLeague")){
            presenter.getListTeam(intent.getStringExtra("idLeague"))
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

    override fun showListTeamLeague(data: List<Team>) {
        if (data.isNotEmpty()){
            recyclerViewTeam.visibility = View.VISIBLE
            recyclerViewTeam.setHasFixedSize(true)
            recyclerViewTeam.layoutManager = LinearLayoutManager(this)
            val adapter = TeamRecyclerViewAdapter(data as ArrayList<Team>)
            recyclerViewTeam.adapter = adapter

            adapter.setOnItemClickCallback(object: TeamRecyclerViewAdapter.OnItemClickCallback {
                override fun onItemClicked(team: Team) {
                    startActivity(Intent(this@TeamLeagueActivity, DetailTeamActivity::class.java)
                        .putExtra("idTeam", team.idTeam))
                }
            })

        }else{
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.text_notification))
                .setMessage(getString(R.string.text_team_data_not_found))
                .setPositiveButton(getString(R.string.text_okay)){ _, _ -> finish() }.create().show()
        }
    }
}
