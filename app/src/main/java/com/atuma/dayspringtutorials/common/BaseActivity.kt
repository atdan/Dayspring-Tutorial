package com.atuma.dayspringtutorials.common

import android.Manifest
import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.atuma.dayspringtutorials.util.Connectivity

import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.PermissionListener

open class BaseActivity : AppCompatActivity() {

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = ProgressDialog(this)
    }

    fun isConnected(): Boolean {
        return Connectivity.isConnected(this)
    }

    // User hasn't requested storage permission; request them to allow
    fun requestStoragePermission() {
        Dexter.withActivity(this)
            .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionRationaleShouldBeShown(
                    permission: com.karumi.dexter.listener.PermissionRequest?,
                    token: PermissionToken?
                ) {

                    //To change body of created functions use File | Settings | File Templates.
                    token!!.continuePermissionRequest()
                }

                override fun onPermissionGranted(response: PermissionGrantedResponse) {

                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {

                }


            }).check()
    }

    // Show progress dialog
    fun showLoading(message: String) {
        progressDialog.setMessage(message)
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

    fun hideLoading() {
        progressDialog.dismiss()
    }

    // Check if user has granted storage permission
    fun storagePermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }

    // Get root database reference
//    fun getDatabaseReference(): DatabaseReference = FirebaseDatabase.getInstance().reference
//
//    // Get root firestore reference
//    fun getFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()
//
//    // Get Firebase Storage reference
//    fun getStorageReference(): StorageReference = FirebaseStorage.getInstance().reference
//
//    // Get FirebaseAuth instance
//    fun getFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
//
//    // Get user
//    fun getUser(): FirebaseUser? {
//        return FirebaseAuth.getInstance().currentUser
//    }
//
//    // Get user ID
//    fun getUid(): String {
//        val user = FirebaseAuth.getInstance().currentUser
//
//        return user!!.uid
//    }
//
//    fun getToken(): String {
//        var token:String? = null
//
//        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(object :
//            OnSuccessListener<InstanceIdResult> {
//            override fun onSuccess(p0: InstanceIdResult?) {
//                token = p0!!.token
//            }
//        })
//
//        return token!!
//    }
//
//    fun refreshToken() {
//        getDatabaseReference().child("users").child(getUid()).child("userToken").setValue(getToken())
//    }
}