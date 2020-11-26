package com.example.tugasqatros.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.tugasqatros.MainActivity
import com.example.tugasqatros.R
import com.example.tugasqatros.ViewModelMain
import com.example.tugasqatros.databinding.ActivityUpdateBinding
import com.example.tugasqatros.model.EntityGoods
import com.example.tugasqatros.util.ViewModelFactory
import es.dmoral.toasty.Toasty

class UpdateActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var viewModelMain: ViewModelMain

    var name = ""

    private val requestDelete = 1
    private val requestUpdate = 3
    private val requestBack = 5

    companion object{
        const val mId = "mId"
        const val mName = "mName"
        const val mSupplier = "mSupplier"
        const val mGoods = "mGoods"
        const val mTime = "mTime"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelMain = ViewModelProvider(this, ViewModelFactory.getInstance(this))[ViewModelMain::class.java]

        setSpinner()
        setData()

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
                showAlertDialog("Update Data", "Are you sure to update this data?", requestUpdate)
                closeKeyBoard()
            }
        }

        binding.btnDelete.setOnClickListener {
            showAlertDialog("Delete Data", "This data will be deleted permanently", requestDelete)
        }
    }

    override fun onBackPressed() {
        showAlertDialog("Save Update", "Changed data not save yet", requestBack)
    }

    private fun setSpinner(){
        binding.spinner.onItemSelectedListener = this
        val arrayAdapter =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, AddNewActivity.listOfName)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = arrayAdapter
    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun updateData(){
        with(binding) {
            val data = EntityGoods(
                id = intent.getIntExtra(mId, 0),
                name = name,
                goods = inputGoods.text.toString(),
                supplier = inputSuplier.text.toString(),
                time = inputTimeDate.text.toString()
            )
            viewModelMain.insert(data)
            closeKeyBoard()

        }
    }

    private fun deleteById(id: Int){
        viewModelMain.deleteById(id)
    }

    private fun setData(){
        with(binding){
            inputGoods.setText(intent.getStringExtra(mGoods).toString())
            inputSuplier.setText(intent.getStringExtra(mSupplier).toString())
            inputTimeDate.setText(intent.getStringExtra(mTime).toString())
        }
    }

    private fun backToMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun showAlertDialog(title: String, message: String, requestCode: Int){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Proceed"){ _, _, ->
            when(requestCode){
                requestDelete -> {
                    deleteById(intent.getIntExtra(mId,1))
                    backToMainActivity()
                }
                requestUpdate -> {
                    updateData()
                    Toasty.success(this, "Data is updated", Toasty.LENGTH_SHORT).show()
                    backToMainActivity()
                }
                requestBack -> {
                    backToMainActivity()
                    super.onBackPressed()
                }
            }
        }
        builder.setNegativeButton("Cancel"){dialog, _, ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun showWarningToasty(message: String){
        Toasty.warning(this, message, Toasty.LENGTH_SHORT).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        name = AddNewActivity.listOfName[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}