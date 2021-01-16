package cl.salazarfelipe.felipesalazartd.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import cl.salazarfelipe.felipesalazartd.R
import cl.salazarfelipe.felipesalazartd.model.pojo.Book
import cl.salazarfelipe.felipesalazartd.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_book_list.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BookListFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var bookRecycler = ArrayList<Book>()
    private lateinit var adapter : BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BookListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BookAdapter(bookRecycler)
        recycler_book.adapter = adapter

        val model : BookViewModel by activityViewModels()
        model.bookList.observe(viewLifecycleOwner, Observer {
            adapter.updateItems(it)
        })

        adapter.bookSelected.observe(viewLifecycleOwner, Observer {

            model.selectedId(it)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, BookDetailFragment.newInstance("", ""), "detail")
                .addToBackStack("detail")
                .commit()
        })
    }
}