package com.example.tugasqatros

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasqatros.databinding.ItemLayoutBinding
import com.example.tugasqatros.model.EntityGoods

class AdapterMain: RecyclerView.Adapter<AdapterMain.ViewHolderMain>() {
    private val listData = ArrayList<EntityGoods>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(item: EntityGoods)
    }

   inner class ViewHolderMain(itemView: View): RecyclerView.ViewHolder(itemView) {
       private val binding = ItemLayoutBinding.bind(itemView)
        fun bind(data:EntityGoods){
            with(binding){
                tvName.text = data.name
                tvItem.text = data.goods
                tvTimeDate.text = data.time
                tvSupplier.text = data.supplier
            }
        }
       init {
           binding.root.setOnClickListener {
               onItemClickCallback?.onItemClicked(listData[adapterPosition])
           }
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolderMain(view)
    }

    override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
        return holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    fun setData(data: List<EntityGoods>){
        this.listData.clear()
        this.listData.addAll(data)
        notifyDataSetChanged()
    }
}