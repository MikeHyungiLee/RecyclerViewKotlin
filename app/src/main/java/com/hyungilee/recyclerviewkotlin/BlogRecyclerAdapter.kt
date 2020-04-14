package com.hyungilee.recyclerviewkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hyungilee.recyclerviewkotlin.models.BlogPost
import kotlinx.android.synthetic.main.layout_blog_list_item.view.*

class BlogRecyclerAdapter : RecyclerView.Adapter<BlogRecyclerAdapter.ViewHolder>(){

    private var items : List<BlogPost> = ArrayList()

    private lateinit var mOnPostClickListener: OnPostClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_blog_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BlogRecyclerAdapter.ViewHolder, position: Int) {
        when(holder){
            is ViewHolder ->{
                holder.bind(items[position])
            }
        }
    }

    fun submitList(blogList: List<BlogPost>){
        items = blogList
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val blogImage: ImageView = itemView.blog_image
        private val blogTitle: TextView = itemView.blog_title
        private val blogAuthor: TextView = itemView.blog_author

        fun bind(blogPost: BlogPost){
            blogTitle.text = blogPost.title
            blogAuthor.text = blogPost.username

            itemView.setOnClickListener{
                mOnPostClickListener.onClick(blogPost)
            }

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
            Glide.with(itemView.context)
                .setDefaultRequestOptions(requestOptions)
                .load(blogPost.image)
                .into(blogImage)

        }
    }

    interface OnPostClickListener{
        fun onClick(blogPost: BlogPost)
    }

    fun setOnPostClickListener(onPostClickListener: OnPostClickListener){
        this.mOnPostClickListener = onPostClickListener
    }
}
