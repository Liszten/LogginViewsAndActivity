package at.fh.swengb.loggingviewsandactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MyActivity", "onCreate")

        share_main.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                type = "text/plain"
                }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
            }

        open_views.setOnClickListener {
            val intent = Intent(this, ViewsActivity::class.java)
            startActivity(intent)
        }

        open_lessons.setOnClickListener() {
            val intent = Intent(this, LessonListActivity::class.java)
            startActivity(intent)
        }

        open_higher_order.setOnClickListener() {
            val intent = Intent(this, HigherOrderActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onStart() {
        super.onStart()
        Log.i("MyActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MyActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MyActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MyActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MyActivity", "onDestroy")
    }
}
