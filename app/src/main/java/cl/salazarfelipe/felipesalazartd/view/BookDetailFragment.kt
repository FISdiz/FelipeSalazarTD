package cl.salazarfelipe.felipesalazartd.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import cl.salazarfelipe.felipesalazartd.R
import cl.salazarfelipe.felipesalazartd.viewmodel.BookViewModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import kotlinx.android.synthetic.main.fragment_book_detail.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BookDetailFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BookDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val phoneViewModel : BookViewModel by activityViewModels()
        phoneViewModel.result.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                det_title.text = it.title
                det_author.text = it.author

                Picasso
                    .get()
                    .load(it.imageLink)
                    .into(det_image)
                Picasso
                    .get()
                    .load(it.imageLink)
                    .transform(BlurTransformation(context,25,1))
                    .into(det_image_blur)
            }
        })
    }
}
            /*fun email() {
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("ensayoPrueba@gmail.com"))
                intent.putExtra(Intent.EXTRA_SUBJECT, "Consulta por ${it.name} , ID : ${it.id} ")
                intent.putExtra(Intent.EXTRA_TEXT, " “Estimados\n" +
                        "Buen día, vi el producto ${it.name} y me gustaría que me contactaran a este correo o al\n" +
                        "siguiente número _________")
                intent.type = "message/rfc822"
                startActivity(Intent.createChooser(intent, "Choose an email client"))
            }

            fab.setOnClickListener { view ->
                Snackbar.make(view, "Email", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show()
                email()
            }
        })*/
