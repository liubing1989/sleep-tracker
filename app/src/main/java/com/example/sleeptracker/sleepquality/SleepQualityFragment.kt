package com.example.sleeptracker.sleepquality

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sleeptracker.R
import com.example.sleeptracker.databinding.SleepQualityBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SleepQualityFragment : Fragment() {

    private var _binding: SleepQualityBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = SleepQualityBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}