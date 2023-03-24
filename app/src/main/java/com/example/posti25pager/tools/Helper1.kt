package com.example.posti25pager.tools

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.posti25pager.models.Post
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class Helper1 : AppCompatActivity() {


  /*
    val db1= FirebaseFirestore.getInstance().collection(POST_REF)

    private fun downloadPosts(): ArrayList<Post> {
        val posts = ArrayList<Post>()

        db1.addSnapshotListener { value, error ->
                if (value != null) {
                    for (doc in value.documents) {
                        val post = Helper().retrivePostFromFirestore(doc)
                        if (post.postNum>10){
                            posts.add(post)
                        }
                    }
//                        logi("57  posts.size=${posts.size}")
                    //    val posts1 = createSuffelPosts(posts)
                   // savePosts(posts)
                   // pref.edit().putInt(SHARPREF_TOTAL_POSTS_SIZE, posts.size).apply()
                }
            }
        return posts
    }*/

//    val db = Firebase.firestore
//    val collectionRef = db.collection("posts")

    val db = FirebaseFirestore.getInstance()
    val collectionRef = db.collection(POST_REF)

    suspend fun changeGradeValue(firstItem: Int, lastItem: Int) {
        for (i in firstItem..lastItem) {
            val postRef = db.collection(POST_REF).document("post$i")
            val postDoc = postRef.get().await()
            if (postDoc.exists()) {
                val grade = postDoc.getLong("grade")?.toInt() ?: 50
                if (grade != 50) {
                    GlobalScope.launch {
                        val batch = db.batch()
                        batch.update(postRef, "grade", 50)
                        batch.commit().await()
                    }
                }
            }
        }
    }

    fun createCenteredTextView(post:Post, layout: ConstraintLayout) {
        val context = layout.context
        val message="num=${post.postNum} grade=${post.grade}"
        val textView = createTextView(message, context)
        textView.id= View.generateViewId()
        textView.textSize=20f
        textView.setBackgroundColor(Color.WHITE)
        addTextViewToLayout(textView, layout)
        centerTextView(textView, layout)
    }

    fun createTextView(message: String, context: Context): TextView {
        val textView = TextView(context)
        textView.text = message
        textView.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        return textView
    }

    fun addTextViewToLayout(textView: TextView, layout: ConstraintLayout) {
        layout.addView(textView)
    }

    fun centerTextView(textView: TextView, layout: ConstraintLayout) {
        val constraints = ConstraintSet()
        constraints.clone(layout)
        constraints.connect(textView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
        constraints.connect(textView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
        constraints.connect(textView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0)
        constraints.connect(textView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)
        constraints.centerHorizontally(textView.id, ConstraintSet.PARENT_ID)
        constraints.centerVertically(textView.id, ConstraintSet.PARENT_ID)
        constraints.applyTo(layout)
    }
    fun logi(message: String) {
        Log.i("gg", message)
    }
}