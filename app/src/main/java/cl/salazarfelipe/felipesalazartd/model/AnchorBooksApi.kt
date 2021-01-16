package cl.salazarfelipe.felipesalazartd.model

import cl.salazarfelipe.felipesalazartd.model.pojo.Book
import cl.salazarfelipe.felipesalazartd.model.pojo.BookDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AnchorBooksApi {

    @GET("Himuravidal/anchorBooks/books")
    fun allBooks() : Call<List<Book>>

    @GET("Himuravidal/anchorBooks/bookDetail/{id}")
    fun detailBook(@Path("id") id:Int) : Call<BookDetail>

}