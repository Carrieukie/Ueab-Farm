package com.karis.utamukitchen.data.firebase

import android.annotation.SuppressLint
import android.app.Activity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth


@SuppressLint("StaticFieldLeak")
object FirebaseUtil {
    private var firebaseUtil: FirebaseUtil? = null
    var mFirebseAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null
    private const val RC_SIGN_IN = 123

    fun callReference(callerActivity: Activity) {
        if (firebaseUtil == null) {
            mFirebseAuth = FirebaseAuth.getInstance()
            mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth: FirebaseAuth ->
                if (firebaseAuth.currentUser == null) {
                    signIn(callerActivity)
                }
            }
        }
    }

    private fun signIn(callerActivity: Activity) {
        val providers = listOf(
            AuthUI.IdpConfig.PhoneBuilder().build()
        )

        callerActivity.startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setIsSmartLockEnabled(false)
                .build(),
            RC_SIGN_IN
        )
    }

    fun attachListener() {
        mFirebseAuth!!.addAuthStateListener(mAuthListener!!)
    }

    fun detachListener() {
        mFirebseAuth!!.removeAuthStateListener(mAuthListener!!)
    }
}