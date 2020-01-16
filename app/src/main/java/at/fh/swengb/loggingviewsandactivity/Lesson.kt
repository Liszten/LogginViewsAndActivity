package at.fh.swengb.loggingviewsandactivity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Lesson(val id: String,
             val name: String,
             val date: String,
             val topic: String,
             val type: LessonType,
             val lecturers: List<Lecturer>,
             val ratings: MutableList<LessonRating>,
             val imageUrl: String) {

    fun ratingAverage(): Float = ratings.map { it.ratingValue }.average().toFloat()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Lesson

        if (id != other.id) return false
        if (name != other.name) return false
        if (date != other.date) return false
        if (topic != other.topic) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + topic.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }

    override fun toString(): String {
        return "Lesson(id='$id', name='$name', date='$date', topic='$topic', type=$type)"
    }

}