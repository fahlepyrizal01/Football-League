package com.rizalfahlepi.footballleague.activity.search

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.rizalfahlepi.footballleague.R
import com.rizalfahlepi.footballleague.activity.detailmatchleague.DetailMatchLeagueActivity
import com.rizalfahlepi.footballleague.activity.detailteam.DetailTeamActivity
import com.rizalfahlepi.footballleague.adapter.MatchRecyclerViewAdapter
import com.rizalfahlepi.footballleague.adapter.TeamRecyclerViewAdapter
import com.rizalfahlepi.footballleague.api.ApiRepository
import com.rizalfahlepi.footballleague.model.response.Event
import com.rizalfahlepi.footballleague.model.response.Team
import kotlinx.android.synthetic.main.activity_search_match_league.*


class SearchMatchLeagueActivity : AppCompatActivity(), View.OnClickListener, SearchMatchLeagueView {

    private lateinit var presenter: SearchMatchLeaguePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match_league)

        searchView.isIconifiedByDefault = false
        searchView.requestFocus()

        imageViewBack.setOnClickListener(this)
        cardViewSearch.setOnClickListener(this)
        textViewChooseTypeSearch.setOnClickListener(this)

        presenter = SearchMatchLeaguePresenter(this, ApiRepository(), Gson())

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean = false

            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.replace(" ", "").isNotEmpty()){
                    when(textViewChooseTypeSearch.text){
                        getString(R.string.text_match) -> {
                            presenter.getListMatch(query)
                        }

                        getString(R.string.text_team) -> {
                            presenter.getListTeam(query)
                        }
                    }
                }
                return false
            }
        })
    }

    override fun onClick(p0: View?) {
        when(p0){
            imageViewBack -> {
                finish()
            }

            cardViewSearch -> {
                searchView.requestFocus()
            }

            textViewChooseTypeSearch -> {
                val arrayType = arrayOf(getString(R.string.text_match), getString(R.string.text_team))
                AlertDialog.Builder(this).setTitle(getString(R.string.text_select_search_type))
                    .setItems(arrayType) { _, position ->
                        textViewChooseTypeSearch.text = arrayType[position]
                        if (searchView.query.toString().replace(" ", "").isNotEmpty()){
                            when(textViewChooseTypeSearch.text){
                                getString(R.string.text_match) -> {
                                    presenter.getListMatch(searchView.query.toString())
                                }

                                getString(R.string.text_team) -> {
                                    presenter.getListTeam(searchView.query.toString())
                                }
                            }
                        }
                    }
                    .setNegativeButton(getString(R.string.text_close)){ _, _ ->}
                    .create().show()
            }
        }
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showListMatchLeague(data: List<Event>) {
        with((data as ArrayList<Event>).iterator()) {
            forEach {
                if (it.strSport != getString(R.string.text_soccer)) {
                    remove()
                }
            }
        }

        if (data.isNotEmpty()){
            textViewSearchNotFound.visibility = View.GONE
            recyclerViewMatch.visibility = View.VISIBLE
            recyclerViewMatch.setHasFixedSize(true)
            recyclerViewMatch.layoutManager = LinearLayoutManager(this)
            val adapter = MatchRecyclerViewAdapter(data)
            recyclerViewMatch.adapter = adapter

            adapter.setOnItemClickCallback(object: MatchRecyclerViewAdapter.OnItemClickCallback {
                override fun onItemClicked(idEvent: String, position: Int) {
                    startActivity(Intent(this@SearchMatchLeagueActivity, DetailMatchLeagueActivity::class.java)
                        .putExtra("idEvent", idEvent)
                        .putExtra("position", position.toString()))
                }
            })
        }else{
            textViewSearchNotFound.visibility = View.VISIBLE
            recyclerViewMatch.visibility = View.GONE
        }
    }

    override fun showListTeamLeague(data: List<Team>) {
        with((data as ArrayList<Team>).iterator()) {
            forEach {
                if (it.strSport != getString(R.string.text_soccer)) {
                    remove()
                }
            }
        }

        if (data.isNotEmpty()){
            textViewSearchNotFound.visibility = View.GONE
            recyclerViewMatch.visibility = View.VISIBLE
            recyclerViewMatch.setHasFixedSize(true)
            recyclerViewMatch.layoutManager = LinearLayoutManager(this)
            val adapter = TeamRecyclerViewAdapter(data)
            recyclerViewMatch.adapter = adapter

            adapter.setOnItemClickCallback(object: TeamRecyclerViewAdapter.OnItemClickCallback {
                override fun onItemClicked(team: Team) {
                    startActivity(Intent(this@SearchMatchLeagueActivity, DetailTeamActivity::class.java)
                        .putExtra("idTeam", team.idTeam))
                }
            })

        }else{
            textViewSearchNotFound.visibility = View.VISIBLE
            recyclerViewMatch.visibility = View.GONE
        }
    }


}
