package cl.salazarfelipe.felipesalazartd.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import cl.salazarfelipe.felipesalazartd.R
import cl.salazarfelipe.felipesalazartd.model.pojo.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_book_list.view.*

class BookAdapter(private var bookDataset : MutableList<Book>) : RecyclerView.Adapter<BookAdapter.PhoneViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book_list, parent, false)
        return PhoneViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookDataset.size
    }

    val bookSelected = MutableLiveData<Book>()

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        holder.bookTitle.text = bookDataset[position].title
        holder.bookAuthor.text = bookDataset[position].author
        holder.bookCountry.text = bookDataset[position].country
        holder.bookLanguage.text = bookDataset[position].language

        Picasso.get().load(bookDataset[position].imageLink).into(holder.bookImage)

        holder.itemView.setOnClickListener{
            bookSelected.value = bookDataset[position]
        }
    }

    fun updateItems (it: List<Book>) {
        bookDataset.clear()
        bookDataset.addAll(it)
        notifyDataSetChanged()
    }
    class PhoneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bookTitle = itemView.book_title
        var bookAuthor = itemView.book_author
        var bookCountry = itemView.book_country
        var bookLanguage = itemView.book_language
        var bookImage = itemView.book_image
    }
}