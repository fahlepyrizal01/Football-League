package com.rizalfahlepi.footballleague.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rizalfahlepi.footballleague.R
import com.rizalfahlepi.footballleague.model.response.Table
import kotlinx.android.synthetic.main.item_table.view.*

class TableRecyclerViewAdapter(private val table: ArrayList<Table>) : RecyclerView.Adapter<TableRecyclerViewAdapter.ViewHolder>() {

    private var team = ""

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_table, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0){
            holder.bind(table[position], position)
        }else{
            holder.bind(table[position-1], position)
        }
    }

    override fun getItemCount(): Int = table.size+1

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(table: Table, position: Int) {
            with(itemView){
                if (position == 0){
                    textViewTeam.text = context.getString(R.string.text_team)
                    textViewPlayed.text = context.getString(R.string.text_played)
                    textViewWin.text = context.getString(R.string.text_win)
                    textViewDraw.text = context.getString(R.string.text_draw)
                    textViewLoss.text = context.getString(R.string.text_loss)
                    textViewGoal.text = context.getString(R.string.text_goal)
                    textViewTotal.text = context.getString(R.string.text_total)

                    Typeface.BOLD.apply {
                        textViewTeam.setTypeface(null, this)
                        textViewPlayed.setTypeface(null, this)
                        textViewWin.setTypeface(null, this)
                        textViewDraw.setTypeface(null, this)
                        textViewLoss.setTypeface(null, this)
                        textViewGoal.setTypeface(null, this)
                        textViewTotal.setTypeface(null, this)
                    }
                }else{
                    team = "${table.name} (${table.teamid})"
                    textViewTeam.text = team
                    textViewPlayed.text = table.played
                    textViewWin.text = table.win
                    textViewDraw.text = table.draw
                    textViewLoss.text = table.loss
                    textViewGoal.text = table.goalsFor
                    textViewTotal.text = table.total

                    Typeface.NORMAL.apply {
                        textViewTeam.setTypeface(null, this)
                        textViewPlayed.setTypeface(null, this)
                        textViewWin.setTypeface(null, this)
                        textViewDraw.setTypeface(null, this)
                        textViewLoss.setTypeface(null, this)
                        textViewGoal.setTypeface(null, this)
                        textViewTotal.setTypeface(null, this)
                    }
                }
            }
        }
    }
}