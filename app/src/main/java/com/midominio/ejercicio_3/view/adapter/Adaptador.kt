package com.midominio.ejercicio_3.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.midominio.ejercicio_3.databinding.ActivityMainBinding
import com.midominio.ejercicio_3.databinding.ListElementsBinding
import com.midominio.ejercicio_3.model.Producto

class Adaptador(context: Context, product: List<Producto>, onItemListener:OnItemListener): RecyclerView.Adapter<Adaptador.ViewHolder>() {

    private val product = product
    private val mOnListener = onItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = ListElementsBinding.inflate(layoutInflater)

        return ViewHolder(binding, mOnListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(product[position])
    }

    override fun getItemCount(): Int {
        return product.size
    }

    interface OnItemListener{
        fun onItemClick(Product: Producto)

    }

    class ViewHolder(binding: ListElementsBinding, onItemListener: OnItemListener): RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        private val ivThumbnail = binding.ivThumbnail
        private val tvTitle = binding.tvTitle
        private val tvEnvio = binding.tvEnvio
        private val tvPrecio = binding.tvPrecio
        private val tvProveedor = binding.tvProveedor

        private val context = binding.root.context
        private val onItemListener = onItemListener
        private lateinit var product: Producto


        init {
            binding.root.setOnClickListener(this)
        }

        fun bindData(item: Producto){

            tvTitle.text = item.name
            tvEnvio.text = item.delivery
            tvPrecio.text = item.price
            tvProveedor.text = item.provider

            Glide.with(context)
                .load(item.thumbnail_url)
                .into(ivThumbnail)

            product = item
        }

        override fun onClick(p0: View?) {
            onItemListener.onItemClick(product)
        }

    }

}