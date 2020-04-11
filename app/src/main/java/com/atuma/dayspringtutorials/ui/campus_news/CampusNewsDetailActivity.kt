package com.atuma.dayspringtutorials.ui.campus_news

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.atuma.dayspringtutorials.R
import com.atuma.dayspringtutorials.model.CampusNews
import com.bumptech.glide.Glide
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_campus_news_detail.*

class CampusNewsDetailActivity : AppCompatActivity(), EventListener<DocumentSnapshot> {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var newsRef: DocumentReference

    private var campusNewsRegistration: ListenerRegistration? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campus_news_detail)

        //get news id from extras
        val campusNewsId = intent.extras?.getString(KEY_CAMPUS_NEWS_ID)
            ?: throw IllegalArgumentException("Must pass extra $KEY_CAMPUS_NEWS_ID")

        // Initialize Firestore
        firestore = Firebase.firestore

        // Get reference to the restaurant
        newsRef = firestore.collection("forum").document(campusNewsId)

        actionBar?.hide()
        collapse_toolbar_layout.setExpandedTitleColor(resources.getColor(android.R.color.transparent))

        var isFav = true
        is_fav.setOnClickListener {
            if (isFav == false){
                is_fav.setImageResource(R.drawable.ic_favorite_red_24dp)
                isFav = true
            }else{
                is_fav.setImageResource(R.drawable.ic_favorite_white_24dp)
                isFav = false
            }
        }

    }

    override fun onStart() {
        super.onStart()
        campusNewsRegistration = newsRef.addSnapshotListener(this)
    }

    override fun onStop() {
        super.onStop()

        campusNewsRegistration?.remove()
        campusNewsRegistration = null
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
    }


    override fun onEvent(snapshot: DocumentSnapshot?, e: FirebaseFirestoreException?) {

        if (e != null){
            Log.w(TAG, "campusNews; onEvent  ", e)
            return
        }

        snapshot?.let {
            val news = snapshot.toObject<CampusNews>()
            if (news != null){
                onNewsLoaded(news)
            }
        }
    }

    private fun onNewsLoaded(news: CampusNews) {
        Glide.with(campus_news_image.context)
            .load(news.image)
            .asBitmap()
//            .placeholder(R.drawable.)
            .into(campus_news_image)

        collapse_toolbar_layout.title = news.title
        campus_news_author.text = news.author
        var newsText = "<![CDATA[" +news.news + "]]>"

        var formatedNews = Html.fromHtml(newsText)

        val formText = news.news?.replace("_n","\n")
        Log.d(TAG, "news.news: ${news.news}")
        Log.d(TAG, "news.news.toString: ${news.news.toString()}")
        Log.d(TAG, "news.news.toString().format(): ${news.news.toString().format()}")
        campus_news_description.text =   formText
        campus_news_time.text = news.time.toString()
        campus_news_title.text = news.title


    }

    private fun onBackArrowClicked() {
        onBackPressed()
    }

    private fun hideKeyboard() {
        val view = currentFocus
        if (view != null) {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    companion object {

        private const val TAG = "CampusNewsDetail"

        const val KEY_CAMPUS_NEWS_ID = "key_campus_news_id"
    }

}
