package com.example.hmoodyscalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    fun onDijit(view: View) {
        val tvInput = findViewById<TextView>(R.id.tvInput)
        tvInput.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View) {
        val tvInput = findViewById<TextView>(R.id.tvInput)
        tvInput.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            val tvInput = findViewById<TextView>(R.id.tvInput)
            tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            val tvInput = findViewById<TextView>(R.id.tvInput)
            var tvValue = tvInput.text.toString()
            var prefix = ""


            try {

                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }



                if(tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()) {
                        one = prefix + one
                    }

//                    tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                    tvInput.text = (one.toDouble() - two.toDouble()).toString()
                }else if(tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

//                    tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                    tvInput.text = (one.toDouble() + two.toDouble()).toString()
                } else if(tvValue.contains("÷")) {
                    val splitValue = tvValue.split("÷")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

//                    tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                    tvInput.text = (one.toDouble() / two.toDouble()).toString()
                } else if(tvValue.contains("×")) {
                    val splitValue = tvValue.split("×")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

//                    tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                    tvInput.text = (one.toDouble() * two.toDouble()).toString()
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    fun onRemove(view: View) {
        var tvInput = findViewById<TextView>(R.id.tvInput)
        var tvValue = tvInput.text.toString()


        if(tvValue.isNotEmpty()) {
            tvInput.text = tvValue.substring(0, tvInput.length() - 1).toString()
        }
    }

//    private fun removeZeroAfterDot(result: String): String {
//        var value = result
//        if(result.contains("0"))
//            value = result.substring(0, result.length - 2)
//        return value
//    }


    fun onOperator(view: View) {
        val tvInput = findViewById<TextView>(R.id.tvInput)
        if (lastNumeric && !isOperatorAdded(tvInput.text.toString())) {
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("÷")  || value.contains("+") || value.contains("-") || value.contains("×")

        }
    }
}