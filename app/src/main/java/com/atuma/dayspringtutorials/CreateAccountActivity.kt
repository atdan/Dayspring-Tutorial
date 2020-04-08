package com.atuma.dayspringtutorials

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.atuma.dayspringtutorials.model.User
import com.atuma.dayspringtutorials.ui.login.LoginActivity
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner
import com.google.firebase.firestore.ktx.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccountActivity : AppCompatActivity() {

    lateinit var userHashMap: HashMap<String, String?>
    val errorMessage = "Please Fill this field"

    private var facultySpinner: SmartMaterialSpinner<String>? = null
    private var departmentSpinner: SmartMaterialSpinner<String>? = null

    private var facultyList: MutableList<String>? = null
    private var departmentList: MutableList<String>? = null
    var facultySpinnerItem: String? = null
    var departmentSpinnerItem: String? = null

    var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        supportActionBar
        title = "Form"


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)



        //init firestore
        db= Firebase.firestore

        initFacultySpinner()
        initDepartmentSpinner(FACULTY_DEFAULT)
        editTextListeners()
        continue_sign_up.setOnClickListener {
            disableErrorEditText()

            if (TextUtils.isEmpty(surname.editText?.text.toString())){
                surname.error = errorMessage
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(first_name.editText?.text.toString())){
                first_name.error = errorMessage
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(phone_number.editText?.text.toString()) || phone_number.editText?.text.toString().length != 11){
                phone_number.error = "Please enter your phone number"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(matric_number.editText?.text.toString())){
                matric_number.error = errorMessage
                return@setOnClickListener
            }
            if (departmentSpinner?.selectedItem == null || departmentSpinnerItem == null){
                departmentSpinner?.errorText = "Please select a Department"
                departmentSpinner?.errorTextColor = Color.RED
                return@setOnClickListener
            }
            if (facultySpinner?.selectedItem == null || facultySpinnerItem == null){
                facultySpinner?.errorText = "Please select a Faculty"
                facultySpinner?.errorTextColor = Color.RED
                return@setOnClickListener
            }

            continue_sign_up.startAnimation()
            saveUserDetails()



        }
    }

    override fun onNavigateUpFromChild(child: Activity?): Boolean {
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)

        return super.onNavigateUpFromChild(child)
    }
    override fun onBackPressed() {
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)

        super.onBackPressed()


    }

    override fun onSupportNavigateUp(): Boolean {
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)

        return super.onSupportNavigateUp()

    }

    private fun editTextListeners(){
        surname.setOnClickListener {
            disableErrorEditText()
        }
        surnameEditText.setOnClickListener {
            disableErrorEditText()
        }
        first_name.setOnClickListener {
            disableErrorEditText()
        }
        first_nameEditText.setOnClickListener {
            disableErrorEditText()
        }
        phone_number.setOnClickListener {
            disableErrorEditText()
        }
        phone_numberEditText.setOnClickListener {
            disableErrorEditText()
        }
        matric_number.setOnClickListener {
            disableErrorEditText()
        }
        matric_numberEditText.setOnClickListener {
            disableErrorEditText()
        }
        email.setOnClickListener {
            disableErrorEditText()
        }
        emailEditText.setOnClickListener {
            disableErrorEditText()
        }
    }

    private fun disableErrorEditText(){
        surname.isErrorEnabled = false
        first_name.isErrorEnabled = false
        phone_number.isErrorEnabled = false
        matric_number.isErrorEnabled = false
    }

    private fun initDepartmentSpinner(facultyName: String){
        departmentSpinner = findViewById(R.id.department_spinner)
        departmentList = ArrayList()
        when(facultyName){
            FACULTY_DEFAULT -> {
                departmentList!!.addAll(resources.getStringArray(R.array.Default_dept))
                departmentSpinner!!.item = departmentList

                departmentSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        departmentSpinnerItem = departmentSpinner!!.item[position]
                    }

                }
            }
            FACULTY_ADMINISTRATION -> {
                departmentList!!.addAll(resources.getStringArray(R.array.Administration_dept))
                departmentSpinner!!.item = departmentList

                departmentSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        departmentSpinnerItem = departmentSpinner!!.item[position]
                    }

                }
            }
            FACULTY_AGRICULTURE -> {
                departmentList!!.addAll(resources.getStringArray(R.array.Agriculture_dept))
                departmentSpinner!!.item = departmentList

                departmentSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        departmentSpinnerItem = departmentSpinner!!.item[position]
                    }

                }
            }
            FACULTY_ARTS -> {
                departmentList!!.addAll(resources.getStringArray(R.array.Arts_dept))
                departmentSpinner!!.item = departmentList

                departmentSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        departmentSpinnerItem = departmentSpinner!!.item[position]
                    }

                }
            }
            FACULTY_CLINICAL_SCIENCES -> {
                departmentList!!.addAll(resources.getStringArray(R.array.ClinicalSciences_dept))
                departmentSpinner!!.item = departmentList

                departmentSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        departmentSpinnerItem = departmentSpinner!!.item[position]
                    }

                }
            }
            FACULTY_DENTISTRY -> {
                departmentList!!.addAll(resources.getStringArray(R.array.Dentistrry_dept))
                departmentSpinner!!.item = departmentList

                departmentSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        departmentSpinnerItem = departmentSpinner!!.item[position]
                    }

                }
            }
            FACULTY_EDM -> {
                departmentList!!.addAll(resources.getStringArray(R.array.EDM_dept))
                departmentSpinner!!.item = departmentList

                departmentSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        departmentSpinnerItem = departmentSpinner!!.item[position]
                    }

                }
            }
            FACULTY_EDUCATION -> {
                departmentList!!.addAll(resources.getStringArray(R.array.Education_dept))
                departmentSpinner!!.item = departmentList

                departmentSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        departmentSpinnerItem = departmentSpinner!!.item[position]
                    }

                }
            }
            FACULTY_LAW -> {
                departmentList!!.addAll(resources.getStringArray(R.array.Law_dept))
                departmentSpinner!!.item = departmentList

                departmentSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        departmentSpinnerItem = departmentSpinner!!.item[position]
                    }

                }
            }
            FACULTY_MBBS -> {
                departmentList!!.addAll(resources.getStringArray(R.array.MBBS_dept))
                departmentSpinner!!.item = departmentList

                departmentSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        departmentSpinnerItem = departmentSpinner!!.item[position]
                    }

                }
            }
            FACULTY_PHARMACY -> {
                departmentList!!.addAll(resources.getStringArray(R.array.Pharmacy_dept))
                departmentSpinner!!.item = departmentList

                departmentSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        departmentSpinnerItem = departmentSpinner!!.item[position]
                    }

                }
            }
            FACULTY_SCIENCES -> {
                departmentList!!.addAll(resources.getStringArray(R.array.Sciences_dept))
                departmentSpinner!!.item = departmentList

                departmentSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        departmentSpinnerItem = departmentSpinner!!.item[position]
                    }

                }
            }
            FACULTY_SOCIAL_SCIENCES -> {
                departmentList!!.addAll(resources.getStringArray(R.array.SocialSciences_dept))
                departmentSpinner!!.item = departmentList

                departmentSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        departmentSpinnerItem = departmentSpinner!!.item[position]
                    }

                }
            }
            FACULTY_TECHNOLOGY -> {
                departmentList!!.addAll(resources.getStringArray(R.array.Technology_dept))
                departmentSpinner!!.item = departmentList

                departmentSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        departmentSpinnerItem = departmentSpinner!!.item[position]
                    }

                }
            }
        }
    }
    private fun initFacultySpinner(){

        facultySpinner = findViewById(R.id.faculty_spinner)
        facultyList = ArrayList()

        facultyList!!.add(FACULTY_ADMINISTRATION)
        facultyList!!.add(FACULTY_AGRICULTURE)
        facultyList!!.add(FACULTY_ARTS)
        facultyList!!.add(FACULTY_EDUCATION)
        facultyList!!.add(FACULTY_EDM)
        facultyList!!.add(FACULTY_MBBS)
        facultyList!!.add(FACULTY_CLINICAL_SCIENCES)
        facultyList!!.add(FACULTY_DENTISTRY)
        facultyList!!.add(FACULTY_LAW)
        facultyList!!.add(FACULTY_PHARMACY)
        facultyList!!.add(FACULTY_SCIENCES)
        facultyList!!.add(FACULTY_SOCIAL_SCIENCES)
        facultyList!!.add(FACULTY_TECHNOLOGY)

        facultySpinner!!.item = facultyList

        facultySpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                facultySpinnerItem = facultyList!![position]
                initDepartmentSpinner(facultyList!![position])
            }

        }
    }


    private fun saveUserDetails(){

        val user = hashMapOf(
            FIELD_SURNAME to surname.editText?.text.toString(),
            FIELD_DEPARTMENT to departmentSpinnerItem,
            FIELD_EMAIL to email.editText?.text.toString(),
            FIELD_FACULTY to facultySpinnerItem,
            FIELD_FIRST_NAME to first_name.editText?.text.toString(),
            FIELD_IMAGE to null,
            FIELD_MATRIC to matric_number.editText?.text.toString(),
            FIELD_PHONE to phone_number.editText?.text.toString()
        )
        db.collection(COLLECTION_USERS)
            .add(user)
            .addOnSuccessListener { documentReference ->

                val bitmap = BitmapFactory.decodeResource(resources,R.drawable.ic_check_white_24dp)
//                continue_sign_up.doneLoadingAnimation(R.color.colorPrimaryDark,bitmap)
                continue_sign_up.dispose()
                Log.d(TAG,"Document added with ID ${documentReference.id}")
                val i = Intent(this, RegisterActivity::class.java)
                startActivity(i)
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
            }.addOnFailureListener{e ->
                val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_close_white_24dp)
                continue_sign_up.doneLoadingAnimation(R.color.red, bitmap)
                continue_sign_up.dispose()
                Log.e(TAG,"Failed to send to firestore: $e")
            }


    }

    companion object {

        // Sharedpref file name
        private const val PREF_NAME = "UserSessionPref"

        // First time logic Check
        const val FIRST_TIME = "firsttime"

        // All Shared Preferences Keys
        private const val IS_LOGIN = "IsLoggedIn"
        // check first time app launch
        const val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"

        const val FIELD_SURNAME = "surname"
        const val FIELD_FIRST_NAME = "firstname"
        const val FIELD_EMAIL = "email"
        const val FIELD_PHONE = "phone"
        const val FIELD_MATRIC = "matric"
        const val FIELD_DEPARTMENT = "department"
        const val FIELD_IMAGE = "image"
        const val FIELD_FACULTY = "faculty"
        const val COLLECTION_USERS = "users"


        const val TAG = "CreateAccountActivity"
        const val FACULTY_ADMINISTRATION = "Administration"
        const val FACULTY_AGRICULTURE= "Agriculture"
        const val FACULTY_ARTS = "Arts"
        const val FACULTY_EDUCATION = "Education"
        const val FACULTY_EDM = "EDM"
        const val FACULTY_MBBS = "MBBS"
        const val FACULTY_CLINICAL_SCIENCES = "Clinical-Sciences"
        const val FACULTY_DENTISTRY = "Dentistry"
        const val FACULTY_LAW = "Law"
        const val FACULTY_PHARMACY = "Pharmacy"
        const val FACULTY_SCIENCES = "Sciences"
        const val FACULTY_SOCIAL_SCIENCES = "Social-Sciences"
        const val FACULTY_TECHNOLOGY = "Technology"
        const val FACULTY_DEFAULT = "Default"




    }

}
