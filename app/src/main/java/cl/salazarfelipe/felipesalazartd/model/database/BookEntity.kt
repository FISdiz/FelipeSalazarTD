package cl.salazarfelipe.felipesalazartd.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books_table")
data class BookEntity (
    @PrimaryKey val id : Int,
    val author: String,
    val country: String,
    val imageLink: String,
    val language: String,
    val title: String
)