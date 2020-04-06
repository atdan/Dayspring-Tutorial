package com.atuma.dayspringtutorials.ui.accomodation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.atuma.dayspringtutorials.HomeActivity
import com.atuma.dayspringtutorials.R

class AccomodationFragment : Fragment() {

    private lateinit var accomodationViewModel: AccomodationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        accomodationViewModel =
            ViewModelProviders.of(this).get(AccomodationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_share, container, false)
        val textView: TextView = root.findViewById(R.id.text_share)
        accomodationViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        (activity as HomeActivity).supportActionBar?.title = "Accommodation"

        return root
    }
}