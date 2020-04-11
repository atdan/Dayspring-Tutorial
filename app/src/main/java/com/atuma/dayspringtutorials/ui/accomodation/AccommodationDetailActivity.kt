package com.atuma.dayspringtutorials.ui.accomodation

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.atuma.dayspringtutorials.R
import com.atuma.dayspringtutorials.model.Accommodation
import com.atuma.dayspringtutorials.util.Utils
import com.bumptech.glide.Glide
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_accommodation_detail.*
import org.imaginativeworld.whynotimagecarousel.CarouselItem
import org.imaginativeworld.whynotimagecarousel.OnItemClickListener

class AccommodationDetailActivity : AppCompatActivity(), EventListener<DocumentSnapshot> {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var accommodationRef: DocumentReference

    private var accommodationRegistration: ListenerRegistration? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accommodation_detail)

        val accommodationId = intent.extras?.getString(KEY_ACCOMMODATION_ID)
            ?:throw IllegalArgumentException("Must pass extra $KEY_ACCOMMODATION_ID")

        title = "Accommodation"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        firestore = Firebase.firestore
        accommodationRef = firestore.collection("accommodation").document(accommodationId)



    }

    override fun onEvent(snapshot: DocumentSnapshot?, e: FirebaseFirestoreException?) {

        if (e != null){
            Log.w(TAG, "onEvent: ", e)
            return
        }

        snapshot?.let {
            val accommodation = snapshot.toObject<Accommodation>()
            if (accommodation != null){
                onAccomoadtionLoaded(accommodation)
            }
        }
    }

    private fun onAccomoadtionLoaded(accomodation: Accommodation) {

        feature_1.visibility = View.GONE
        feature_2.visibility = View.GONE
        feature_3.visibility = View.GONE
        feature_4.visibility = View.GONE

        Glide.with(image.context)
            .load(accomodation.image_one)
            .placeholder(R.drawable.ic_insert_photo_black_24dp)
            .into(image)

        val carouselList = mutableListOf<CarouselItem>()
        carouselList.add(accomodation.image_one?.let { CarouselItem(imageUrl = it) }!!)
        carouselList.add(accomodation.image_two?.let { CarouselItem(imageUrl = it) }!!)
        carouselList.add(accomodation.image_three?.let { CarouselItem(imageUrl = it) }!!)
        carouselList.add(accomodation.image_four?.let { CarouselItem(imageUrl = it) }!!)

        carousel.onItemClickListener = object : OnItemClickListener{
            override fun onClick(position: Int, carouselItem: CarouselItem) {
                Glide.with(image.context)
                    .load(carouselItem.imageUrl)
                    .placeholder(R.drawable.ic_insert_photo_black_24dp)
                    .into(image)
            }

            override fun onLongClick(position: Int, dataObject: CarouselItem) {
                TODO("Not yet implemented")
            }

        }

        carousel.addData(carouselList)
        carousel.setIndicator(custom_indicator)

        hostel_name.text = accomodation.name
        price.text = accomodation.price
        agent.text = accomodation.agent
        agent_number.text = accomodation.number
        location_text.text = accomodation.location
        feature_1.text = accomodation.feature_one
        feature_2.text = accomodation.feature_two
        feature_3.text = accomodation.feature_three
        feature_4.text = accomodation.feature_four

        if (TextUtils.isEmpty(feature_1.text) || feature_1.text == null){
            feature_1.visibility = View.GONE
        }else{
            feature_1.visibility = View.VISIBLE
        }

        if (TextUtils.isEmpty(feature_2.text) || feature_2.text == null){
            feature_2.visibility = View.GONE
        }else{
            feature_2.visibility = View.VISIBLE
        }

        if (TextUtils.isEmpty(feature_3.text) || feature_3.text == null){
            feature_3.visibility = View.GONE
        }else{
            feature_3.visibility = View.VISIBLE
        }

        if (TextUtils.isEmpty(feature_4.text) || feature_4.text == null){
            feature_4.visibility = View.GONE
        }else{
            feature_4.visibility = View.VISIBLE
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        when (item.itemId) {
            android.R.id.home ->{
                Utils.animateEnterLeft(this)
                finish()
            }
        }
        return true
    }

    override fun onStart() {
        super.onStart()
        accommodationRegistration = accommodationRef.addSnapshotListener(this)
    }

    override fun finish() {
        super.finish()
        Utils.animateEnterRight(this)
    }

    override fun onStop() {
        super.onStop()
        accommodationRegistration?.remove()
        accommodationRegistration = null
    }
    companion object {

        private const val TAG = "AccommodationDetail"

        const val KEY_ACCOMMODATION_ID = "key_accommodation_id"
    }


}
