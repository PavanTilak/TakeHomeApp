package com.example.todolist.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.todolist.databinding.TakehomeDetailsBinding
import com.example.todolist.model.Product

class TakeHomeDetailFragment : Fragment() {

    private var product : Product? =  null
    lateinit var mBinding : TakehomeDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = arguments?.getParcelable(BUNDLE_PRODUCT)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = TakehomeDetailsBinding.inflate(layoutInflater)
        mBinding.productIdDetail.text = "Product ID : "+product?.productId
        mBinding.productNameDetail.text = "ProductName : "+product?.productName
        mBinding.shortDesc.text = "ShortDescription : "+product?.shortDescription
        mBinding.longDesc.text = "LongDescription : "+product?.longDescription
        mBinding.priceDetail.text = "Price : "+product?.price
        mBinding.reviewRating.text = "ReviewRating : "+product?.reviewRating.toString()
        mBinding.reviewCount.text = "ReviewCount : "+product?.reviewCount.toString()

        return mBinding.root
    }

    companion object {
        private const val BUNDLE_PRODUCT="com.example.takehome.BUNDLE_PRODUCT"

        fun newInstance(product : Product) =
                TakeHomeDetailFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(BUNDLE_PRODUCT, product)
                    }
                }
    }

}