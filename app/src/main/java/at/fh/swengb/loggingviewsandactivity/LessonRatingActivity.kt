package at.fh.swengb.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import at.fh.swengb.loggingviewsandactivity.LessonRepository.rateLesson
import kotlinx.android.synthetic.main.activity_lesson_rating.*

class LessonRatingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_rating)

        val lessonID = intent.getStringExtra(LessonListActivity.EXTRA_LESSON_ID)

        if (lessonID != null) {
           LessonRepository.lessonById(lessonID, success = {
               lesson_rating_header.text =it.name
           }, error = {})

            rate_lesson.setOnClickListener {
                rateLesson(lessonID, LessonRating(lesson_rating_bar.rating,lesson_feedback.text.toString()), success = {}, error = { Log.e("lessonsListFailed", "alsoFailed")})
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
        else {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }


    }

    companion object {
        val EXTRA_ADDED_OR_EDITED_RESULT = "EXTRA_ADDED_OR_EDITED_RESULT"
    }
}
