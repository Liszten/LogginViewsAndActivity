package at.fh.swengb.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class HigherOrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_higher_order)
        myHigherOrderFunction { x -> x*102 }
        kotlinHigherOrderWithList()
    }

    private fun kotlinHigherOrderWithList() {
        val list = LessonRepository.lessonsList()
        val bloder = Lecturer("Lukas Bloder")

        val heldByBloder = list.filter{bloder in it.lecturers}
        Log.e("Bloder", heldByBloder.toString())

        val topicMap = list.groupBy {it.topic}
        Log.e("TopicMap", topicMap.toString())

        val avgLecture = list.map{it.ratingAverage()}.average()
        Log.e("avg", avgLecture.toString())
    }

    private fun myHigherOrderFunction(param: (Int) -> Int) {
        if (param(6) == 612) {
            Log.v("Higher_Order","Congrats, your first lambda works :)")
        }
        else {
            Log.v("higher_Order", "Well, we can't ship that...")
        }
    }
}
