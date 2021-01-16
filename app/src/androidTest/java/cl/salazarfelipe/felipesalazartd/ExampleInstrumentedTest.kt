package cl.salazarfelipe.felipesalazartd

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import cl.salazarfelipe.felipesalazartd.model.database.BookDao
import cl.salazarfelipe.felipesalazartd.model.database.BookDatabase
import cl.salazarfelipe.felipesalazartd.model.database.BookEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class TestInstrumental {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var bookDao: BookDao
    private lateinit var bookDB: BookDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        bookDB = Room.inMemoryDatabaseBuilder(context, BookDatabase::class.java).build()
        bookDao = bookDB.getBookDao()
    }

    @After
    fun tearDown() {
        bookDB.close()
    }

    @Test
    fun insertBook() = runBlocking {
        //Given
        val bookList =
            listOf(BookEntity(5, "Felipe", "Chile", "https://123Entel", "Español", "asinomas"))

        //When
        bookDao.insertBook(bookList)

        //Then
        bookDao.getAllBooks().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }
}
