package com.atuma.dayspringtutorials.ui.accomodation

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.atuma.dayspringtutorials.HomeActivity
import com.atuma.dayspringtutorials.R
import com.atuma.dayspringtutorials.adapter.AccommodationAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_accomodation.view.*
import kotlinx.android.synthetic.main.fragment_accomodation.view.accomodation_recycler

class AccommodationFragment : Fragment(), AccommodationAdapter.OnAccommodationSelectedListener {

    private lateinit var accomodationViewModel: AccomodationViewModel

    lateinit var firestore: FirebaseFirestore
    lateinit var query: Query

    lateinit var adapter: AccommodationAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        accomodationViewModel =
            ViewModelProviders.of(this).get(AccomodationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_accomodation, container, false)
        accomodationViewModel.text.observe(viewLifecycleOwner, Observer {

        })

        // Enable Firestore logging
        FirebaseFirestore.setLoggingEnabled(true)

        (activity as HomeActivity).supportActionBar?.title = "Accommodation"

        // Firestore
        firestore = Firebase.firestore

        // Get ${LIMIT} hostels`
        query = firestore.collection("accommodation")
            .orderBy("time", Query.Direction.DESCENDING)
            .limit(LIMIT.toLong())
        adapter = object : AccommodationAdapter(query, this@AccommodationFragment){
            override fun onDataChanged() {
                // Show/hide content if the query returns empty.
                if (itemCount == 0) {
                    Log.d(TAG, "Empty list")
                    root.accomodation_recycler.visibility = View.GONE
                    root.viewEmpty.visibility = View.VISIBLE
                } else {
                    Log.d(TAG, "Not Empty list")
                    root.accomodation_recycler.visibility = View.VISIBLE
                    root.viewEmpty.visibility = View.GONE
                }
            }

            override fun onError(e: FirebaseFirestoreException) {
                Snackbar.make(container!!, "Error: check logs for info", Snackbar.LENGTH_SHORT).show()
            }
        }

        root.accomodation_recycler.layoutManager = LinearLayoutManager(context)
        root.accomodation_recycler.adapter = adapter


        return root
    }

    override fun onStart() {
        super.onStart()

        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    companion object {

        private const val TAG = "AccomodationFragment"

        private const val LIMIT = 4
    }

    override fun OnAccommodationSelected(accommodation: DocumentSnapshot) {
        // Go to the details page for the selected restaurant
        val intent = Intent(activity, AccommodationDetailActivity::class.java )
        intent.putExtra(AccommodationDetailActivity.KEY_CAMPUS_NEWS_ID, accommodation.id)
        val bundle = ActivityOptions.makeCustomAnimation(activity,R.anim.slide_in_from_right, R.anim.slide_out_to_left).toBundle()
        startActivity(intent, bundle)
    }
}