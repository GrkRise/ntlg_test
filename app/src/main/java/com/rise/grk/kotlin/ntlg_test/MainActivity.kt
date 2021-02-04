package com.rise.grk.kotlin.ntlg_test

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val tv = findViewById<TextView>(R.id.intro)
        val originalText = tv.text

        val spannable = SpannableString(originalText)
        spannable.setSpan(ForegroundColorSpan(Color.BLACK), 0, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        tv.text = spannable

        val dividerItemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(this.getDrawable(R.drawable.item_decor)!!)

        val rv = findViewById<RecyclerView>(R.id.list)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = CustomAdapter(fillList())
        rv.addItemDecoration(dividerItemDecoration)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    private fun fillList() : List<CourseInfo>{
        val data = mutableListOf<CourseInfo>()
        (0..30).forEach { i -> data.add(i, CourseInfo(i.toString(), i.toString())) }
        return data
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