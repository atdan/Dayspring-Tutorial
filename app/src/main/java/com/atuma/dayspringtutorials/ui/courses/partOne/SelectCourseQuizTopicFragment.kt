package com.atuma.dayspringtutorials.ui.courses.partOne

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager

import com.atuma.dayspringtutorials.R
import com.atuma.dayspringtutorials.ui.courses.partOne.CoursesActivity.Companion.COURSE_SELECTED
import com.atuma.dayspringtutorials.ui.courses.partOne.adapters.QuizTopicAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_select_quiz_topic.view.*
import kotlinx.android.synthetic.main.start_quiz_dialog.view.*


class SelectCourseQuizTopicFragment : Fragment(), QuizTopicAdapter.OnTopicSelectedListener {

    lateinit var firestore: FirebaseFirestore
    lateinit var query: Query

    lateinit var adapter: QuizTopicAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_select_quiz_topic, container, false)

        // Enable Firestore logging
        FirebaseFirestore.setLoggingEnabled(true)

        firestore = Firebase.firestore

        Log.d(TAG, "Just before calling getSource")
        getSource(root, container)

        return root
    }

    private fun getSource(root: View?, container: ViewGroup?) {
        Log.d(TAG, "Gotten to getSource before when")
        Log.d(TAG, "Inside getSource: Course Selected: $COURSE_SELECTED")

        when(COURSE_SELECTED){
            CoursesFragment.CHM101 -> {
                Log.d(TAG, "Gotten to getSource: ${CoursesFragment.CHM101}")
                getDataFromFirestore(CoursesFragment.CHM101, root, container)
            }
            CoursesFragment.CHM102 -> {
                getDataFromFirestore(CoursesFragment.CHM102, root, container)
            }
            CoursesFragment.MTH101 -> {
                getDataFromFirestore(CoursesFragment.MTH101, root, container)

            }
            CoursesFragment.MTH102 -> {
                getDataFromFirestore(CoursesFragment.MTH102, root, container)
            }
            CoursesFragment.MTH104 -> {
                getDataFromFirestore(CoursesFragment.MTH104, root, container)

            }
            CoursesFragment.MTH105 -> {
                getDataFromFirestore(CoursesFragment.MTH105, root, container)

            }
            CoursesFragment.MTH106 -> {
                getDataFromFirestore(CoursesFragment.MTH106, root, container)

            }
            CoursesFragment.PHY101 -> {
                getDataFromFirestore(CoursesFragment.PHY101, root, container)

            }
            CoursesFragment.PHY102 -> {
                getDataFromFirestore(CoursesFragment.PHY102, root, container)

            }
            CoursesFragment.PHY105 -> {
                getDataFromFirestore(CoursesFragment.PHY105, root, container)

            }
            CoursesFragment.PHY106 -> {
                getDataFromFirestore(CoursesFragment.PHY106, root, container)

            }
            CoursesFragment.SSC105 -> {
                getDataFromFirestore(CoursesFragment.SSC105, root, container)

            }
        }

    }


    private fun getDataFromFirestore(course: String, root: View?, container: ViewGroup?) {

        query = firestore.collection("courses/partone/$course/quiz/topics")
            .orderBy("week", Query.Direction.ASCENDING)


        adapter = object : QuizTopicAdapter(query, this@SelectCourseQuizTopicFragment){
            override fun onDataChanged() {
                // Show/hide content if the query returns empty.
                if (itemCount == 0) {
                    Log.d(TAG, "Empty list")
                    root?.chm101_quiz_topics_recycler?.visibility = View.GONE
                    root?.viewEmpty?.visibility = View.VISIBLE
                } else {
                    Log.d(TAG, "Not Empty list")
                    root?.chm101_quiz_topics_recycler?.visibility = View.VISIBLE
                    root?.viewEmpty?.visibility = View.GONE
                }
            }

            override fun onError(e: FirebaseFirestoreException) {
                Snackbar.make(container!!, "Error: check logs for info", Snackbar.LENGTH_SHORT).show()

            }
        }

        root?.chm101_quiz_topics_recycler?.layoutManager = LinearLayoutManager(context)
        root?.chm101_quiz_topics_recycler?.adapter = adapter
    }


    override fun onStart() {
        super.onStart()

        try {
            adapter.startListening()

        }catch (e: UninitializedPropertyAccessException){

        }


    }

    override fun onStop() {
        super.onStop()
        try {
            adapter.stopListening()

        }catch (e: UninitializedPropertyAccessException){

        }
    }

    companion object {

        private const val TAG = "QuizTopicFragment"

    }

    override fun OnTopicSelected(topic: DocumentSnapshot) {


        val dialogView = LayoutInflater.from(activity).inflate(R.layout.start_quiz_dialog, null)

        val dialogBuilder = AlertDialog.Builder(requireActivity())
            .setView(dialogView)
        val alert = dialogBuilder.show()
//        alert.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogBuilder
            .setView(dialogView)
        dialogView.dialog_close.setOnClickListener {
            alert.dismiss()
        }
        dialogView.dialog_start_button.setOnClickListener {
            alert.dismiss()
        }


    }
}
