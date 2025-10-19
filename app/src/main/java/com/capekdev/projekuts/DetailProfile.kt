package com.capekdev.projekuts

import android.R
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capekdev.projekuts.databinding.ActivityDetailProfileBinding
import com.capekdev.projekuts.databinding.ActivityMainBinding
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog

class DetailProfile : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProfileBinding
    companion object{
        var JUMLAH_TEMAN = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val index = intent.getIntExtra("profileIndex", 0)
        binding.txtNama.setText(ProfileBank.profiles[index].nama)
        binding.txtNRP.setText(ProfileBank.profiles[index].nrp)
        binding.imgProfile.setImageResource(ProfileBank.profiles[index].imageId)
        val deskripsi = arrayOf("About Me", "My Course", "My Experience")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, deskripsi)
        binding.txtDeskripsi.setText(ProfileBank.profiles[index].aboutMe)
        binding.spinner.adapter = adapter
        when(ProfileBank.profiles[index].program){
            "Program DSAI" -> binding.radioDSAI.isChecked = true
            "Program NCS" -> binding.radioNCS.isChecked = true
            "Program IMES" -> binding.radioIMES.isChecked = true
            "Program DMT" -> binding.radioDMT.isChecked = true
            "Program GD" -> binding.radioGD.isChecked = true
        }
        binding.spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    0 -> binding.txtDeskripsi.setText(ProfileBank.profiles[index].aboutMe)
                    1 -> binding.txtDeskripsi.setText(ProfileBank.profiles[index].myCourse)
                    2 -> binding.txtDeskripsi.setText(ProfileBank.profiles[index].myExp)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        binding.btnAddFriend.setOnClickListener {
            val profileName = ProfileBank.profiles[index].nama
            JUMLAH_TEMAN++
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Friend Request")
            builder.setMessage("Sukses tambah $profileName sebagai friend. Friend anda sekarang adalah " + JUMLAH_TEMAN +".")
            builder.setPositiveButton("OK") { dialog, which ->
                dialog.dismiss()
            }
            builder.show()
        }
    }
}