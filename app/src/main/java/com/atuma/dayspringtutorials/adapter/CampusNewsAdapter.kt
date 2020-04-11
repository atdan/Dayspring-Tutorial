package com.atuma.dayspringtutorials.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atuma.dayspringtutorials.R
import com.atuma.dayspringtutorials.model.CampusNews
import com.bumptech.glide.Glide
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import kotlinx.android.synthetic.main.item_campus_news.view.*

open class CampusNewsAdapter(query: Query, private val listener: OnCampusNewsSelectedListener) :
    FirestoreAdapter<CampusNewsAdapter.ViewHolder>(query) {

    interface OnCampusNewsSelectedListener {

        fun onCampusNewsSelected(campusNews: DocumentSnapshot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_campus_news, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getSnapshot(position), listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            snapshot: DocumentSnapshot,
            listener: OnCampusNewsSelectedListener?
        ) {

            val campusNews = snapshot.toObject<CampusNews>() ?: return

            val resources = itemView.resources

            // Load image
            Glide.with(itemView.campus_news_image.context)
                .load(campusNews.image)
                .into(itemView.campus_news_image)


            itemView.campus_news_author.text = campusNews.author
            itemView.campus_news_description.text = campusNews.news
            itemView.campus_news_title.text = campusNews.title
            itemView.campus_news_time.text = campusNews.time.toString()


            // Click listener
            itemView.setOnClickListener {
                listener?.onCampusNewsSelected(snapshot)
            }
        }
    }
}
