package com.atuma.dayspringtutorials.ui.courses.partOne

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.atuma.dayspringtutorials.R
import com.atuma.dayspringtutorials.ui.courses.partOne.CoursesActivity.Companion.COURSE_SELECTED
import com.atuma.dayspringtutorials.ui.courses.partOne.adapters.PastQuestionAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_past_questions.view.*


class PastQuestionsFragment : Fragment(), PastQuestionAdapter.OnPastQuestionSelectedListener {

    lateinit var firestore: FirebaseFirestore
    lateinit var query: Query

    lateinit var adapter: PastQuestionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_past_questions, container, false)

        FirebaseFirestore.setLoggingEnabled(true)
        firestore = Firebase.firestore


        Log.d(TAG, "Just before calling getSource")
        getSource(root, container)

        return root
    }

    private fun getDataFromFirestore(course: String, root: View?, container: ViewGroup?) {
        query = firestore.collection("courses/partone/$course/pq/topics")
            .orderBy("year", Query.Direction.ASCENDING)


        adapter = object : PastQuestionAdapter(query, this@PastQuestionsFragment){
            override fun onDataChanged() {
                if (itemCount == 0){
                    Log.d(TAG, "Get Data from firestore onDataChanged itemCount =0")
                    root?.past_question_recycler?.visibility = View.GONE
                    root?.viewEmpty?.visibility = View.VISIBLE
                }else{
                    Log.d(TAG, "Get Data from firestore onDataChanged itemCount = $itemCount")

                    root?.past_question_recycler?.visibility = View.VISIBLE
                    root?.viewEmpty?.visibility = View.GONE
                }
            }

            override fun onError(e: FirebaseFirestoreException) {
                Snackbar.make(container!!, "Error: check logs for info", Snackbar.LENGTH_SHORT).show()
            }
        }

        root?.past_question_recycler?.layoutManager = LinearLayoutManager(context)
        root?.past_question_recycler?.adapter = adapter

    }


    private fun getSource(root: View?, container: ViewGroup?) {
        Log.d(TAG, "Gotten to getSource before when")
        Log.d(TAG, "Inside getSource: Past Question: $COURSE_SELECTED")

        when(COURSE_SELECTED){
            CoursesFragment.CHM101 -> {
                Log.d(TAG, "Gotten to getSource PQ: ${CoursesFragment.CHM101}")
                (activity as CoursesActivity).supportActionBar?.title = "CHM 101 Past Questions"

                getDataFromFirestore(CoursesFragment.CHM101, root, container)
            }
            CoursesFragment.CHM102 -> {
                (activity as CoursesActivity).supportActionBar?.title = "CHM 102 Past Questions"
                getDataFromFirestore(CoursesFragment.CHM102, root, container)
            }
            CoursesFragment.MTH101 -> {
                (activity as CoursesActivity).supportActionBar?.title = "MTH 101 Past Questions"

                getDataFromFirestore(CoursesFragment.MTH101, root, container)

            }
            CoursesFragment.MTH102 -> {
                (activity as CoursesActivity).supportActionBar?.title = "MTH 102 Past Questions"
                getDataFromFirestore(CoursesFragment.MTH102, root, container)
            }
            CoursesFragment.MTH104 -> {
                (activity as CoursesActivity).supportActionBar?.title = "MTH 104 Past Questions"

                getDataFromFirestore(CoursesFragment.MTH104, root, container)

            }
            CoursesFragment.MTH105 -> {
                (activity as CoursesActivity).supportActionBar?.title = "MTH 105 Past Questions"

                getDataFromFirestore(CoursesFragment.MTH105, root, container)

            }
            CoursesFragment.MTH106 -> {
                (activity as CoursesActivity).supportActionBar?.title = "MTH 106 Past Questions"

                getDataFromFirestore(CoursesFragment.MTH106, root, container)

            }
            CoursesFragment.PHY101 -> {
                (activity as CoursesActivity).supportActionBar?.title = "PHY 101 Past Questions"

                getDataFromFirestore(CoursesFragment.PHY101, root, container)

            }
            CoursesFragment.PHY102 -> {
                (activity as CoursesActivity).supportActionBar?.title = "PHY 102 Past Questions"

                getDataFromFirestore(CoursesFragment.PHY102, root, container)

            }
            CoursesFragment.PHY105 -> {
                (activity as CoursesActivity).supportActionBar?.title = "PHY 105 Past Questions"

                getDataFromFirestore(CoursesFragment.PHY105, root, container)

            }
            CoursesFragment.PHY106 -> {
                (activity as CoursesActivity).supportActionBar?.title = "PHY 106 Past Questions"

                getDataFromFirestore(CoursesFragment.PHY106, root, container)

            }
            CoursesFragment.SSC105 -> {
                (activity as CoursesActivity).supportActionBar?.title = "SSC 105 Past Questions"

                getDataFromFirestore(CoursesFragment.SSC105, root, container)

            }
        }

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

        private const val TAG = "PastQuestionFragment"

    }

    override fun OnPastQuestionSelected(topic: DocumentSnapshot) {

        Log.d(TAG, "PQ Selected")
    }


}
