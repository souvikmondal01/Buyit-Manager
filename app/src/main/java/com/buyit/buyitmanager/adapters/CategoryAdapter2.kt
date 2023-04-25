package com.buyit.buyitmanager.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buyit.buyitmanager.databinding.ListCategoryBinding
import com.buyit.buyitmanager.models.CategoryModel


class CategoryAdapter2(
    val onItemClicked: (Int, CategoryModel) -> Unit,
    val onDeleteClicked: (Int, CategoryModel) -> Unit
) : RecyclerView.Adapter<CategoryAdapter2.MyViewHolder>() {

    private var list: MutableList<CategoryModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            ListCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
//        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClicked.invoke(position, item)
        }
        holder.binding.ivDelete.setOnClickListener {
            onDeleteClicked.invoke(position, item)
        }
        holder.binding.tvCategoryName.text = item.categoryName

    }

    fun updateList(list: MutableList<CategoryModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemChanged(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(val binding: ListCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)
//    {
//        fun bind(item: CategoryModel) {
//            binding.tvCategoryName.text = item.categoryName
//            binding.date.setText(sdf.format(item.date))
//            binding.tags.apply {
//                if (item.tags.isNullOrEmpty()) {
//                    hide()
//                } else {
//                    removeAllViews()
//                    if (item.tags.size > 2) {
//                        item.tags.subList(0, 2).forEach { tag -> addChip(tag) }
//                        addChip("+${item.tags.size - 2}")
//                    } else {
//                        item.tags.forEach { tag -> addChip(tag) }
//                    }
//                }
//            }
//            binding.desc.apply {
//                if (item.description.length > 120) {
//                    text = "${item.description.substring(0, 120)}..."
//                } else {
//                    text = item.description
//                }
//            }
//            binding.ivDelete.setOnClickListener { onItemClicked.invoke(adapterPosition, item) }
//        }
//    }
}