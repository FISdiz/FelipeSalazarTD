package cl.salazarfelipe.felipesalazartd.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.salazarfelipe.felipesalazartd.model.Repository
import cl.salazarfelipe.felipesalazartd.model.pojo.Book
import cl.salazarfelipe.felipesalazartd.model.pojo.BookDetail

class BookViewModel(application: Application) : AndroidViewModel(application) {

    var repository : Repository = Repository(application)
    var bookList = repository.bookList
    lateinit var result : LiveData<BookDetail>

    init {
        repository = Repository(application)
        repository.loadApiData()
    }

    private val selectedBook = MutableLiveData<Book>()

    fun selectedId (book : Book) {
        selectedBook.value = book
        repository.loadDetailData(book.id)
        result = repository.getBookDetail(book.id)
    }

}