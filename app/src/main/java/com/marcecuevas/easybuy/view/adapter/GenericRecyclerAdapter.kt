package com.marcecuevas.easybuy.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcecuevas.easybuy.data.model.DTO.GenericDTO

abstract class GenericRecyclerAdapter<VH: GenericRecyclerAdapter.GenericViewHolder<T>, T:
                GenericDTO>(val context: Context?): RecyclerView.Adapter<VH>() {

    var items: List<T>? = emptyList()

    fun loadItems(newItems: List<T>?){
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return getHolder(parent)
    }

    override fun getItemCount(): Int {
        return items?.count() ?: 0
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items?.get(position))
    }

    protected abstract fun getHolder(parent: ViewGroup): VH


    abstract class GenericViewHolder<T: GenericDTO>(itemView: View): RecyclerView.ViewHolder(itemView){

        abstract fun bind(item: T?)
    }
}