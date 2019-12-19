package at.fh.swengb.loggingviewsandactivity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class LessonRating(val ratingValue: Float, val feedback: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LessonRating

        if (ratingValue != other.ratingValue) return false
        if (feedback != other.feedback) return false

        return true
    }



    override fun toString(): String {
        return "LessonRating(ratingValue=$ratingValue, feedback='$feedback')"
    }

    override fun hashCode(): Int {
        var result = ratingValue.hashCode()
        result = 31 * result + feedback.hashCode()
        return result
    }


}