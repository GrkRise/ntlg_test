package com.rise.grk.kotlin.ntlg_test

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder

import com.rise.grk.kotlin.ntlg_test.retrofit.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    var itemsArr : ArrayList<Cell> = ArrayList()
    lateinit var rv : RecyclerView
    lateinit var adapter: CustomAdapter
    lateinit var progress: ProgressBar
    lateinit var tv : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(findViewById(R.id.toolbar))



        progress = findViewById<ProgressBar>(R.id.load_bar)
        progress.visibility = ProgressBar.VISIBLE

        val dividerItemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(this.getDrawable(R.drawable.item_decor)!!)
        rv = findViewById<RecyclerView>(R.id.list)
        rv.layoutManager = LinearLayoutManager(this)
        rv.addItemDecoration(dividerItemDecoration)

        ParseJSON()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

//    private fun fillList() : List<CourseInfo>{
//        val data = mutableListOf<CourseInfo>()
//        (0..30).forEach { i -> data.add(i, CourseInfo(i.toString(), i.toString())) }
//        return data
//    }
    @SuppressLint("LongLogTag")
    private fun ParseJSON()
    {

        tv = findViewById<TextView>(R.id.intro)
        val originalText = tv.text

        val spannable = SpannableString(originalText)
        spannable.setSpan(ForegroundColorSpan(Color.BLACK), 0, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        tv.text = spannable


        val retrofit = Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(RetrofitService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getdata()

            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(response.body())

                    Log.d("Pretty Printed JSON :", prettyJson)

                    val items = response.body()?.data
                    if(items != null){
                        for(i in 0 until items.count()){
                            val title = items[i].direction!!.title

                            var count = 0
                            for(j in 0 until items[i].groups!!.count() ){
                                count +=  items[i].groups!![j].items!!.size
                            }

                            val color = items[i].direction!!.badge!!.bgColor


                            val model = Cell(title, count, color)

                            itemsArr.add(model)

                            adapter = CustomAdapter(itemsArr)
                            adapter.notifyDataSetChanged()
                            progress.visibility = ProgressBar.INVISIBLE
                            tv.visibility = TextView.VISIBLE

                        }
                    }

                    rv.adapter = adapter
                    rv.visibility = RecyclerView.VISIBLE
                }

                else{
                    Log.e("Retrofit_Error", response.code().toString())
                }
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}