package com.joseph.healthawareness

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_hospitals.*

class Hospitals : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospitals)
         //create an array of hospitals
        val hospitals = arrayOf<String> ("KNH","Mater","Nairobi Hospital","Meridian","Mediheal","Kiambu")

        //you need an adapter, observe below thjta we pass the 'hospitals' array above to the adapter , towards the end of below line
        val arrayAdapter  = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, hospitals)

        //set the adapter on line 16 to your listview
        listhosi.adapter  = arrayAdapter

        //add a list listner
        listhosi.onItemClickListener = AdapterView.OnItemClickListener{parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            //position is what was clicked, counting from 0
            if (position==0){//knh
                //intent - explicit, implicit
                val i = Intent(Intent.ACTION_VIEW, Uri.parse("geo:-1.2054030000000047, 36.854067000000015z=15"))
                startActivity(i)
            }

            if (position==1){ //mater
                //intent - explicit, implicit
                val i = Intent(Intent.ACTION_VIEW, Uri.parse("geo:-1.2054030000000047, 36.854067000000015z=15"))
                startActivity(i)
            }
            //more here
            if (position==2){ //nairobi hosi
                //intent - explicit, implicit
                val i = Intent(this, About::class.java) //example
                startActivity(i)
            }

            //do more ifs
        };


    }
}