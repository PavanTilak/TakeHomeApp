package com.example.todolist.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.api.IAwsToDoList
import com.example.todolist.model.TakeHomeModel
import com.example.todolist.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class TakeHomeViewModel  : ViewModel() /*, Callback<TakeHomeModel>*/ {

    private var mTakeHomeLiveObj: MutableLiveData<TakeHomeModel> = MutableLiveData()
    lateinit var mTakeHome: TakeHomeModel

    //coroutine service call
    fun takeHomeServiceCall() {
        val service = ApiClient.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getTakeHomeData(6,6)
            withContext(Dispatchers.IO) {
                try {
                    val data : Response<TakeHomeModel> = response.execute()
                    if (data.isSuccessful) {
                        val listModel = data.body()
                        if (listModel != null) {
                            mTakeHome = listModel
                            if (mTakeHome != null ) {
                                mTakeHomeLiveObj.postValue(mTakeHome)
                            }
                        }
                    } else {
                        System.out.println("khfkdshf ")
                        //toast("Error: ${response.code()}")
                    }
                } catch (e: HttpException) {
                    System.out.println("khfkdshf ")
                    //toast("Exception ${e.message}")
                } catch (e: Throwable) {
                    System.out.println("khfkdshf ")
                    //toast("Ooops: Something else went wrong")
                }
            }
        }
    }

    //RxJava backend service call
    /*fun getTakeHomeApiCall() {
        val todoListApi = ApiClient.apiClient().create(IAwsToDoList::class.java)
        val call = todoListApi.getTakeHomeData(6,6)
        call.enqueue(this as (Callback<TakeHomeModel>))
    }

    override fun onResponse(call: Call<TakeHomeModel>, response: Response<TakeHomeModel>) {
        if (response.isSuccessful) {

            val listModel = response.body()
            if (listModel != null) {
                 mTakeHome = listModel
                if (mTakeHome != null ) {
                    mTakeHomeLiveObj.postValue(mTakeHome)
                }
            }
        } else {
            mTakeHomeLiveObj.postValue(null)
        }
    }

    override fun onFailure(call: Call<TakeHomeModel>, t: Throwable) {
        Log.e("TakeHomeViewModel"," request failed= "+t.stackTrace)
        mTakeHomeLiveObj.postValue(null)
    }*/

    fun getTakeHomeData() : MutableLiveData<TakeHomeModel> {
        return mTakeHomeLiveObj
    }
}