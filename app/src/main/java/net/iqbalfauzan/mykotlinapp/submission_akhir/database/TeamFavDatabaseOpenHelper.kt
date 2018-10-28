package net.iqbalfauzan.mykotlinapp.submission_akhir.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class TeamFavDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Team.db", null, 1){
    companion object {
        private var instance: TeamFavDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): TeamFavDatabaseOpenHelper {
            if (instance == null) {
                instance = TeamFavDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as TeamFavDatabaseOpenHelper
        }
    }

    override fun onCreate(p0: SQLiteDatabase) {
        // Here you create tables
        p0.createTable(TeamFavorite.TABLE_FAVORITE, true,
                TeamFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                TeamFavorite.TEAMID to TEXT + UNIQUE,
                TeamFavorite.TEAMNAME to TEXT,
                TeamFavorite.TEAMIMAGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(TeamFavorite.TABLE_FAVORITE, true)
    }
}
// Access property for Context
val Context.databaseTeam: TeamFavDatabaseOpenHelper
    get() = TeamFavDatabaseOpenHelper.getInstance(applicationContext)