package com.atuma.dayspringtutorials.ui.courses.partOne.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atuma.dayspringtutorials.R
import com.atuma.dayspringtutorials.adapter.FirestoreAdapter
import com.atuma.dayspringtutorials.model.PastQuestion
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import kotlinx.android.synthetic.main.item_past_questions.view.*

open class PastQuestionAdapter (query: Query, private val listener: OnPastQuestionSelectedListener)
    :FirestoreAdapter<PastQuestionAdapter.ViewHolder>(query){

    interface OnPastQuestionSelectedListener{
        fun OnPastQuestionSelected(topic: DocumentSnapshot)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(R.layout.item_past_questions, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getSnapshot(position), listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(
            snapshot: DocumentSnapshot,
            listener: OnPastQuestionSelectedListener?
        ){

            val pastQuestion = snapshot.toObject<PastQuestion>()  ?: return

            Log.d("PQ Adapter", "Year: ${pastQuestion.year}")
            Log.d("PQ Adapter", "Type: ${pastQuestion.type}")

            itemView.pq_year.text = pastQuestion.year
            itemView.pq_type.text = pastQuestion.type

            itemView.setOnClickListener {
                listener?.OnPastQuestionSelected(snapshot)
            }
        }
    }



}