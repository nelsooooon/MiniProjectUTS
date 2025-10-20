package com.capekdev.projekuts

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capekdev.projekuts.databinding.ProfileCardBinding

class ProfileAdapter(): RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ProfileCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.binding.imgProfile.setImageResource(ProfileBank.profiles[position].imageId)
        holder.binding.txtNama.setText(ProfileBank.profiles[position].nama)
        holder.binding.txtNRP.setText("NRP " + ProfileBank.profiles[position].nrp)
        holder.binding.txtProgram.setText("Program " + ProfileBank.profiles[position].program)
        holder.binding.cardProfile.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailProfile::class.java)
            intent.putExtra("profileIndex", position)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return ProfileBank.profiles.size
    }

    class ProfileViewHolder(val binding: ProfileCardBinding): RecyclerView.ViewHolder(binding.root)
}