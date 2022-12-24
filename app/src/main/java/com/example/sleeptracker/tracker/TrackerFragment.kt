package com.example.sleeptracker.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sleeptracker.R
import com.example.sleeptracker.database.SleepDatabase
import com.example.sleeptracker.databinding.SleepTrackerBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class TrackerFragment : Fragment() {

    private lateinit var binding: SleepTrackerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = SleepTrackerBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao()
        val trackerFactory = TrackerViewModelFactory(dataSource, application)
        val trackerViewModel = ViewModelProvider(this, trackerFactory)[TrackerViewModel::class.java]
        val adapter = SleepNightAdapter()
        binding.sleepList.adapter = adapter
        trackerViewModel.nights.observe(viewLifecycleOwner) {
            it?.let { adapter.submitList(it) }
        }
        binding.sleepTrackerViewModel = trackerViewModel
        trackerViewModel.showSnackBarEvent.observe(viewLifecycleOwner) {
            if (it == true) { // Observed state is true.
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()
                trackerViewModel.doneShowingSnackbar()
            }
        }
        trackerViewModel.navigateToSleepQuality.observe(viewLifecycleOwner) { night ->
            night?.let {
                this.findNavController().navigate(
                    TrackerFragmentDirections.actionTrackerFragmentToSleepQualityFragment(night.nightId))
                trackerViewModel.doneNavigating()
            }
        }
        binding.lifecycleOwner = this
        return binding.root
    }
}