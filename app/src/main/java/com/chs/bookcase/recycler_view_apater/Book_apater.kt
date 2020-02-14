package com.chs.bookcase.recycler_view_apater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chs.bookcase.R
import com.chs.bookcase.data_binding_class.Books
import com.chs.bookcase.databinding.ItemBooksBinding
import kotlinx.android.synthetic.main.item_books.view.*

class BookAdapter(val mcontext:FragmentActivity, val items:List<Books>,
                  private val clickListener:(book: Books)->Unit) : RecyclerView.Adapter<BookAdapter.BookViewHolder>(){

    class BookViewHolder(val binding: ItemBooksBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_books,parent,false)
        val viewHolder = BookViewHolder(ItemBooksBinding.bind(view))
        view.setOnClickListener {
            if(viewHolder.adapterPosition != RecyclerView.NO_POSITION)
                clickListener.invoke(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount()=items.size

    override fun onBindViewHolder(holder: BookAdapter.BookViewHolder, position: Int) {
        holder.binding.bookData = items[position]
        if(items[position].img_url!="No_img"){
            Glide.with(mcontext).load(items[position].img_url).into(holder.itemView.item_book_img)
        }
        else Glide.with(mcontext).load(R.drawable.ico_error_img).into(holder.itemView.item_book_img)

        if(items[position].book_state_img==0) holder.itemView.item_book_state.setImageResource(R.drawable.ico_ok)
        else holder.itemView.item_book_state.setImageResource(R.drawable.ico_waning)
    }
}