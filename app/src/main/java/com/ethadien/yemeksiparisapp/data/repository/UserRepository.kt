package com.ethadien.yemeksiparisapp.data.repository

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ethadien.yemeksiparisapp.data.entity.UserModel
import com.ethadien.yemeksiparisapp.utils.Constants.COLLECTION_PATH
import com.ethadien.yemeksiparisapp.utils.Constants.E_MAIL
import com.ethadien.yemeksiparisapp.utils.Constants.FAILURE
import com.ethadien.yemeksiparisapp.utils.Constants.ID
import com.ethadien.yemeksiparisapp.utils.Constants.NICKNAME
import com.ethadien.yemeksiparisapp.utils.Constants.PHONE_NUMBER
import com.ethadien.yemeksiparisapp.utils.Constants.SIGN_IN
import com.ethadien.yemeksiparisapp.utils.Constants.SIGN_UP
import com.ethadien.yemeksiparisapp.utils.Constants.SUCCESS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserRepository(var auth : FirebaseAuth, var db : FirebaseFirestore) {

    var isSignIn = MutableLiveData<Boolean>()

    var isSignUp = MutableLiveData<Boolean>()

    var userModelInfo = MutableLiveData<UserModel>()


    fun signIn(eMail: String, password: String) {

        auth.signInWithEmailAndPassword(eMail, password).addOnCompleteListener { task ->

            if (task.isSuccessful) {
                isSignIn.value = true
                Log.d(SIGN_IN, SUCCESS)
            } else {
                isSignIn.value = false
                Log.w(SIGN_IN, FAILURE, task.exception)
            }
        }
    }

    fun signUp(eMail: String, password: String, nickname: String, phoneNumber: String) {

        auth.createUserWithEmailAndPassword(eMail, password).addOnCompleteListener { task ->

            if (task.isSuccessful) {

                val currentUser = auth.currentUser
                currentUser?.let { fbUser ->
                    val user = hashMapOf(
                        ID to fbUser.uid,
                        E_MAIL to eMail,
                        NICKNAME to nickname,
                        PHONE_NUMBER to phoneNumber
                    )

                    db.collection(COLLECTION_PATH).document(fbUser.uid)
                        .set(user)
                        .addOnSuccessListener {
                            isSignUp.value = true
                            Log.d(SIGN_UP, SUCCESS)
                        }
                        .addOnFailureListener { e ->
                            isSignUp.value = false
                            Log.w(SIGN_UP, FAILURE, e)
                        }
                }

            } else {
                isSignUp.value = false
                Log.w(SIGN_UP, FAILURE, task.exception)
            }
        }
    }

    fun getUserInfo() {
        auth.currentUser?.let { user ->

            val docRef = db.collection(COLLECTION_PATH).document(user.uid)
            docRef.get()
                .addOnSuccessListener { document ->
                    document?.let {
                        userModelInfo.value = UserModel(
                            user.email,
                            document.get("nickname") as String,
                            document.get("phonenumber") as String
                        )
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(ContentValues.TAG, "get failed with ", exception)
                }
        }
    }

    fun signOut() {
        auth.signOut()
    }
}