package com.example.hedeftakipapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter, günlük görevler listesini alır ve her bir görevi RecyclerView'da görüntüler.
class DailyTasksAdapter(private val dailyTasks: List<String>) : RecyclerView.Adapter<DailyTasksAdapter.DailyTaskViewHolder>() {

    // RecyclerView öğesi oluşturulurken çağrılır
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyTaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_daily_task, parent, false)
        return DailyTaskViewHolder(view)
    }

    // Her bir öğe (item) için bind işlemi yapılır
    override fun onBindViewHolder(holder: DailyTaskViewHolder, position: Int) {
        holder.bind(dailyTasks[position])
    }

    // Verinin toplam uzunluğunu döndürür
    override fun getItemCount(): Int = dailyTasks.size

    // ViewHolder sınıfı, her bir öğenin görünümünü yönetir
    inner class DailyTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskName: TextView = itemView.findViewById(R.id.textViewDailyTaskName)

        // Görevi view ile bağlar
        fun bind(task: String) {
            taskName.text = task
        }
    }
}
