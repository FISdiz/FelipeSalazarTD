package cl.salazarfelipe.felipesalazartd.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BookEntity::class, DetailBookEntity::class], version = 1)
abstract class BookDatabase : RoomDatabase() {

    abstract fun getBookDao() : BookDao

    companion object {
        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun getDatabase(context: Context): BookDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java,
                    "phone_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}