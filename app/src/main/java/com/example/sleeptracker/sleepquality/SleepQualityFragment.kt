package com.example.sleeptracker.sleepquality

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sleeptracker.database.SleepDatabase
import com.example.sleeptracker.databinding.SleepQualityBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SleepQualityFragment : Fragment() {

    private lateinit var binding: SleepQualityBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = SleepQualityBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        val args = SleepQualityFragmentArgs.fromBundle(arguments!!)
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao()
        val viewModelFactory = SleepQualityViewModelFactory(args.sleepNightKey, dataSource)
        val sleepQualityViewModel =
            ViewModelProvider(this, viewModelFactory)[SleepQualityViewModel::class.java]
        sleepQualityViewModel.navigateToSleepTracker.observe(viewLifecycleOwner) {
            if (it == true) {
                this.findNavController()
                    .navigate(SleepQualityFragmentDirections.actionSleepQualityFragmentToTrackerFragment())
                sleepQualityViewModel.doneNavigating()
            }
        }
        binding.sleepQualityViewModel =sleepQualityViewModel
        binding.lifecycleOwner = this
        return binding.root

    }
}