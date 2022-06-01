package com.midominio.ejercicio_3.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.midominio.ejercicio_3.R
import com.midominio.ejercicio_3.databinding.ActivityMainBinding
import com.midominio.ejercicio_3.model.ProductApi
import com.midominio.ejercicio_3.model.Producto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL= "https://www.serverbpw.com/"
    private val LOG_TAG = "LOGS"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val  productApi: ProductApi = retrofit.create(ProductApi::class.java)

        val call: Call<List<Producto>> = productApi.getProduct("cm/2022-2/products.php")

        call.enqueue(object: Callback<List<Producto>>{
            override fun onResponse(call: Call<List<Producto>>,response: Response<List<Producto>>
            ) {

                Log.d(LOG_TAG,"Respuesta del Servidor: ${response.toString()}")
                Log.d(LOG_TAG, "Datos: ${response.body().toString()}")

            }

            override fun onFailure(call: Call<List<Producto>>, t: Throwable) {
                Log.d(LOG_TAG,"ERROR")
                Toast.makeText(this@MainActivity, "No hay conexión", Toast.LENGTH_SHORT).show()
            }

        })

    }
}