package com.example.carshowroom.ui.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.carshowroom.databinding.FragmentMessageCentreBinding

class MessageCentreFragment : Fragment() {

    private var _binding: FragmentMessageCentreBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMessageCentreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

}