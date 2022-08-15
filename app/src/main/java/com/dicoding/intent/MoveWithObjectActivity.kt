package com.dicoding.intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MoveWithObjectActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_PERSON = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_object)

        val tvObjectReceived: TextView = findViewById(R.id.tv_object_received)

        val persons = intent.getParcelableArrayListExtra<Person>(EXTRA_PERSON) as ArrayList<Person>
        var text = ""

        for ((index, person) in persons.withIndex()) {
            text += "${index + 1}. ${person.name}\n     E-Mail: ${person.email}\n     Age: ${person.age}\n     Location: ${person.city}\n\n"
        }

        tvObjectReceived.text = text
    }
}