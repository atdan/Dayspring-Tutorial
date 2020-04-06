package com.atuma.dayspringtutorials.ui.campus_news

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.atuma.dayspringtutorials.HomeActivity
import com.atuma.dayspringtutorials.R
import com.atuma.dayspringtutorials.adapter.CampusNewsAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_campus_news.*
import kotlinx.android.synthetic.main.fragment_campus_news.view.*

class CampusNewsFragment : Fragment(), CampusNewsAdapter.OnCampusNewsSelectedListener {

    private lateinit var campusNewsViewModel: CampusNewsViewModel

    lateinit var firestore: FirebaseFirestore
    lateinit var query: Query


    lateinit var adapter: CampusNewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        campusNewsViewModel =
            ViewModelProviders.of(this).get(CampusNewsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_campus_news, container, false)
        campusNewsViewModel.text.observe(viewLifecycleOwner, Observer {

        })

        // Enable Firestore logging
        FirebaseFirestore.setLoggingEnabled(true)

        (activity as HomeActivity).supportActionBar?.title = "Campus News"
        activity?.title = "Campus News"
//        root.activity?.title = "Campus News"

        // Firestore
        firestore = Firebase.firestore

        // Get ${LIMIT} restaurants
        query = firestore.collection("forum")
            .orderBy("time", Query.Direction.DESCENDING)
            .limit(LIMIT.toLong())

        // RecyclerView
        adapter = object : CampusNewsAdapter(query, this@CampusNewsFragment) {
            override fun onDataChanged() {
                // Show/hide content if the query returns empty.
                if (itemCount == 0) {
                    root.campus_news_recycler.visibility = View.GONE
                    root.viewEmpty.visibility = View.VISIBLE
                } else {
                    root.campus_news_recycler.visibility = View.VISIBLE
                    root.viewEmpty.visibility = View.GONE
                }
            }

            override fun onError(e: FirebaseFirestoreException) {
                // Show a snackbar on errors
                Snackbar.make(
                    container!!,
                    "Error: check logs for info.", Snackbar.LENGTH_LONG).show()
            }
        }

        root.campus_news_recycler.layoutManager = LinearLayoutManager(context)
        root.campus_news_recycler.adapter = adapter


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

    override fun onCampusNewsSelected(campusNews: DocumentSnapshot) {

        // Go to the details page for the selected restaurant
        val intent = Intent(activity, CampusNewsDetailActivity::class.java )
        intent.putExtra(CampusNewsDetailActivity.KEY_CAMPUS_NEWS_ID, campusNews.id)
        val bundle = ActivityOptions.makeCustomAnimation(activity,R.anim.slide_in_from_right, R.anim.slide_out_to_left).toBundle()
        startActivity(intent, bundle)

    }

    companion object {

        private const val TAG = "CampusNewsFragment"

        private const val LIMIT = 10
    }
}