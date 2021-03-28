package com.example.todolist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.model.Product
import com.example.todolist.views.TakeHomeDetailFragment


class TakeHomeListAdapter(private val mProductList: List<Product>, private val context: Context)
    : RecyclerView.Adapter<TakeHomeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.todolist_row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = mProductList!![position]

        holder.productId.text = "ProductId : " + product.productId
        holder.productName.text = "ProductName : " + product.productName
        holder.price.text = "Price : " + product.price
        holder.reviewRating.text = "ReviewRating : " + product.reviewRating

        holder.mViewRoot.setOnClickListener {
            val detailFragment: Fragment = TakeHomeDetailFragment.newInstance(product)
            (context as AppCompatActivity).supportFragmentManager.beginTransaction().replace(R.id.restTabView, detailFragment).addToBackStack(null).commit()
        }
    }

    override fun getItemCount(): Int {
        return mProductList?.size ?: 0
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val productId: TextView = view.findViewById(R.id.productId)
        val productName: TextView = view.findViewById(R.id.productName)
        val price: TextView = view.findViewById(R.id.price)
        val reviewRating: TextView = view.findViewById(R.id.reviewRating)
        val mViewRoot: ConstraintLayout = view.findViewById(R.id.mViewRoot)
    }
}
