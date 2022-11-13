package com.example.carshowroom.ui.contact

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.carshowroom.databinding.FragmentContactBinding

class ContactFragment : Fragment() {

    private var _binding: FragmentContactBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentContactBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.loc.clipToOutline = true

        binding.loc.setOnClickListener() {
            val gmmIntentUri = Uri.parse("geo:47.203107298160376, 38.93437261617795?q=Корпус Г")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
        binding.locText.setOnClickListener() {
            val gmmIntentUri = Uri.parse("geo:47.203107298160376, 38.93437261617795?q=Корпус Г")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
        binding.phone.setOnClickListener() {
            val call = Intent(Intent.ACTION_DIAL)
            call.setData(Uri.parse("tel:" + "+7 (863) 237-03-70"))
            startActivity(call)
        }

        return root
    }

}