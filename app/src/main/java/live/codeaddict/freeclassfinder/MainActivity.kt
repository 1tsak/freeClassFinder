package live.codeaddict.freeclassfinder

import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private val classList = ArrayList<ClassViewModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var classAdapter: ClassAdapter
    private var requestQueue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.recycler)
        classAdapter = ClassAdapter(classList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = classAdapter

        prepareClassList()
//        getTime()

    }

    private fun prepareClassList() {
        val queue = Volley.newRequestQueue(this)
        Toast.makeText(applicationContext,"Hoi",Toast.LENGTH_LONG).show()

        val url = "https://ak.iocoder.in/fcf/fetchClasses.php?time=a"
        val request =
            JsonObjectRequest(Request.Method.GET, url, null, { response ->
                try {
                    val classArray = response.getJSONArray("data")

                    for (i in 0 until classArray.length()) {
                        val classItem = classArray.getJSONObject(i)
                        classList.add(
                            ClassViewModel(
                                classItem.getString("id"),
                                classItem.getString("class_id"),
                                classItem.getString("class_name"),
                                classItem.getString("class_floor"),
                                classItem.getString("freetime")
                            )
                        )
                    }
                    Toast.makeText(applicationContext,"Hii",Toast.LENGTH_LONG).show()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                classAdapter.notifyDataSetChanged()
            }, { error -> error.printStackTrace() })
        queue.add(request);


    }

    private fun getTime(): String {
        val c: Calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("HH:mm:ss")
        val strTime: String = sdf.format(c.time)
        return strTime
    }
}