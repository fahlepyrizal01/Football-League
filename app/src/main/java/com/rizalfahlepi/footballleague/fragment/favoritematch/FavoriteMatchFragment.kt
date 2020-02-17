package com.rizalfahlepi.footballleague.fragment.favoritematch

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.rizalfahlepi.footballleague.R
import com.rizalfahlepi.footballleague.adapter.MatchViewPagerAdapter
import com.rizalfahlepi.footballleague.db.FavoriteMatch
import com.rizalfahlepi.footballleague.db.MyDatabaseOpenHelper.Companion.database
import com.rizalfahlepi.footballleague.model.response.Event
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteMatchFragment : Fragment() {

    private var lastPosition = 0

    override fun onResume() {
        super.onResume()

        showFavorite()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }

    private fun showFavorite(){
        context?.database?.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
            val favorites = result.parseList(classParser<FavoriteMatch>())
            val lastMatch =  ArrayList<Event>()
            val nextMatch =  ArrayList<Event>()
            for (favorite in favorites){
                if (favorite.IS_LAST_MATCH == "0"){
                    lastMatch.add(Event(
                        idEvent = favorite.EVENT_ID,
                        strHomeTeam = favorite.HOME_TEAM_NAME,
                        strAwayTeam = favorite.AWAY_TEAM_NAME,
                        intHomeScore = favorite.HOME_TEAM_SCORE,
                        intAwayScore = favorite.AWAY_TEAM_SCORE,
                        dateEvent = favorite.DATE,
                        strTime = favorite.TIME))
                }else{
                    nextMatch.add(Event(
                        idEvent = favorite.EVENT_ID,
                        strHomeTeam = favorite.HOME_TEAM_NAME,
                        strAwayTeam = favorite.AWAY_TEAM_NAME,
                        intHomeScore = favorite.HOME_TEAM_SCORE,
                        intAwayScore = favorite.AWAY_TEAM_SCORE,
                        dateEvent = favorite.DATE,
                        strTime = favorite.TIME))
                }
            }

            if (lastMatch.isEmpty() && nextMatch.isEmpty()){
                AlertDialog.Builder(context as Activity)
                    .setTitle(getString(R.string.text_notification))
                    .setMessage(getString(R.string.text_you_havent_added_a_favorite_list_yet))
                    .setPositiveButton(getString(R.string.text_okay)){ _, _ -> }.create().show()
            }

            val pagerAdapter = MatchViewPagerAdapter(context as Activity, lastMatch, nextMatch, childFragmentManager)
            tabLayoutMatch.setupWithViewPager(viewpagerMatch)
            viewpagerMatch.adapter = pagerAdapter
            viewpagerMatch.currentItem = lastPosition

            viewpagerMatch.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
                override fun onPageScrollStateChanged(state: Int) {}

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

                override fun onPageSelected(position: Int) {
                    lastPosition = position
                }
            })
        }
    }
}
