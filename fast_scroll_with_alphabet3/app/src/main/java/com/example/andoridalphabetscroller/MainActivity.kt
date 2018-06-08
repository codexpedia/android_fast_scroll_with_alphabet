package com.example.andoridalphabetscroller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val itemList = ArrayList<String>()
    val linearLayoutManager = LinearLayoutManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemArrayAdapter = ItemArrayAdapter(R.layout.list_item, itemList)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = itemArrayAdapter

        val letters = arrayOf("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z")
        letters.forEach {
            for (i in 0..20) {
                itemList.add("$it Item $i")
            }
        }
    }

    fun onScrollbarLettterClick(v: View) {
        val firstLetter = v.getTag() as String
        var index = 0
        if (itemList != null) {
            for (string in itemList) {
                if (string.startsWith(firstLetter.toLowerCase())) {
                    index = itemList.indexOf(string)
                    break
                }
            }
        }
        linearLayoutManager.scrollToPositionWithOffset(index, 20)
    }
}
