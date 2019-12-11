package at.fh.swengb.loggingviewsandactivity

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_lesson.view.*

class LessonAdapter(val clickListener: (lesson: Lesson) -> Unit): RecyclerView.Adapter<LessonViewHolder>() {

    private var lessonList = listOf<Lesson>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val studentItemView = inflater.inflate(R.layout.item_lesson, parent, false)
        return LessonViewHolder(studentItemView, clickListener)
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int = lessonList.size
        //To change body of created functions use File | Settings | File Templates.

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson = lessonList[position]
        holder.bindItem(lesson)
        //To change body of created functions use File | Settings | File Templates.
    }

    fun updateList(newlist: List<Lesson>) {
        lessonList = newlist
        notifyDataSetChanged()
    }
}

class LessonViewHolder(itemView: View, val clickListener: (lesson: Lesson) -> Unit): RecyclerView.ViewHolder(itemView) {



    fun bindItem(lesson: Lesson) {
        var average = 0.0

        if (lesson.ratings.isNotEmpty()) {
            average = lesson.ratingAverage().toDouble()
        }

        itemView.item_lesson_name.text = lesson.name
        itemView.item_lesson_date.text = lesson.date
        itemView.item_lesson_topic.text = lesson.topic
        itemView.item_lesson_lecturers.text = lesson.lecturers.map{it.name}.joinToString(", ")
        itemView.item_lesson_avg_rating_bar.rating = average.toFloat()
        itemView.item_lesson_avg_rating_value.text = average.toString()
        itemView.item_lesson_rating_count.text = lesson.ratings.size.toString()
        itemView.setOnClickListener {
            clickListener(lesson)
        }

    }
}