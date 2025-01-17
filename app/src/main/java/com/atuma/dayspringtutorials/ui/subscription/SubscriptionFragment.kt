package com.atuma.dayspringtutorials.ui.subscription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.atuma.dayspringtutorials.R

class SubscriptionFragment : Fragment() {

    private lateinit var subscriptionViewModel: SubscriptionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        subscriptionViewModel =
            ViewModelProviders.of(this).get(SubscriptionViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_send, container, false)
        val textView: TextView = root.findViewById(R.id.text_send)
        subscriptionViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}