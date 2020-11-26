package com.example.tugasqatros

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.tugasqatros.databinding.ActivityMainBinding
import com.example.tugasqatros.detail.AddNewActivity
import com.example.tugasqatros.detail.UpdateActivity
import com.example.tugasqatros.detail.UpdateActivity.Companion.mGoods
import com.example.tugasqatros.detail.UpdateActivity.Companion.mId
import com.example.tugasqatros.detail.UpdateActivity.Companion.mName
import com.example.tugasqatros.detail.UpdateActivity.Companion.mSupplier
import com.example.tugasqatros.detail.UpdateActivity.Companion.mTime
import com.example.tugasqatros.model.EntityGoods
import com.example.tugasqatros.util.ViewModelFactory
import es.dmoral.toasty.Toasty

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModelMain: ViewModelMain
    private lateinit var adapterMain: AdapterMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelMain = ViewModelProvider(this, ViewModelFactory.getInstance(this))[ViewModelMain::class.java]

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, AddNewActivity::class.java)
            startActivity(intent)
        }

        showRecyclerview()
    }

    private fun showRecyclerview(){
        observe()
        with(binding) {
            adapterMain = AdapterMain()
            rvData.layoutManager =
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            rvData.adapter = adapterMain

            adapterMain.setOnItemClickCallback(object : AdapterMain.OnItemClickCallback{
                override fun onItemClicked(item: EntityGoods) {
                    val intent = Intent(this@MainActivity, UpdateActivity::class.java)
                    intent.putExtra(mId, item.id)
                    intent.putExtra(mName, item.name)
                    intent.putExtra(mGoods, item.goods)
                    intent.putExtra(mSupplier, item.supplier)
                    intent.putExtra(mTime, item.time)
                    startActivity(intent)
                }
            })
        }
    }

    private fun observe(){
        viewModelMain.getAllData().observe(this, {
            adapterMain.setData(it)
        })
    }
}