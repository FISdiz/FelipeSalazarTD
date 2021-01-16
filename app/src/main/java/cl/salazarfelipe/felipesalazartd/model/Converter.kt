package cl.salazarfelipe.felipesalazartd.model

import cl.salazarfelipe.felipesalazartd.model.database.BookEntity
import cl.salazarfelipe.felipesalazartd.model.database.DetailBookEntity
import cl.salazarfelipe.felipesalazartd.model.pojo.Book
import cl.salazarfelipe.felipesalazartd.model.pojo.BookDetail

fun bookConverter(bookList: List<Book>): List<BookEntity> {
    return bookList.map { book ->
        BookEntity(
            book.id,
            book.author,
            book.country,
            book.imageLink,
            book.language,
            book.title
        )
    }
}

fun detailBookConverter (detailBook : BookDetail) : DetailBookEntity {
    return DetailBookEntity(
        detailBook.id,
        detailBook.author,
        detailBook.country,
        detailBook.imageLink,
        detailBook.language,
        detailBook.link,
        detailBook.pages,
        detailBook.title,
        detailBook.year,
        detailBook.price,
        detailBook.lastPrice,
        detailBook.delivery
    )
}
