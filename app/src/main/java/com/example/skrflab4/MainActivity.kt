package com.example.skrflab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    fun toastMsg(msg: String) {

        val toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
        toast.show()
    }






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val b=findViewById(R.id.buttonOK) as Button
        buttonOK.text=savedInstanceState?.getString("WOLOLO!")?:"haba!"
        b.setOnClickListener {
            Toast.makeText(this, "You clicked me.", Toast.LENGTH_SHORT).show()
            b.text="WOLOLO!"

        }

        buttonOK.text=savedInstanceState?.getString("WOLOLO!")?:"haba!"

        lista.layoutManager=LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        var users = mutableListOf<User>(User(1, "haba","kaba"), User(2, "iks","igrek"))
        val adapter = UserListAdapter(users)
        lista.adapter = adapter

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("imie", buttonOK.text.toString())
        outState.putSerializable(buttonOK.text as String?, buttonOK.text.toString())
    }
}
