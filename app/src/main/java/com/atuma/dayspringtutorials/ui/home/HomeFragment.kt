package com.atuma.dayspringtutorials.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

//import androidx.fragment.app

import com.atuma.dayspringtutorials.R
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val homeViewModel: HomeViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)


        val partOneCourses: TextView = root.findViewById(R.id.part_one_courses)
        val partTwoCourses: TextView = root.findViewById(R.id.part_two_courses)
        val partThreeCourses: TextView = root.findViewById(R.id.part_three_courses)


        partOneCourses.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_partOneCoursesFragment)
        }


        partTwoCourses.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_partTwoCoursesFragment)
        }

        partThreeCourses.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_partThreeCoursesFragment)
        })


        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().title = getString(R.string.home)
    }
}