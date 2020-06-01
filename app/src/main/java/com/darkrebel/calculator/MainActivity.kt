package com.darkrebel.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Numbers
        tv0.setOnClickListener { appendoninput("0",true)}
        tv1.setOnClickListener { appendoninput("1",true)}
        tv2.setOnClickListener { appendoninput("2",true)}
        tv3.setOnClickListener { appendoninput("3",true)}
        tv4.setOnClickListener { appendoninput("4",true)}
        tv5.setOnClickListener { appendoninput("5",true)}
        tv6.setOnClickListener { appendoninput("6",true)}
        tv7.setOnClickListener { appendoninput("7",true)}
        tv8.setOnClickListener { appendoninput("8",true)}
        tv9.setOnClickListener { appendoninput("9",true)}

        tvdot.setOnClickListener { appendoninput(".",true)}

        //Operators
        tvplus.setOnClickListener { appendoninput("+",false)}
        tvminus.setOnClickListener { appendoninput("-",false)}
        tvmultiply.setOnClickListener { appendoninput("*",false)}
        tvdivide.setOnClickListener { appendoninput("/",false)}
        tvobracket.setOnClickListener { appendoninput("(",false)}
        tvcbracket.setOnClickListener { appendoninput(")",false)}

        //Clear
        tvclear.setOnClickListener {
            inputTextView.text = ""
            outputTextView.text = ""
        }

        //Back
        tvback.setOnClickListener {
            val string = inputTextView.text.toString()
            if (string.isNotEmpty()){
                inputTextView.text=string.substring(0,string.length-1)
            }
            outputTextView.text = ""
        }

        //Equal
        tvequal.setOnClickListener {
            try {
                val inputExpress = ExpressionBuilder(inputTextView.text.toString()).build()
                val outputExpress = inputExpress.evaluate()
                val longoutput = outputExpress.toLong()
                if (outputExpress == longoutput.toDouble())
                    outputTextView.text = longoutput.toString()
                else
                    outputTextView.text = outputExpress.toString()

            }catch (e:Exception){
                Log.d("Exception", "message : " + e.message)
            }
        }

    }

    fun appendoninput( string: String,  canClear: Boolean){

        if (outputTextView.text.isNotEmpty()){
            inputTextView.text = ""
        }

        if(canClear){
            outputTextView.text= ""
            inputTextView.append(string)
        }
        else{
            inputTextView.append(outputTextView.text)
            inputTextView.append(string)
            outputTextView.text= ""

        }

    }
}
