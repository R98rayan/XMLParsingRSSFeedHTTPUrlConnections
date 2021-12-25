package com.example.rssfeeddemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xmlparsingrssfeedhttpurlconnections.Apology
import com.example.xmlparsingrssfeedhttpurlconnections.R
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapter(private var list: List<Apology>): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = list[position]

        holder.itemView.apply {
            itemTitle.text = item.title
            itemSummary.text = item.summary
        }
    }

    override fun getItemCount() = list.size

    fun update(list: List<Apology>){
        this.list = list
        notifyDataSetChanged()
    }
}