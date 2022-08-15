package com.dicoding.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvResult: TextView

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == MoveForResultActivity.RESULT_CODE && result.data != null) {
            val selectedValue = result.data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
            "Hasil: $selectedValue".also { tvResult.text = it }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveWithObject: Button = findViewById(R.id.btn_move_with_object)
        btnMoveWithObject.setOnClickListener(this)

        val btnMoveWithResult:  Button = findViewById(R.id.btn_move_with_result)
        btnMoveWithResult.setOnClickListener(this)

        tvResult = findViewById(R.id.tv_result)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_move_with_object -> {
                val persons = ArrayList<Person>()

                persons.add(Person("Wahyu Amirulloh", 16, "wahyu@gmail.com", "Jakarta"))
                persons.add(Person("Yaskur Fauzan", 15, "yaskur@gmail.com", "Bogor"))

                val moveWithObjectIntent = Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                moveWithObjectIntent.putParcelableArrayListExtra(MoveWithObjectActivity.EXTRA_PERSON, persons)
                startActivity(moveWithObjectIntent)
            }

            R.id.btn_move_with_result -> {
                val moveWithResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
                resultLauncher.launch(moveWithResultIntent)
            }
        }
    }
}