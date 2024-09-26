package com.example.apk_pandu.UI

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.apk_pandu.R
import com.example.apk_pandu.api.ApiClient
import com.example.apk_pandu.api.adapter.ProductAdapter
import com.example.apk_pandu.api.model.Product
import com.example.apk_pandu.api.model.ProductResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity3 : AppCompatActivity() {
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var call: Call<ProductResponse>
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        swipeRefresh = findViewById(R.id.refresh_layout)
        recyclerView = findViewById(R.id.recycler_view)

        productAdapter = ProductAdapter { product -> productOnClick(product) }
        recyclerView.adapter = productAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        swipeRefresh.setOnRefreshListener {
            getData()
        }

        getData()
    }

    private fun productOnClick(product: Product) {
        Toast.makeText(this, product.description, Toast.LENGTH_SHORT).show()
    }

    private fun getData() {
        swipeRefresh.isRefreshing = true

        call = ApiClient.productService.getAll()
        call.enqueue(object : Callback<ProductResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                swipeRefresh.isRefreshing = false
                if (response.isSuccessful) {
                    productAdapter.submitList(response.body()?.products)
                    productAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                swipeRefresh.isRefreshing = false
                Toast.makeText(this@MainActivity3, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
