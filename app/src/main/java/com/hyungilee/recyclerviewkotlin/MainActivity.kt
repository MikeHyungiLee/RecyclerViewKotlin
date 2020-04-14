package com.hyungilee.recyclerviewkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyungilee.recyclerviewkotlin.models.BlogPost
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG = "MainActivity"
    }

    private lateinit var blogAdapter: BlogRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet(){
        val data = DataSource.createDataSet()
        blogAdapter.submitList(data)
    }
    private fun initRecyclerView(){
        // apply 를 사용해서 layoutManager 와 adapter 를 지정해주었을때,
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            blogAdapter = BlogRecyclerAdapter()
            blogAdapter.setOnPostClickListener(onPostClickListener)
            val topSpacingDecoration = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecoration)
            adapter = blogAdapter
        }
    }

    // onPostClickListener
    private val onPostClickListener = object : BlogRecyclerAdapter.OnPostClickListener{
        override fun onClick(blogPost: BlogPost) {
            Toast.makeText(applicationContext, blogPost.username, Toast.LENGTH_SHORT).show()
        }
    }
}
