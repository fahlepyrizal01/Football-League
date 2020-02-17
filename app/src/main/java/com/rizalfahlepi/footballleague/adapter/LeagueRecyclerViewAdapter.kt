package com.rizalfahlepi.footballleague.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rizalfahlepi.footballleague.model.League
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.constraint.layout.constraintLayout

class LeagueRecyclerViewAdapter(private val context: Context, private val leagues: ArrayList<League>) : RecyclerView.Adapter<LeagueRecyclerViewAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(ListLeagueUI().createView(AnkoContext.create(context, viewGroup)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(leagues[position])
    }

    override fun getItemCount(): Int = leagues.size

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(league: League) {
            with(itemView){
                Glide.with(context).load(league.logo).into(findViewById(ListLeagueUI.imageViewLogo))
                findViewById<TextView>(ListLeagueUI.textViewTitle).text = league.title

                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(league)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked (league: League)
    }

    class ListLeagueUI : AnkoComponent<ViewGroup> {
        companion object {
            const val imageViewLogo =  1
            const val textViewTitle = 2
        }

        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            cardView {
                lparams {
                    width = matchParent
                    height = wrapContent
                    margin = dip(5)
                    radius = dip(8).toFloat()
                }

                constraintLayout {
                    padding = dip(16)
                    lparams(matchParent, wrapContent)

                    imageView {
                        id = imageViewLogo
                    }.lparams {
                        height = dip(50)
                        width = dip(50)
                        startToStart = ConstraintSet.PARENT_ID
                        topToTop = ConstraintSet.PARENT_ID
                        endToEnd = ConstraintSet.PARENT_ID
                    }

                    textView {
                        id = textViewTitle
                        textSize = 16f
                        typeface = Typeface.DEFAULT_BOLD
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams {
                        width = dip(0)
                        height = wrapContent
                        topMargin = dip(8)
                        startToStart = ConstraintSet.PARENT_ID
                        topToBottom = imageViewLogo
                        endToEnd = ConstraintSet.PARENT_ID
                        bottomToBottom = ConstraintSet.PARENT_ID
                    }
                }
            }
        }
    }
}