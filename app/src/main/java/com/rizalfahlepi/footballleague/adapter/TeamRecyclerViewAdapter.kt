package com.rizalfahlepi.footballleague.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rizalfahlepi.footballleague.R
import com.rizalfahlepi.footballleague.model.response.Team
import kotlinx.android.synthetic.main.item_team.view.*

class TeamRecyclerViewAdapter(private val table: ArrayList<Team>) : RecyclerView.Adapter<TeamRecyclerViewAdapter.ViewHolder>() {

    private var name = ""
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_team, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(table[position])
    }

    override fun getItemCount(): Int = table.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(team: Team) {
            with(itemView){
                Glide.with(context).load(team.strTeamBadge).into(imageViewLogoTeam)
                name = "${team.strTeam} (${team.idTeam})"
                textViewNameTeam.text = name

                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(team)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked (team: Team)
    }
}