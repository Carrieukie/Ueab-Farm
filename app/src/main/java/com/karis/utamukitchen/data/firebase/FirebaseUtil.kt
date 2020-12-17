package com.karis.utamukitchen.data.firebase

//import android.app.Activity
//import com.firebase.ui.auth.AuthUI
//import com.firebase.ui.auth.AuthUI.IdpConfig.PhoneBuilder
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseAuth.AuthStateListener
//import java.util.*


object FirebaseUtil {
//    private var firebaseUtil: FirebaseUtil? = null
//    var mFirebaseAuth: FirebaseAuth? = null
//    var mAuthListener: AuthStateListener? = null
//    private const val RC_SIGN_IN = 123
//    private var caller: Activity? = null
//
//    fun callReference(callerActivity: Activity?) {
//        if (firebaseUtil == null) {
//            caller = callerActivity
//            mAuthListener = AuthStateListener { firebaseAuth: FirebaseAuth ->
//                if (firebaseAuth.currentUser == null) {
//                    signIn()
//                }
//            }
//        }
//    }
//
//    private fun signIn() {
//        val providers = listOf(
//            PhoneBuilder().build()
//        )
//
//        caller!!.startActivityForResult(
//            AuthUI.getInstance()
//                .createSignInIntentBuilder()
//                .setAvailableProviders(providers)
//                .setIsSmartLockEnabled(false)
//                .build(),
//            RC_SIGN_IN
//        )
//    }
//
//    fun attachListener() {
//        mFirebaseAuth!!.addAuthStateListener(mAuthListener!!)
//    }
//
//    fun detachListener() {
//        mFirebaseAuth!!.removeAuthStateListener(mAuthListener!!)
//    }
}