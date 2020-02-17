package com.rizalfahlepi.footballleague.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.rizalfahlepi.footballleague.R
import com.rizalfahlepi.footballleague.fragment.match.MatchFragment
import com.rizalfahlepi.footballleague.model.response.Event

class MatchViewPagerAdapter (private val context: Context,
                             private val lastMatch: ArrayList<Event>,
                             private val nextMatch: ArrayList<Event>,
                             fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val tabTitles = intArrayOf(R.string.text_last_match, R.string.text_next_match)

    override fun getItem(position: Int): Fragment {
        return MatchFragment.newInstance(if (position == 0) lastMatch else nextMatch, position.toString())
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(tabTitles[position])
    }

    override fun getCount(): Int = 2
}