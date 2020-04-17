package com.atuma.dayspringtutorials.ui.partOne

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.atuma.dayspringtutorials.R
import com.atuma.dayspringtutorials.ui.courses.partOne.CoursesActivity
import kotlinx.android.synthetic.main.fragment_part_one_courses.view.*

class PartOneCoursesFragment : Fragment(){

    private lateinit var partOneCoursesViewModel: PartOneCoursesViewModel



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        partOneCoursesViewModel = ViewModelProviders.of(this).get(PartOneCoursesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_part_one_courses, container, false)

        root.chm101.setOnClickListener{
            goToCHMFragment()
        }

        root.chm102.setOnClickListener {
            goToCHM102Fragment()
        }
        root.mth101.setOnClickListener{
            goToMTH101Fragment()
        }
        root.mth102.setOnClickListener {
            goToMTH102Fragment()
        }
        root.mth104.setOnClickListener{
            goToMTH104Fragment()
        }
        root.mth105.setOnClickListener{
            goToMTH105Fragment()
        }
        root.mth106.setOnClickListener {
            goToMTH106Fragment()
        }
        root.phy101.setOnClickListener {
            goToPHY101Fragment()
        }
        root.phy102.setOnClickListener {
            goToPHY102Fragment()
        }
        root.phy105.setOnClickListener {
            goToPHY105Fragment()
        }
        root.phy106.setOnClickListener {
            goToPHY106Fragment()
        }
        root.ssc105.setOnClickListener {
            goToSSC105Fragment()
        }

        return root
    }

    private fun goToSSC105Fragment() {
        val i = Intent(activity, CoursesActivity::class.java)
        i.putExtra(COURSE_CODE, SSC105 )
        startActivity(i)
        com.atuma.dayspringtutorials.util.Utils.animateEnterRight(this.requireActivity())
    }

    private fun goToPHY106Fragment() {
        val i = Intent(activity, CoursesActivity::class.java)
        i.putExtra(COURSE_CODE, PHY106 )
        startActivity(i)
        com.atuma.dayspringtutorials.util.Utils.animateEnterRight(this.requireActivity())
    }

    private fun goToPHY105Fragment() {
        val i = Intent(activity, CoursesActivity::class.java)
        i.putExtra(COURSE_CODE, PHY105 )
        startActivity(i)
        com.atuma.dayspringtutorials.util.Utils.animateEnterRight(this.requireActivity())
    }

    private fun goToPHY102Fragment() {
        val i = Intent(activity, CoursesActivity::class.java)
        i.putExtra(COURSE_CODE, PHY102 )
        startActivity(i)
        com.atuma.dayspringtutorials.util.Utils.animateEnterRight(this.requireActivity())
    }

    private fun goToPHY101Fragment() {
        val i = Intent(activity, CoursesActivity::class.java)
        i.putExtra(COURSE_CODE, PHY101 )
        startActivity(i)
        com.atuma.dayspringtutorials.util.Utils.animateEnterRight(this.requireActivity())
    }

    private fun goToMTH106Fragment() {
        val i = Intent(activity, CoursesActivity::class.java)
        i.putExtra(COURSE_CODE, MTH106 )
        startActivity(i)
        com.atuma.dayspringtutorials.util.Utils.animateEnterRight(this.requireActivity())
    }

    private fun goToMTH105Fragment() {
        val i = Intent(activity, CoursesActivity::class.java)
        i.putExtra(COURSE_CODE, MTH105 )
        startActivity(i)
        com.atuma.dayspringtutorials.util.Utils.animateEnterRight(this.requireActivity())
    }

    private fun goToMTH104Fragment() {
        val i = Intent(activity, CoursesActivity::class.java)
        i.putExtra(COURSE_CODE, MTH104 )
        startActivity(i)
        com.atuma.dayspringtutorials.util.Utils.animateEnterRight(this.requireActivity())
    }

    private fun goToMTH102Fragment() {
        val i = Intent(activity, CoursesActivity::class.java)
        i.putExtra(COURSE_CODE, MTH102 )
        startActivity(i)
        com.atuma.dayspringtutorials.util.Utils.animateEnterRight(this.requireActivity())
    }

    private fun goToMTH101Fragment() {
        val i = Intent(activity, CoursesActivity::class.java)
        i.putExtra(COURSE_CODE, MTH101 )
        startActivity(i)
        com.atuma.dayspringtutorials.util.Utils.animateEnterRight(this.requireActivity())
    }


    private fun goToCHM102Fragment() {
        val i = Intent(activity, CoursesActivity::class.java)
        i.putExtra(COURSE_CODE, CHM102 )
        startActivity(i)
        com.atuma.dayspringtutorials.util.Utils.animateEnterRight(this.requireActivity())
    }

    private fun goToCHMFragment() {
        val i = Intent(activity, CoursesActivity::class.java)
        i.putExtra(COURSE_CODE, CHM101 )
        startActivity(i)
        com.atuma.dayspringtutorials.util.Utils.animateEnterRight(this.requireActivity())
    }

    companion object{
        const val TAG = "PartOneCoursesFragment"
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

