package com.cdp.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {
    private var lone : TextView?=null
    private var l3 : TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val btnpicker : Button = findViewById(R.id.btnDatepicker)
        lone = findViewById(R.id.lbl1)
        val l2 : TextView = findViewById(R.id.lbl2)
         l3  = findViewById(R.id.lbl3)
        val l4 : TextView = findViewById(R.id.lbl4)


        btnpicker.setOnClickListener {
            clickDatePicker()

        }

    }

    private fun clickDatePicker(){

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            { _: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->

                Toast.makeText(this,"Date Selected",Toast.LENGTH_LONG).show()

                val selectedDate = "$dayOfMonth/${month+1}/$year"
                lone?.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)

                theDate?.let {
                    val selectedDateInMinutes = theDate.time/60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time/60000
                        val differentInminutes = currentDateInMinutes - selectedDateInMinutes

                        l3?.text = differentInminutes.toString()
                    }

                }


            },
            year,
            month,
            day

        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()


    }


}
