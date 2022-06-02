package com.midominio.ejercicio_3.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.midominio.ejercicio_3.databinding.ActivityProductoDetallesBinding
import com.midominio.ejercicio_3.databinding.ListElementsBinding
import com.midominio.ejercicio_3.model.ProductApi
import com.midominio.ejercicio_3.model.Producto
import com.midominio.ejercicio_3.model.ProductoDetalles
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductoDetalles : AppCompatActivity() {

    private lateinit var binding: ActivityProductoDetallesBinding

    private val BASE_URL= "https://www.serverbpw.com/"
    private val LOG_TAG = "LOGS"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductoDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        val id = bundle?.getString("id", "0")
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val  productApi: ProductApi = retrofit.create(ProductApi::class.java)

        val call: Call<ProductoDetalles> = productApi.getProductDetail(id)

        call.enqueue(object : Callback<ProductoDetalles>{
            override fun onResponse(call: Call<ProductoDetalles>, response: Response<ProductoDetalles>
            ) {

                with(binding){
                    pbConexion.visibility = View.INVISIBLE
                    Toast.makeText(this@ProductoDetalles, "Conexión Establecida", Toast.LENGTH_SHORT).show()

                    tvTitle.text = response.body()?.name

                    Glide.with(this@ProductoDetalles)
                        .load(response.body()?.imag_url)
                        .into(ivImage)

                    tvLongDesc.text = response.body()?.desc
                }

            }

            override fun onFailure(call: Call<ProductoDetalles>, t: Throwable) {
                Log.d(LOG_TAG,"ERROR")
                Toast.makeText(this@ProductoDetalles, "No hay conexión Producto", Toast.LENGTH_SHORT).show()
                binding.pbConexion.visibility = View.INVISIBLE
            }

        })

    }
}