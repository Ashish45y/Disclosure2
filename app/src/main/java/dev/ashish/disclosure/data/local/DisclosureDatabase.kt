package dev.ashish.disclosure.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.ashish.disclosure.data.local.dao.SourceDao
import dev.ashish.disclosure.data.local.dao.TopHeadlinesDao
import dev.ashish.disclosure.data.local.entity.Article
import dev.ashish.disclosure.data.local.entity.NewsSources

@Database(
    entities = [Article::class, NewsSources::class],
    version = 1,
    exportSchema = false
)
abstract class DisclosureDatabase : RoomDatabase() {

    abstract fun topHeadlinesDao(): TopHeadlinesDao

    abstract fun newsSourceDao(): SourceDao

}