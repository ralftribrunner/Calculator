package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()  ,View.OnClickListener{
    var operator=Operators.NIL
    lateinit var firstnumber:EditText
    lateinit var secondnumber:EditText

    override fun onSaveInstanceState(savedInstanceState: Bundle) {


        savedInstanceState.putString("first", firstnumber.getText().toString())
        savedInstanceState.putString("second", secondnumber.getText().toString())
        savedInstanceState.putString("operator", textView2.getText().toString())
        super.onSaveInstanceState(savedInstanceState)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id._1).setOnClickListener(this)
        findViewById<Button>(R.id._2).setOnClickListener(this)
        findViewById<Button>(R.id._3).setOnClickListener(this)
        findViewById<Button>(R.id._4).setOnClickListener(this)
        findViewById<Button>(R.id._5).setOnClickListener(this)
        findViewById<Button>(R.id._6).setOnClickListener(this)
        findViewById<Button>(R.id._7).setOnClickListener(this)
        findViewById<Button>(R.id._8).setOnClickListener(this)
        findViewById<Button>(R.id._9).setOnClickListener(this)
        findViewById<Button>(R.id.zero).setOnClickListener(this)
        findViewById<Button>(R.id.dzero).setOnClickListener(this)
        findViewById<Button>(R.id.minus).setOnClickListener(this)
        findViewById<Button>(R.id.multi).setOnClickListener(this)
        findViewById<Button>(R.id.divide).setOnClickListener(this)
        findViewById<Button>(R.id.clear).setOnClickListener(this)
        findViewById<Button>(R.id.plus).setOnClickListener(this)
        findViewById<Button>(R.id.answer).setOnClickListener(this)
        firstnumber = findViewById<EditText>(R.id.tv_first)
        secondnumber = findViewById<EditText>(R.id.tv_second)
        if (savedInstanceState != null) {
            Log.i("belép","ide")
            firstnumber.setText(savedInstanceState.getString("first"))
            secondnumber.setText(savedInstanceState.getString("second"))
            var temp:String
            when(savedInstanceState.getString("operator")) {
                "+" -> temp="+"
                "-" -> temp="-"
                "×" -> temp="×"
                "÷" -> temp="÷"
                "ANS" -> temp="ANS"
                else -> temp=""
            }
            textView2.setText(temp)
        }
    }



    override fun onClick(v: View) {
        when (v.id) {
            R.id._1 -> concat("1")
            R.id._2 -> concat("2")
            R.id._3 -> concat("3")
            R.id._4 -> concat("4")
            R.id._5 -> concat("5")
            R.id._6 -> concat("6")
            R.id._7 -> concat("7")
            R.id._8 -> concat("8")
            R.id._9 -> concat("9")
            R.id.zero -> concat("0")
            R.id.dzero -> concat("00")
            R.id.minus -> {/*if (!firstnumber.getText().toString().equals("") and (secondnumber.getText().toString().equals(""))) */operator=Operators.MINUS }
            R.id.multi -> {/*if (!firstnumber.getText().toString().equals("")and (secondnumber.getText().toString().equals("")))*/ operator=Operators.MULTI }
            R.id.divide -> {/*if (!firstnumber.getText().toString().equals("")and (secondnumber.getText().toString().equals(""))) */operator=Operators.DIV }
            R.id.plus -> {/*if (!firstnumber.getText().toString().equals("")and (secondnumber.getText().toString().equals("")))*/ operator=Operators.PLUS }
            R.id.clear -> clearinput()
            R.id.answer -> calculating()
        }
        updateView()
    }
    fun clearinput() {
        firstnumber.setText("")
        secondnumber.setText("")
        operator=Operators.NIL

//        if (operator.equals(Operators.NIL) or operator.equals(Operators.ANS)) {
//            firstnumber.setText("")
//        }
//        else {
//           secondnumber.setText("")
//        }

    }

    fun concat(x: String) {
        if (operator.equals(Operators.ANS)) {
            operator=Operators.NIL
            firstnumber.setText("");
            secondnumber.setText("");

        }
        if ((operator.equals(Operators.NIL) && currentFocus!=secondnumber) || currentFocus==firstnumber) {
            firstnumber.append(x)
        }
        else if (currentFocus==secondnumber || !operator.equals(Operators.NIL) || !operator.equals(Operators.ANS)){
            secondnumber.append(x)
        }
    }

    fun calculating():String{
        var ans=""
        if (secondnumber.getText().toString().equals("")) return firstnumber.getText().toString()
        if (operator.equals(Operators.PLUS))  ans= (firstnumber.getText().toString().toDouble()+secondnumber.getText().toString().toDouble()).toString()
        if (operator.equals(Operators.MULTI))  ans= (firstnumber.getText().toString().toDouble()*secondnumber.getText().toString().toDouble()).toString()
        if (operator.equals(Operators.MINUS))  ans= (firstnumber.getText().toString().toDouble()-secondnumber.getText().toString().toDouble()).toString()
        if (operator.equals(Operators.DIV))  ans= (firstnumber.getText().toString().toDouble()/secondnumber.getText().toString().toDouble()).toString()
        firstnumber.setText(ans)
        secondnumber.setText("")
        operator=Operators.ANS
        return ans
    }

    fun updateView() {

        var op:String
        when(operator) {
            Operators.ANS -> op="ANS"
            Operators.DIV -> op="÷"
            Operators.NIL -> op=""
            Operators.MINUS -> op="-"
            Operators.PLUS -> op="+"
            Operators.MULTI -> op="×"
        }
        textView2.setText(op)
    }

    fun setTexts(first: String, second: String, op: String) {
        firstnumber.setText(first)
        secondnumber.setText(second)
        when(op) {
            "+" -> operator=Operators.PLUS
            "-" -> operator=Operators.MINUS
            "×" -> operator=Operators.MULTI
            "÷" -> operator=Operators.DIV
            "ANS" -> operator=Operators.ANS
            else -> operator=Operators.NIL
        }
        updateView()
    }

    fun getFirst(): String {
        return firstnumber.getText().toString()
    }
    fun getSecond(): String {
        return secondnumber.getText().toString()
    }
    fun getOp(): Operators {
        return operator
    }



}

