package com.atuma.dayspringtutorials.ui.courses.partOne.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atuma.dayspringtutorials.R
import com.atuma.dayspringtutorials.adapter.FirestoreAdapter
import com.atuma.dayspringtutorials.model.QuizTopic
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import kotlinx.android.synthetic.main.item_quiz_topic.view.*

open class QuizTopicAdapter(query: Query, private val listener: OnTopicSelectedListener)
    :FirestoreAdapter<QuizTopicAdapter.ViewHolder>(query){

    interface OnTopicSelectedListener{
        fun OnTopicSelected(topic: DocumentSnapshot)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(R.layout.item_quiz_topic, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(getSnapshot(position), listener)
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(
            snapshot: DocumentSnapshot,
            listener: OnTopicSelectedListener?
        ){

            val quizTopic = snapshot.toObject<QuizTopic>()  ?: return

            itemView.quiz_week.text = quizTopic.week
            itemView.quiz_topic.text = quizTopic.topic
            itemView.quiz_subtopics.text = quizTopic.subtopic

            itemView.setOnClickListener {
                listener?.OnTopicSelected(snapshot)
            }
        }
    }




}