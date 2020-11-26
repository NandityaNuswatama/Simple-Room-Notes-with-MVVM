package com.example.tugasqatros.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.tugasqatros.MainActivity
import com.example.tugasqatros.R
import com.example.tugasqatros.ViewModelMain
import com.example.tugasqatros.databinding.ActivityAddNewBinding
import com.example.tugasqatros.model.EntityGoods
import com.example.tugasqatros.util.ViewModelFactory
import es.dmoral.toasty.Toasty

class AddNewActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityAddNewBinding
    private lateinit var viewModelMain: ViewModelMain

    companion object {
        val listOfName = arrayOf("Anthony", "Fulan", "Joko", "Mike", "Samsul")
    }

    private var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelMain = ViewModelProvider(this, ViewModelFactory.getInstance(this))[ViewModelMain::class.java]

        setSpinner()

        binding.btnSave.setOnClickListener {
            if (binding.inputGoods.text.isEmpty()) {
                showWarningToasty("Goods list must be fill")
            }
            if (binding.inputSuplier.text.isEmpty()){
                showWarningToasty("Supplier must be fill")
            }
            if (binding.inputTimeDate.text.isEmpty()){
                showWarningToasty("Time and Date must be fill")
            }
            else {
                insertData()
                Toasty.success(this, "Data saved successfully", Toasty.LENGTH_SHORT).show()
                closeKeyBoard()
                onBackPressed()
            }

        }
    }

    private fun setSpinner(){
        binding.spinner.onItemSelectedListener = this
        val arrayAdapter =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listOfName)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = arrayAdapter
    }

    private fun insertData(){
        with(binding) {
            val data = EntityGoods(
                name = name,
                goods = inputGoods.text.toString(),
                supplier = inputSuplier.text.toString(),
                time = inputTimeDate.text.toString()
            )
            viewModelMain.insert(data)
        }
    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun showWarningToasty(message: String){
        Toasty.warning(this, message, Toasty.LENGTH_SHORT).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        name = listOfName[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}