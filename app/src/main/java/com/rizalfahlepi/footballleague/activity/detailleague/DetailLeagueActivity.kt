package com.rizalfahlepi.footballleague.activity.detailleague

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.rizalfahlepi.footballleague.R
import com.rizalfahlepi.footballleague.activity.matchleague.MatchLeagueActivity
import com.rizalfahlepi.footballleague.activity.search.SearchMatchLeagueActivity
import com.rizalfahlepi.footballleague.activity.tableteamleague.TableTeamLeagueActivity
import com.rizalfahlepi.footballleague.activity.teamleague.TeamLeagueActivity
import com.rizalfahlepi.footballleague.model.League
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout

class DetailLeagueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailLeagueUI()
            .setContentView(this)
    }

    class DetailLeagueUI : AnkoComponent<DetailLeagueActivity> {

        companion object {
            const val toolbarID = 1
            const val imageViewFlagID = 2
            const val imageViewLogoID = 3
            const val textViewTitleID = 4
            const val textViewIDID = 5
            const val textViewDescriptionTitleID = 6
            const val constrainLayoutButtonSeeMatchID = 7
            const val buttonSeeMatch = 8
            const val buttonSeeTable = 9
            const val buttonSeeTeam = 10
        }


        override fun createView(ui: AnkoContext<DetailLeagueActivity>) = with(ui) {
            constraintLayout {
                lparams(matchParent, matchParent)

                toolbar {
                    id =
                        toolbarID
                    background = ContextCompat.getDrawable(context, R.color.colorPrimary)
                    title = context.getString(R.string.text_league_details)
                    navigationIcon = ContextCompat.getDrawable(context, R.drawable.ic_arrow_back)
                    setTitleTextColor(ContextCompat.getColor(context, android.R.color.white))
                    setNavigationOnClickListener {
                        (context as Activity).finish()
                    }

                    menu.apply {
                        add(context.getString(R.string.text_action_search))
                            .setIcon(R.drawable.ic_search)
                            .setOnMenuItemClickListener {
                                startActivity<SearchMatchLeagueActivity>()
                                true
                            }
                            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
                    }
                }.lparams {
                    width = dip(0)
                    height = wrapContent
                    startToStart = ConstraintSet.PARENT_ID
                    topToTop = ConstraintSet.PARENT_ID
                    endToEnd = ConstraintSet.PARENT_ID
                }

                scrollView {
                    constraintLayout {
                        imageView {
                            id =
                                imageViewFlagID
                            scaleType = ImageView.ScaleType.FIT_XY
                            Glide.with(context).load(getData(context)?.flag).into(this)
                        }.lparams {
                            width = dip(0)
                            height = dip(170)
                            startToStart = ConstraintSet.PARENT_ID
                            topToTop = ConstraintSet.PARENT_ID
                            endToEnd = ConstraintSet.PARENT_ID
                        }

                        imageView {
                            id =
                                imageViewLogoID
                            Glide.with(context).load(getData(context)?.logo).into(this)
                        }.lparams {
                            width = dip(110)
                            height = dip(110)
                            startToStart =
                                imageViewFlagID
                            topToBottom =
                                imageViewFlagID
                            endToEnd =
                                imageViewFlagID
                            bottomToBottom =
                                imageViewFlagID
                        }

                        textView {
                            id =
                                textViewTitleID
                            text = getData(context)?.title
                            textSize = 16f
                            typeface = Typeface.DEFAULT_BOLD
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams {
                            width = dip(0)
                            height = wrapContent
                            startToStart = ConstraintSet.PARENT_ID
                            topToBottom =
                                imageViewLogoID
                            endToEnd = ConstraintSet.PARENT_ID
                            topMargin = dip(8)
                        }

                        textView {
                            id =
                                textViewIDID
                            text = getData(context)?.id
                            textSize = 16f
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams {
                            width = dip(0)
                            height = wrapContent
                            startToStart = ConstraintSet.PARENT_ID
                            topToBottom =
                                textViewTitleID
                            endToEnd = ConstraintSet.PARENT_ID
                        }

                        textView {
                            id =
                                textViewDescriptionTitleID
                            text = context.getString(R.string.text_description)
                            textSize = 16f
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams {
                            width = wrapContent
                            height = wrapContent
                            leftMargin = dip(16)
                            topMargin = dip(16)
                            startToStart = ConstraintSet.PARENT_ID
                            topToBottom =
                                textViewIDID
                        }

                        textView {
                            text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                Html.fromHtml(getData(context)?.description, Html.FROM_HTML_MODE_COMPACT)
                            } else {
                                @Suppress("DEPRECATION")
                                Html.fromHtml(getData(context)?.description)
                            }
                            textSize = 16f
                        }.lparams {
                            width = dip(0)
                            height = wrapContent
                            topMargin = dip(8)
                            rightMargin = dip(16)
                            bottomMargin = dip(16)
                            startToStart =
                                textViewDescriptionTitleID
                            topToBottom =
                                textViewDescriptionTitleID
                            endToEnd = ConstraintSet.PARENT_ID
                            bottomToBottom = ConstraintSet.PARENT_ID
                        }
                    }.lparams {
                        width = matchParent
                        height = wrapContent
                    }
                }.lparams {
                    width = dip(0)
                    height = dip(0)
                    startToStart = ConstraintSet.PARENT_ID
                    topToBottom =
                        toolbarID
                    endToEnd = ConstraintSet.PARENT_ID
                    bottomToTop =
                        constrainLayoutButtonSeeMatchID
                }

                constraintLayout {
                    id = constrainLayoutButtonSeeMatchID

                    button {
                        id = buttonSeeTable
                        text = context.getString(R.string.text_see_table)
                        textColor = ContextCompat.getColor(context, android.R.color.white)
                        background = ContextCompat.getDrawable(context, R.color.colorPrimary)
                        setOnClickListener {
                            startActivity<TableTeamLeagueActivity>("idLeague" to getData(context)?.id)
                        }
                    }.lparams {
                        width = dip(0)
                        height = wrapContent
                        topMargin = dip(16)
                        bottomMargin = dip(16)
                        rightMargin = dip(8)
                        leftMargin = dip(16)
                        startToStart = ConstraintSet.PARENT_ID
                        topToTop = ConstraintSet.PARENT_ID
                        endToStart = buttonSeeTeam
                        bottomToBottom = ConstraintSet.PARENT_ID
                    }

                    button {
                        id = buttonSeeTeam
                        text = context.getString(R.string.text_see_team)
                        textColor = ContextCompat.getColor(context, android.R.color.white)
                        background = ContextCompat.getDrawable(context, R.color.colorPrimary)
                        setOnClickListener {
                            startActivity<TeamLeagueActivity>("idLeague" to getData(context)?.id)
                        }
                    }.lparams {
                        width = dip(0)
                        height = dip(0)
                        rightMargin = dip(8)
                        startToEnd = buttonSeeTable
                        topToTop = buttonSeeTable
                        endToStart = buttonSeeMatch
                        bottomToBottom = buttonSeeTable
                    }

                    button {
                        id = buttonSeeMatch
                        text = context.getString(R.string.text_see_match)
                        textColor = ContextCompat.getColor(context, android.R.color.white)
                        background = ContextCompat.getDrawable(context, R.color.colorPrimary)
                        setOnClickListener {
                            startActivity<MatchLeagueActivity>("idLeague" to getData(context)?.id)
                        }
                    }.lparams {
                        width = dip(0)
                        height = dip(0)
                        rightMargin = dip(16)
                        startToEnd = buttonSeeTeam
                        topToTop = buttonSeeTable
                        endToEnd = ConstraintSet.PARENT_ID
                        bottomToBottom = buttonSeeTable
                    }
                }.lparams {
                    width = dip(0)
                    height = wrapContent
                    startToStart = ConstraintSet.PARENT_ID
                    endToEnd = ConstraintSet.PARENT_ID
                    bottomToBottom = ConstraintSet.PARENT_ID
                }
            }
        }

        private fun getData(context: Context): League? {
            return if ((context as Activity).intent.hasExtra("league")) context.intent.getParcelableExtra("league") else null
        }
    }
}
