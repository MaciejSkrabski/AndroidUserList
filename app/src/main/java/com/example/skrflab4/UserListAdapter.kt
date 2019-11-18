package com.example.skrflab4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class UserListAdapter(val users: MutableList<User>) : RecyclerView.Adapter<UserListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.first_layout, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount():Int{
        return users.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position:Int){
        var user = users[position]
        holder.userIDText.text=user.id.toString()
        holder.userNameText.text=user.name
        holder.userSurnameText.text=user.surname
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val userIDText=itemView.findViewById<TextView>(R.id.number)
        val userNameText=itemView.findViewById<TextView>(R.id.imie)
        val userSurnameText=itemView.findViewById<TextView>(R.id.nazwisko)
    }











//https://developer.android.com/guide/topics/ui/layout/recyclerview


}

