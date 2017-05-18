package com.wmbest2.calc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import kotlinx.android.synthetic.main.activity_calculator.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.textChangedListener

class CalculatorActivity : AppCompatActivity() {

    val calculator = Calculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        plus.onClick {
            calculator.plus()
            result.text = ""
        }
        enter.onClick {
            val result = calculator.enter()
            updateDisplay(result)
            calculator.clear()
            calculator.inputValue = result
        }
        clear.onClick {
            result.text = ""
            calculator.clear()
        }

        result.textChangedListener {
            onTextChanged { value, _, _, _ ->
                if (value.isNullOrBlank()) {
                    calculator.inputValue = Double.NaN
                } else {
                    calculator.inputValue = value.toString().toDouble()
                }
            }
        }

        one.onClick { numberClicked(it) }
        two.onClick { numberClicked(it) }
        three.onClick { numberClicked(it) }
        four.onClick { numberClicked(it) }
        five.onClick { numberClicked(it) }
        six.onClick { numberClicked(it) }
        seven.onClick { numberClicked(it) }
        eight.onClick { numberClicked(it) }
        nine.onClick { numberClicked(it) }
        zero.onClick { numberClicked(it) }
        period.onClick { numberClicked(it) }
    }

    fun numberClicked(view: View?) = when(view?.id) {
        R.id.one -> addDigit(1)
        R.id.two -> addDigit(2)
        R.id.three -> addDigit(3)
        R.id.four -> addDigit(4)
        R.id.five -> addDigit(5)
        R.id.six-> addDigit(6)
        R.id.seven -> addDigit(7)
        R.id.eight -> addDigit(8)
        R.id.nine -> addDigit(9)
        R.id.zero -> addDigit(0)
        R.id.period -> addPeriod()
        else -> {}
    }

    fun addDigit(value: Int) {
        clearNaN()
        result.text = result.text.toString() + value
    }

    fun addPeriod() {
        clearNaN()
        if (!result.text.contains('.')) {
            result.text = result.text.toString() + "."
        }
    }

    fun clearNaN() {
        if (result.text.isNotBlank()) {
            val resultValue = result.text.toString().toDouble()
            if (resultValue.isInfinite() || resultValue.isNaN()) {
                result.text = ""
            }
        }
    }

    fun updateDisplay(value: Double) {
        result.text = value.toString()
    }
}

class Calculator {
    var inputValue = Double.NaN
    var operations = mutableListOf<Expr>()

    fun plus() {
        if (inputValue.isFinite()) {
            operations.add(Const(inputValue))
            operations.add(Sum())
        }
    }

    fun enter(): Double {
        operations.add(Const(inputValue))
        var currentOperation: Expr = NoOp
        var result = 0.0

        for (op in operations) {
            when (op) {
                is Const -> {
                    if (currentOperation is NoOp) {
                        result = op.number
                    } else {
                        result = eval(currentOperation, result, op.number)
                    }
                }
                else -> currentOperation = op
            }
        }
       	return result
    }

    fun clear() {
        operations.clear()
        inputValue = Double.NaN
    }

}

sealed class Expr
data class Const(val number: Double) : Expr()
class Sum() : Expr()
object NoOp: Expr()
object NotANumber : Expr()

fun eval(expr: Expr, e1: Double, e2: Double): Double = when (expr) {
    is Sum -> e1 + e2
    else -> Double.NaN
}
