package com.atuma.dayspringtutorials.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atuma.dayspringtutorials.R
import com.atuma.dayspringtutorials.model.Accommodation
import com.bumptech.glide.Glide
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import kotlinx.android.synthetic.main.item_accomodation.view.*

open class AccommodationAdapter (query: Query, private val listener: OnAccommodationSelectedListener):
    FirestoreAdapter<AccommodationAdapter.ViewHolder>(query)
{

    interface OnAccommodationSelectedListener{

        fun OnAccommodationSelected(accommodation: DocumentSnapshot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_accomodation, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getSnapshot(position), listener)
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            snapshot: DocumentSnapshot,
            listener: OnAccommodationSelectedListener?
        ) {

            val accommodation = snapshot.toObject<Accommodation>() ?: return

            val resources = itemView.resources

            // Load image
            Glide.with(itemView.accomodation_image.context)
                .load(accommodation.image_one)
                .placeholder(R.drawable.ic_insert_photo_black_24dp)
                .into(itemView.accomodation_image)


            itemView.price.text = accommodation.price
            itemView.location_text.text = accommodation.location
            itemView.hostel_name.text = accommodation.name
//            itemView.campus_news_author.text = campusNews.author
//            itemView.campus_news_description.text = campusNews.news
//            itemView.campus_news_title.text = campusNews.title
//            itemView.campus_news_time.text = campusNews.time.toString()


            // Click listener
            itemView.setOnClickListener {
                listener?.OnAccommodationSelected(snapshot)
            }
        }
    }


}