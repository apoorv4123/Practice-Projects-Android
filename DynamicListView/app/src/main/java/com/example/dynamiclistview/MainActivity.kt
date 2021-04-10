package com.example.dynamiclistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val expenses = arrayOf(
            Expense("Transportation", 100f),
            Expense("Grocery", 250f),
            Expense("Garments", 150f),
            Expense("Mobile data", 120f),
            Expense("Maintenance", 180f),
            Expense("Electricity", 90f)
        )

        val expensesList: ListView = findViewById(R.id.expenses_lists)
        val expenseAdapter = ExpenseAdapter(expenses)
        expensesList.adapter = expenseAdapter

        total.text = Expense.total(expenses)

    }
}