package cl.salazarfelipe.felipesalazartd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.salazarfelipe.felipesalazartd.view.BookListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, BookListFragment.newInstance("", ""), "lista")
                .commit()
        } else {
            supportFragmentManager.findFragmentByTag("lista")
        }
    }
}