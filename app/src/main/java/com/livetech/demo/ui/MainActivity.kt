package com.livetech.demo.ui

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.livetech.demo.R
import com.livetech.demo.core.models.Photo
import com.livtech.common.ui.SimpleScrollListener
import com.livtech.common.ui.SimpleSearchTextChangeListener

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: PhotoViewModel
    private lateinit var adapter: PhotosAdapter
    private val photos = ArrayList<Photo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModels()
        initList()
        initPhotos()
        initLoading()
        initSearchView()
    }

    private fun initList() {
        val recyclerView = findViewById<RecyclerView>(R.id.photos_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PhotosAdapter(photos)
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(onScrollListener)
    }

    private fun initLoading() {
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        viewModel.loading.observe(this, Observer { show ->
            run {
                if (show) progressBar.visibility = View.VISIBLE
                else progressBar.visibility = View.GONE
            }
        })
    }

    private fun initSearchView() {
        val search = findViewById<EditText>(R.id.search_edit)
        search.addTextChangedListener(SimpleSearchTextChangeListener(onSearchTextChange = { searchText -> viewModel.doSearching(searchText)
        }, lifeCycle = this.lifecycle) )
    }

    private fun initViewModels() {
        viewModel = ViewModelProviders.of(this).get(PhotoViewModel::class.java)
    }

    private fun initPhotos() {
        viewModel.photoData.observe(this, Observer { newPhotos ->
            run {
                photos.clear()
                photos.addAll(newPhotos)
                adapter.notifyDataSetChanged()
            }
        })
    }

    private val onScrollListener: SimpleScrollListener =
        object : SimpleScrollListener() {
           override fun onScrolledToBottom(){
                viewModel.loadNextPage()
           }
        }
}
