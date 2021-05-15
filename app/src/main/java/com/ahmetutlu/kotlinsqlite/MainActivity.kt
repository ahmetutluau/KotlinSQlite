package com.ahmetutlu.kotlinsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val myDatabase=this.openOrCreateDatabase("Musicians", MODE_PRIVATE,null)
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY,name VARCHAR,age INT) ")
            myDatabase.execSQL("INSERT INTO musicians(name,age) VALUES('lara',22)")
            myDatabase.execSQL("UPDATE musicians SET age=61 WHERE id=2")
            myDatabase.execSQL("DELETE FROM musicians WHERE name='james'")

            val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE name LIKE '%s'",null)
            // WHERE id= 5 (id si 5 olanı çağır) && WHERE name LIKE '%s'(sonu s ile biteni çağır) && WHERE name LIKE 'k%'(k ile başlyanları çağır)
            val nameIx= cursor.getColumnIndex("name")
            val ageIx= cursor.getColumnIndex("age")
            val idIx= cursor.getColumnIndex("id")

            while (cursor.moveToNext()){
                println("Name:"+cursor.getString(nameIx))
                println("Age:"+cursor.getInt(ageIx))
                println("Id:"+cursor.getInt(idIx))
            }

        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}