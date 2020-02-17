package com.rizalfahlepi.footballleague.fragment.league

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.rizalfahlepi.footballleague.R
import com.rizalfahlepi.footballleague.activity.detailleague.DetailLeagueActivity
import com.rizalfahlepi.footballleague.adapter.LeagueRecyclerViewAdapter
import com.rizalfahlepi.footballleague.model.League
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class LeagueFragment : Fragment(), AnkoComponent<Context> {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        constraintLayout {
            lparams(matchParent, matchParent)

            recyclerView {
                padding = dip(16)
                layoutManager = GridLayoutManager(context, 2)
                adapter = LeagueRecyclerViewAdapter(context, initData(context))
                (adapter as LeagueRecyclerViewAdapter).setOnItemClickCallback(object: LeagueRecyclerViewAdapter.OnItemClickCallback {
                    override fun onItemClicked(league: League) {
                        startActivity<DetailLeagueActivity>("league" to league)
                    }
                })
            }.lparams {
                width = dip(0)
                height = dip(0)
                startToStart = ConstraintSet.PARENT_ID
                topToTop = ConstraintSet.PARENT_ID
                endToEnd = ConstraintSet.PARENT_ID
                bottomToBottom = ConstraintSet.PARENT_ID
            }
        }
    }

    private fun initData(context: Context): ArrayList<League> {
        val dataFlagCountry = context.resources.obtainTypedArray(R.array.data_league_flag_country)
        val dataLogo = context.resources.obtainTypedArray(R.array.data_league_logo)
        val dataID = context.resources.getStringArray(R.array.data_id_league)
        val dataTitle = context.resources.getStringArray(R.array.data_title_league)
        val dataDescription = context.resources.getStringArray(R.array.data_description_league)

        val leagues = ArrayList<League>()
        for (position in dataTitle.indices) {
            val league = League(
                dataFlagCountry.getResourceId(position, -1),
                dataLogo.getResourceId(position, -1),
                dataID[position],
                dataTitle[position],
                dataDescription[position]
            )

            leagues.add(league)
        }

        dataFlagCountry.recycle()
        dataLogo.recycle()

        return leagues
    }
}
