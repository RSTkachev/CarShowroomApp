package com.example.carshowroom.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.carshowroom.databinding.FragmentFavoriteCarsBinding

class FavoriteCarsFragment : Fragment() {

    private var _binding: FragmentFavoriteCarsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoriteCarsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

}