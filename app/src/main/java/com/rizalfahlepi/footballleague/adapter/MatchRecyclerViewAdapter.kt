package com.rizalfahlepi.footballleague.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rizalfahlepi.footballleague.R
import com.rizalfahlepi.footballleague.model.response.Event
import kotlinx.android.synthetic.main.item_match.view.*
import java.text.SimpleDateFormat
import java.util.*

class MatchRecyclerViewAdapter(private val events: ArrayList<Event>) : RecyclerView.Adapter<MatchRecyclerViewAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    private val simpleDateFormatView = SimpleDateFormat("yyyy-MM-dd HH:mm:ss ZZZZZ", Locale.getDefault())
    private var date: Date? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_match, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position], position)
    }

    override fun getItemCount(): Int = events.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: Event, position: Int) {
            with(itemView){
                textViewValueDate.text = try {
                    simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
                    date = simpleDateFormat.parse("${event.dateEvent}T${event.strTime}")
                    simpleDateFormatView.timeZone = TimeZone.getDefault()
                    events[position].dtLocaleDate = date
                    if (date != null){
                        simpleDateFormatView.format(date ?: Date())
                    }else{
                        "-"
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                    "-"
                }
                textViewValueTeamHome.text = event.strHomeTeam ?: "-"
                textViewValueScoreHome.text = event.intHomeScore ?: "0"
                textViewValueTeamAway.text = event.strAwayTeam ?: "-"
                textViewValueScoreAway.text = event.intAwayScore ?: "0"

                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(event.idEvent ?: "", if (event.dtLocaleDate ?: Date() < Date()) 0 else 1)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked (idEvent: String, position: Int)
    }
}