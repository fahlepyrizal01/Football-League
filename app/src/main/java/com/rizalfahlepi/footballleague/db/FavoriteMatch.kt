package com.rizalfahlepi.footballleague.db

data class FavoriteMatch (val id: Long?,
                          val EVENT_ID: String?,
                          val HOME_TEAM_NAME: String?,
                          val AWAY_TEAM_NAME: String?,
                          val HOME_TEAM_SCORE: String?,
                          val AWAY_TEAM_SCORE: String?,
                          val DATE: String?,
                          val TIME: String?,
                          val IS_LAST_MATCH: String?) {

    companion object {
        const val TABLE_FAVORITE_MATCH: String = "TABLE_FAVORITE_MATCH"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val HOME_TEAM_SCORE: String = "HOME_TEAM_SCORE"
        const val AWAY_TEAM_SCORE: String = "AWAY_TEAM_SCORE"
        const val DATE: String = "DATE"
        const val TIME: String = "TIME"
        const val IS_LAST_MATCH: String = "IS_LAST_MATCH"
    }
}