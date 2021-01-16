package cl.salazarfelipe.felipesalazartd.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://my-json-server.typicode.com/"

class RetrofitClient {

    companion object {

        fun retrofitInstance() : AnchorBooksApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(AnchorBooksApi::class.java)
        }
    }
}