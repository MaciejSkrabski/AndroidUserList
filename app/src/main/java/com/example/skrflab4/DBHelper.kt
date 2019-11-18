package com.example.skrflab4

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class DBHelper(context: Context): SQLiteOpenHelper(context, DATA_BASE_NAME,null, DATA_BASE_VERSION) {

    companion object{
        internal  const val DATA_BASE_NAME="users.db"
        internal const val DATA_BASE_VERSION=1
        internal const val TABLE_NAME="users"
        internal const val COL_ID="id"
        internal const val COL_NAME="name"
        internal const val COL_SUR_NAME="surname"

    }

    override fun onCreate(db: SQLiteDatabase?){
        val sql=db!!.execSQL(
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME("+
                    "$COL_ID INTEGER PRIMARY KEY,"+
                    "$COL_NAME TEXT NOT NULL,"+
                    "$COL_SUR_NAME TEXT NOT NULL)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int){
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)


    }
    val allUsers: MutableList<User>
        get(){
            val query="SELECT * FROM $TABLE_NAME"
            val users= mutableListOf<User>()
            val db=this.writableDatabase
            val cursor=db.rawQuery(query,null)

            if(cursor.moveToFirst()){
                do{
                    val id=cursor.getInt(cursor.getColumnIndex(COL_ID))
                    val name=cursor.getString(cursor.getColumnIndex(COL_NAME))
                    val surname=cursor.getString(cursor.getColumnIndex(COL_SUR_NAME))
                    val user=User(id,name,surname)
                    users.add(user)

                }while(cursor.moveToNext())
            }
            db.close()
            return users
        }

    fun getUserById(id:Int):User?{
        val db=this.readableDatabase
        val query="SELECT * FROM $TABLE_NAME WHERE $COL_ID=$id"
        val cursor=db.rawQuery(query,null)
        if(cursor.moveToFirst()){
            val name=cursor.getString(cursor.getColumnIndex(COL_NAME))
            val surname=cursor.getString(cursor.getColumnIndex(COL_SUR_NAME))
            cursor.close()
            db.close()
            return User(id,name,surname)
        }
        db.close()
        return null
    }

    fun editUser(user: User?,name: String,surname: String){
        if(user is User){
            val db=this.writableDatabase
            val value= contentValuesOf()
            value.put(COL_NAME,user.name)
            value.put(COL_SUR_NAME,user.surname)
            user.name=name
            user.surname=surname
            db.update(TABLE_NAME,value, COL_ID + "=?", arrayOf(user.id.toString()))
            db.close()

        }
    }



    fun addUser(user: User):Long{

        val db=this.writableDatabase
        val value= contentValuesOf()
        value.put(COL_NAME,user.name)
        value.put(COL_SUR_NAME,user.surname)
        val result=db.insert(TABLE_NAME,null,value)
        // user id=result.toInt()
        return result
    }
}