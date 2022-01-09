package kr.ac.kpu.first_practice

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "MainActivityLog"
    private val URL = "http://172.30.1.52:3000"
    private lateinit var retrofit: Retrofit
    private lateinit var service: ApiService
    private lateinit var btn_get: Button
    private lateinit var btn_post: Button
    private lateinit var btn_delete: Button
    private lateinit var btn_update: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstInit()
        btn_get.setOnClickListener(this)
        btn_post.setOnClickListener(this)
        btn_delete.setOnClickListener(this)
        btn_update.setOnClickListener(this)
    }

    /**
     * Init
     */
    private fun firstInit() {
        btn_get = findViewById(R.id.btn_get)
        btn_post = findViewById(R.id.btn_post)
        btn_delete = findViewById(R.id.btn_delete)
        btn_update = findViewById(R.id.btn_update)
        retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build()

        service = retrofit.create(ApiService::class.java)
    }

    /**
     * View.OnLongClickListener override method
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_get -> {
                val call_get : Call<ResponseBody> = service.getFunc("get data")
                call_get.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            try {
                                val result = response.body()!!.string()
                                Log.v(TAG, "result = $result")
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT)
                                    .show()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        } else {
                            Log.v(TAG, "error = " + response.code().toString())
                            Toast.makeText(
                                applicationContext,
                                "error = " + response.code().toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.v(TAG, "Fail")
                        Toast.makeText(applicationContext, "Response Fail", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
            R.id.btn_post -> {
                val call_post : Call<ResponseBody> = service.postFunc("post data")
                call_post.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            try {
                                val result = response.body()!!.string()
                                Log.v(TAG, "result = $result")
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT)
                                    .show()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        } else {
                            Log.v(TAG, "error = " + response.code().toString())
                            Toast.makeText(
                                applicationContext,
                                "error = " + response.code().toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.v(TAG, "Fail")
                        Toast.makeText(applicationContext, "Response Fail", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
            R.id.btn_update -> {
                val call_put : Call<ResponseBody> = service.putFunc("board", "put data")
                call_put.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            try {
                                val result = response.body()!!.string()
                                Log.v(TAG, "result = $result")
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT)
                                    .show()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        } else {
                            Log.v(TAG, "error = " + response.code().toString())
                            Toast.makeText(
                                applicationContext,
                                "error = " + response.code().toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.v(TAG, "Fail")
                        Toast.makeText(applicationContext, "Response Fail", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
            R.id.btn_delete -> {
                val call_delete : Call<ResponseBody> = service.deleteFunc("board")
                call_delete.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            try {
                                val result = response.body()!!.string()
                                Log.v(TAG, "result = $result")
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT)
                                    .show()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        } else {
                            Log.v(TAG, "error = " + response.code().toString())
                            Toast.makeText(
                                applicationContext,
                                "error = " + response.code().toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.v(TAG, "Fail")
                        Toast.makeText(applicationContext, "Response Fail", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
            else -> {}
        }
    }
}