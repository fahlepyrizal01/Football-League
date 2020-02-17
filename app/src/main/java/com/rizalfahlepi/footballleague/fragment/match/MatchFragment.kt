package com.rizalfahlepi.footballleague.fragment.match

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizalfahlepi.footballleague.R
import com.rizalfahlepi.footballleague.activity.detailmatchleague.DetailMatchLeagueActivity
import com.rizalfahlepi.footballleague.adapter.MatchRecyclerViewAdapter
import com.rizalfahlepi.footballleague.model.response.Event
import kotlinx.android.synthetic.main.fragment_match.*

class MatchFragment : Fragment() {

    companion object {
        private const val ARG_EVENTS = "events"
        private const val ARG_POSITION = "position"
        fun newInstance(events : ArrayList<Event>, position: String): MatchFragment {
            val fragment = MatchFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList(ARG_EVENTS, events)
            bundle.putString(ARG_POSITION, position)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val events = arguments?.getParcelableArrayList<Event>(ARG_EVENTS) as ArrayList<Event>
            recyclerViewMatch.setHasFixedSize(true)
            recyclerViewMatch.layoutManager = LinearLayoutManager(context)
            val adapter = MatchRecyclerViewAdapter(events)
            recyclerViewMatch.adapter = adapter

            adapter.setOnItemClickCallback(object: MatchRecyclerViewAdapter.OnItemClickCallback {
                override fun onItemClicked(idEvent: String, position: Int) {
                    startActivity(Intent(context, DetailMatchLeagueActivity::class.java)
                        .putExtra("idEvent", idEvent)
                        .putExtra("position", arguments?.getString(ARG_POSITION)))
                }
            })
        }
    }
}
