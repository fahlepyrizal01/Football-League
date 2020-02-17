package com.rizalfahlepi.footballleague.activity.tableteamleague

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.rizalfahlepi.footballleague.R
import com.rizalfahlepi.footballleague.activity.search.SearchMatchLeagueActivity
import com.rizalfahlepi.footballleague.adapter.TableRecyclerViewAdapter
import com.rizalfahlepi.footballleague.api.ApiRepository
import com.rizalfahlepi.footballleague.model.response.Table
import kotlinx.android.synthetic.main.activity_table_team_league.*

class TableTeamLeagueActivity : AppCompatActivity(), TableTeamLeagueView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_team_league)

        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolBar.setNavigationOnClickListener { finish() }

        val presenter = TableTeamLeaguePresenter(this, ApiRepository(), Gson())


        if (intent.hasExtra("idLeague")){
            presenter.getListTable(intent.getStringExtra("idLeague"))
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

    override fun showListTableLeague(data: List<Table>) {
        recyclerViewTable.visibility = View.VISIBLE
        recyclerViewTable.setHasFixedSize(true)
        recyclerViewTable.layoutManager = LinearLayoutManager(this)
        val adapter = TableRecyclerViewAdapter(data as ArrayList<Table>)
        recyclerViewTable.adapter = adapter
    }
}
