package com.example.atry

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.*

import android.net.Uri

import android.widget.EditText
import android.widget.Toast
import com.example.atry.StudentsProvider.Companion.STUDENT_ID

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickAddName(view: View) {
        val cols = listOf<String>(StudentsProvider._ID,StudentsProvider.NAME,StudentsProvider.GRADE
        ).toTypedArray()

        val values = ContentValues()
        values.put(StudentsProvider.NAME, (findViewById<View>(R.id.editText2) as EditText).text.toString()
        )
        values.put(StudentsProvider.GRADE, (findViewById<View>(R.id.editText) as EditText).text.toString()
        )
        val uri = contentResolver.insert(StudentsProvider.CONTENT_URI, values)
        var rs = contentResolver.query(StudentsProvider.CONTENT_URI, cols, null, null, cols[0])
        if (rs?.moveToNext() == true)
        Toast.makeText(applicationContext,rs.getString(1),Toast.LENGTH_SHORT).show()
    }







    @SuppressLint("Range")
    fun onClickRetrieveStudents(view: View?) {
        // Retrieve student records
        val URL = "content://com.example.try.StudentsProvider"
        val students = Uri.parse(URL)
        //\  val c = contentResolver!!.query(students,null,null,null,"name")
        var a = StudentsProvider()

        var c = a.query(students, null, null, null, null)
        //val //c = managedQuery(students, null, null, null, "name")
        if (c != null) {
            if (c?.moveToFirst()!!) {
                do {
                    Toast.makeText(
                        this,
                        c.getString(c.getColumnIndex(StudentsProvider._ID)) +

                                ", " + c.getString(c.getColumnIndex(StudentsProvider.NAME)) + ", "

                                + c.getString(c.getColumnIndex(StudentsProvider.GRADE)),
                        Toast.LENGTH_SHORT
                    ).show()
                } while (c.moveToNext())
            }
        }
    }
}







