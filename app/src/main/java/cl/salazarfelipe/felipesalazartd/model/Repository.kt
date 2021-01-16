package cl.salazarfelipe.felipesalazartd.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import cl.salazarfelipe.felipesalazartd.model.database.BookDatabase
import cl.salazarfelipe.felipesalazartd.model.database.BookEntity
import cl.salazarfelipe.felipesalazartd.model.database.DetailBookEntity
import cl.salazarfelipe.felipesalazartd.model.pojo.Book
import cl.salazarfelipe.felipesalazartd.model.pojo.BookDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(context: Context) {

    var bookDatabase = BookDatabase.getDatabase(context)
    var bookList = bookDatabase.getBookDao().getAllBooks()

    fun loadApiData() {

        val call = RetrofitClient.retrofitInstance().allBooks()

        call.enqueue(object : Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                if (response.isSuccessful) {
                    saveListDatabase(bookConverter(response.body()!!))
                } else {
                Log.d("REPO", "${call.request().url()}")
            }
            }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
            }
        })
    }

    fun loadDetailData (id:Int) {

        val call = RetrofitClient.retrofitInstance().detailBook(id)

        call.enqueue(object : Callback<BookDetail> {
            override fun onResponse(call: Call<BookDetail>, response: Response<BookDetail>) {
                saveDetailDatabase(detailBookConverter(response.body()!!))
            }

            override fun onFailure(call: Call<BookDetail>, t: Throwable) {
            }
        })
    }

    fun saveListDatabase(listBookEntity : List<BookEntity>) {
        CoroutineScope(Dispatchers.IO).launch {
            bookDatabase.getBookDao().insertBook(listBookEntity)
        }
    }

    fun saveDetailDatabase (detailEntity : DetailBookEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            bookDatabase.getBookDao().insertDetailBook(detailEntity)
        }
    }

    fun getBookDetail (id : Int) : LiveData<BookDetail> {
        return bookDatabase.getBookDao().getSingleDetail(id)
    }
}