package com.example.androidlecture_8_retrofit.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import com.example.androidlecture_8_retrofit.JokeApp
import com.example.androidlecture_8_retrofit.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as JokeApp).viewModel
        val button = findViewById<Button>(R.id.actionButton)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val textView = findViewById<TextView>(R.id.textView)
        val checkBox = findViewById<CheckBox>(R.id.checkbox)
        val changeButton = findViewById<ImageButton>(R.id.changeButton)
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.choseFavorites(isChecked)
        }
        progressBar.visibility = View.VISIBLE

        button.setOnClickListener {
            button.isEnabled = false
            progressBar.visibility = View.VISIBLE
            viewModel.getJoke()
        }

        changeButton.setOnClickListener {
            viewModel.changeJokeStatus()
        }


        viewModel.init(object : DataCallback {
            override fun provideText(text: String)  {
                button.isEnabled = true
                progressBar.visibility = View.INVISIBLE
                textView.text = text
            }

            override fun provideIconRes(id: Int)  {
                changeButton.setImageResource(id)
            }
        })
    }


    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }
}