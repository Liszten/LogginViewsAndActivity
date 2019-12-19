package at.fh.swengb.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_lesson_list.*


class LessonListActivity : AppCompatActivity() {
    val lessonAdapter = LessonAdapter() {
        val implicitIntent = Intent(this, LessonRatingActivity::class.java)
        implicitIntent.putExtra(EXTRA_LESSON_ID, it.id)
        startActivityForResult(implicitIntent, ADD_OR_EDIT_RATING_REQUEST)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)


        //lessonAdapter.updateList(LessonRepository.lessonsList())
        lesson_recycler_view.layoutManager = LinearLayoutManager(this)
        lesson_recycler_view.adapter = lessonAdapter

        parseJson()

        //SleepyAsyncTask().execute()

        LessonRepository.lessonsList(
            success = {
                // handle success
                lessonAdapter.updateList(it)
            },
            error = {
                // handle error
                Log.e("LesssonListError", it)
            }
        )

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_OR_EDIT_RATING_REQUEST && resultCode == Activity.RESULT_OK) {
            val resultExtra = data?.getStringExtra(LessonRatingActivity.EXTRA_ADDED_OR_EDITED_RESULT)
            //lessonAdapter.updateList(LessonRepository.lessonsList())
            Log.e("RESULT_EXTRA", "$resultExtra")
        }
    }

    companion object {
        val EXTRA_LESSON_ID = "LESSON_ID_EXTRA"
        val ADD_OR_EDIT_RATING_REQUEST = 1
    }

    fun parseJson() {

        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter<Lesson>(Lesson::class.java)
        val json = """
            {
                "id": "1",
                "name": "Lecture 0",
                "date": "09.10.2019",
                "topic": "Introduction",
                "type": "LECTURE",
                "lecturers": [
                    {
                        "name": "Lukas Bloder"
                    },
                    {
                        "name": "Sanja Illes"
                    }
                ],
                "ratings": []
            }
        """
        val result = jsonAdapter.fromJson(json)


    }
}
