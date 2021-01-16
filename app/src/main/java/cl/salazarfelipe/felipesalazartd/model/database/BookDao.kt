package cl.salazarfelipe.felipesalazartd.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.salazarfelipe.felipesalazartd.model.pojo.Book
import cl.salazarfelipe.felipesalazartd.model.pojo.BookDetail

@Dao
interface BookDao {

    @Query("SELECT * FROM books_table")
    fun getAllBooks() : LiveData<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(bookList : List<BookEntity>)

    @Query("SELECT * FROM detail_book_table WHERE id=:id" )
    fun getSingleDetail(id : Int) : LiveData<BookDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailBook (bookDetail : DetailBookEntity)

}