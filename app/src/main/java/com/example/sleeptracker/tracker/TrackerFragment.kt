package com.example.sleeptracker.tracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sleeptracker.R
import com.example.sleeptracker.database.SleepDatabase
import com.example.sleeptracker.databinding.SleepTrackerBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class TrackerFragment : Fragment() {

    private var _binding: SleepTrackerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = SleepTrackerBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao()
        val trackerFactory = TrackerViewModelFactory(dataSource, application)
        val trackerViewModel = ViewModelProvider(this, trackerFactory)[TrackerViewModel::class.java]
        binding.startButton.setOnClickListener {
            trackerViewModel.onStartTracking()
        }
        binding.stopButton.setOnClickListener {
            trackerViewModel.onStopTracking()
        }
        binding.clearButton.setOnClickListener {
            trackerViewModel.onClear()
        }
        trackerViewModel.nightsString.observe(viewLifecycleOwner){
            binding.textview.text = it
        }
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}