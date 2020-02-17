package com.rizalfahlepi.footballleague.db

data class FavoriteTeam (val id: Long?,
                          val TEAM_ID: String?,
                          val TEAM_NAME: String?,
                          val TEAM_LOGO: String?) {

    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_LOGO: String = "TEAM_LOGO"
    }
}