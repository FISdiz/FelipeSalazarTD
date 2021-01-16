package cl.salazarfelipe.felipesalazartd

import cl.salazarfelipe.felipesalazartd.model.bookConverter
import cl.salazarfelipe.felipesalazartd.model.detailBookConverter
import cl.salazarfelipe.felipesalazartd.model.pojo.Book
import cl.salazarfelipe.felipesalazartd.model.pojo.BookDetail
import org.junit.Test

import org.junit.Assert.*

class ExampleUnitTest {

    @Test
    fun TestDetailConverter() {
        //Given
        val bookDetail = BookDetail(1, "mauricio", "chile", "http://", "Español", "http://2", 1, "nose", 2000, 2000, 3000, true)
        //When
        val response = detailBookConverter(bookDetail)
        //Then
        assert(response.id == bookDetail.id)
    }
}