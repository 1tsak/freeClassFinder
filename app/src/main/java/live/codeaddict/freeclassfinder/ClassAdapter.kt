package live.codeaddict.freeclassfinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ClassAdapter(private val mList: List<ClassViewModel>):
    RecyclerView.Adapter<ClassAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.class_item,parent,false)
        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curItem = mList[position]
       holder.name.text= curItem.className
       holder.floor.text= curItem.classFloor
        holder.timeleft.text = curItem.time
//       holder.timeleft.text= curItem.className
    }

    override fun getItemCount(): Int {
        return mList.size
    }
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val name:TextView = itemView.findViewById(R.id.className)
        val floor:TextView = itemView.findViewById(R.id.floor)
        val capacity:TextView = itemView.findViewById(R.id.capacity)
        val timeleft:TextView = itemView.findViewById(R.id.time)
    }
}