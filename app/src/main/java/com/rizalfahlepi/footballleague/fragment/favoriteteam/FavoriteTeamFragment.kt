package com.rizalfahlepi.footballleague.fragment.favoriteteam

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager

import com.rizalfahlepi.footballleague.R
import com.rizalfahlepi.footballleague.activity.detailteam.DetailTeamActivity
import com.rizalfahlepi.footballleague.adapter.TeamRecyclerViewAdapter
import com.rizalfahlepi.footballleague.db.FavoriteTeam
import com.rizalfahlepi.footballleague.db.MyDatabaseOpenHelper.Companion.database
import com.rizalfahlepi.footballleague.model.response.Team
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteTeamFragment : Fragment() {

    override fun onResume() {
        super.onResume()

        showFavorite()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }

    private fun showFavorite(){
        context?.database?.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favorites = result.parseList(classParser<FavoriteTeam>())
            val team =  ArrayList<Team>()
            for (favorite in favorites){
                team.add(Team(
                    idTeam = favorite.TEAM_ID,
                    strTeam = favorite.TEAM_NAME,
                    strTeamBadge = favorite.TEAM_LOGO)
                )
            }

            if (team.isEmpty()){
                AlertDialog.Builder(context as Activity)
                    .setTitle(getString(R.string.text_notification))
                    .setMessage(getString(R.string.text_you_havent_added_a_favorite_list_yet))
                    .setPositiveButton(getString(R.string.text_okay)){ _, _ -> }.create().show()
            }

            recyclerViewTeam.visibility = View.VISIBLE
            recyclerViewTeam.setHasFixedSize(true)
            recyclerViewTeam.layoutManager = LinearLayoutManager(context as Activity)
            val adapter = TeamRecyclerViewAdapter(team)
            recyclerViewTeam.adapter = adapter

            adapter.setOnItemClickCallback(object: TeamRecyclerViewAdapter.OnItemClickCallback {
                override fun onItemClicked(team: Team) {
                    startActivity(
                        Intent(context as Activity, DetailTeamActivity::class.java)
                            .putExtra("idTeam", team.idTeam))
                }
            })
        }
    }
}
