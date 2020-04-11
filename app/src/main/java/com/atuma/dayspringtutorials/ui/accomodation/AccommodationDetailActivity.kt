package com.atuma.dayspringtutorials.ui.accomodation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.atuma.dayspringtutorials.R
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class AccommodationDetailActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var newsRef: DocumentReference

    private var accommodationRegistration: ListenerRegistration? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accommodation_detail)
    }

    companion object {

        private const val TAG = "AccommodationDetail"

        const val KEY_CAMPUS_NEWS_ID = "key_campus_news_id"
    }
}
