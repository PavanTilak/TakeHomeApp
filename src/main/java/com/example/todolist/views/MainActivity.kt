package com.example.todolist.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.adapter.TakeHomeListAdapter
import com.example.todolist.utilities.Utils.checkNetworkState
import com.example.todolist.viewmodels.TakeHomeViewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private var mRecyclerView: RecyclerView? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var mRecyclerAdapter: RecyclerView.Adapter<*>? = null
    private var mEmptyView: TextView? = null
    private var mProgressBar: ProgressBar? = null
    lateinit var mViewModel: TakeHomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(
                this.application).create(TakeHomeViewModel::class.java)

        initViewIds()
        setupViewModelObservers()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initViewIds() {
        mRecyclerView = findViewById(R.id.recycler_view)
        mEmptyView = findViewById(R.id.emptyStringView)

        mProgressBar = findViewById(R.id.progressBar)
    }

    private fun setupViewModelObservers() {
        mViewModel.getTakeHomeData().observe(this,androidx.lifecycle.Observer {
            mProgressBar!!.visibility = View.GONE

            if(it != null) {
                mRecyclerAdapter = TakeHomeListAdapter(it.products, this)
                mLayoutManager = LinearLayoutManager(this)

                mRecyclerView!!.layoutManager = mLayoutManager
                mRecyclerView!!.visibility = View.VISIBLE
                mEmptyView!!.visibility = View.GONE
                mRecyclerView!!.adapter = mRecyclerAdapter
            } else {
                mRecyclerView!!.visibility = View.GONE
                mEmptyView!!.visibility = View.VISIBLE
                Toast.makeText(this@MainActivity, "No Response received from backend", Toast.LENGTH_SHORT).show()
            }
        })
        loadTodoList();
    }

    private fun loadTodoList() {
        if(checkNetworkState(this)) {
            mProgressBar!!.visibility = View.VISIBLE
            mViewModel.takeHomeServiceCall()
        } else {
            Toast.makeText(this@MainActivity, resources.getText(R.string.network_error), Toast.LENGTH_LONG).show()
        }
    }
}