package com.example.list_with_side_apphabet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mLayoutManager = LinearLayoutManager(this)
        rv_list.setLayoutManager(mLayoutManager)
        val myDataset = ArrayList<String>()
        for (i in 0..25) {
            for (j in 1..10) {
                myDataset.add(Character.toString((65 + i).toChar()) + " $j Row item")
            }
        }
        var mAdapter = MyAdapter(myDataset)
        rv_list.setAdapter(mAdapter)
        rv_list.setItemAnimator(DefaultItemAnimator())

        fast_scroller.setRecyclerView(rv_list)

        val mAlphabetItems = ArrayList<AlphabetItem>()
        val strAlphabets = ArrayList<String>()
        for (i in 0 until myDataset.size) {
            val name = myDataset.get(i)
            if (name == null || name!!.trim({ it <= ' ' }).isEmpty())
                continue

            val word = name!!.substring(0, 1)
            if (!strAlphabets.contains(word)) {
                strAlphabets.add(word)
                mAlphabetItems.add(AlphabetItem(i, word, false))
            }
        }

        fast_scroller.setUpAlphabet(mAlphabetItems)

    }
}
