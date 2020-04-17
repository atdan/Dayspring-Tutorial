package com.atuma.dayspringtutorials.ui.courses.partOne

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.atuma.dayspringtutorials.R
import com.atuma.dayspringtutorials.ui.courses.partOne.CoursesActivity.Companion.COURSE_SELECTED
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_chm101.*
import kotlinx.android.synthetic.main.fragment_chm101.view.*


class CoursesFragment : Fragment() {

    private lateinit var coursesViewModel: CoursesViewModel
    lateinit var firestore: FirebaseFirestore
    lateinit var query: Query


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        coursesViewModel = ViewModelProviders.of(this).get(CoursesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_chm101, container, false)



        root.takeQuiz.setOnClickListener {
             findNavController().navigate(R.id.action_chm101Fragment_to_chm101SelectQuizTopic)
        }

        root.pastQuestion.setOnClickListener {
            findNavController().navigate(R.id.action_chm101Fragment_to_pastQuestionFragment)
        }


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when(COURSE_SELECTED){
            CHM101 -> {
                requireActivity().title = "CHM 101"
                chm101_image.setImageResource(R.drawable.ic_test_tube)
            }
            CHM102 -> {
                requireActivity().title = "CHM 102"
                chm101_image.setImageResource(R.drawable.ic_molecular)

            }
            MTH101 -> {
                requireActivity().title = "MTH 101"
                chm101_image.setImageResource(R.drawable.ic_union)

            }
            MTH102 -> {
                requireActivity().title = "MTH 102"
                chm101_image.setImageResource(R.drawable.ic_mathematics)

            }
            MTH104 -> {
                requireActivity().title = "MTH 104"
                chm101_image.setImageResource(R.drawable.ic_calculating)

            }
            MTH105 -> {
                requireActivity().title = "MTH 105"
                chm101_image.setImageResource(R.drawable.ic_function)


            }
            MTH106 -> {
                requireActivity().title = "MTH 106"
                chm101_image.setImageResource(R.drawable.ic_probability)

            }
            PHY101 -> {
                requireActivity().title = "PHY 101"
                chm101_image.setImageResource(R.drawable.ic_seesaw)
            }
            PHY102 -> {
                requireActivity().title = "PHY 102"
                chm101_image.setImageResource(R.drawable.ic_atom)


            }
            PHY105 -> {
                requireActivity().title = "PHY 105"
                chm101_image.setImageResource(R.drawable.ic_gravity)
            }
            PHY106 -> {
                requireActivity().title = "PHY 106"
                chm101_image.setImageResource(R.drawable.ic_satellite)

            }
            SSC105 -> {
                requireActivity().title = "SSC 105"
                chm101_image.setImageResource(R.drawable.ic_abacus)

            }
        }

    }


    private fun moveToQuizFragment() {

        
    }


    companion object {

        const val TAG = "CHM101Fragment"
        const val COURSE_CODE = "COURSE_CODE"
        const val CHM101 = "chm101"
        const val CHM102 = "chm102"
        const val MTH101 = "mth101"
        const val MTH102 = "mth102"
        const val MTH104 = "mth104"
        const val MTH105 = "mth105"
        const val MTH106 = "mth106"
        const val PHY101 = "phy101"
        const val PHY102 = "phy102"
        const val PHY105 = "phy105"
        const val PHY106 = "phy106"
        const val SSC105 = "ssc105"
    }
}
