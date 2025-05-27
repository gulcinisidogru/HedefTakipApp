package com.example.hedeftakipapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class SuggestedHabitsAdapter(
    private val onAddClick: (Habit) -> Unit
) : ListAdapter<Habit, SuggestedHabitsAdapter.HabitViewHolder>(HabitDiffCallback()) {

    // 1. ViewHolder sınıfı
    class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val habitName: TextView = itemView.findViewById(R.id.textViewSuggestedHabitName)
        private val habitDescription: TextView = itemView.findViewById(R.id.textViewSuggestedHabitDescription)
        private val addButton: Button = itemView.findViewById(R.id.buttonAddSuggestedHabit)

        // 2. View binding fonksiyonu
        fun bind(habit: Habit, onAddClick: (Habit) -> Unit) {
            habitName.text = habit.name
            habitDescription.text = habit.description

            // 3. Click listener'ı temizle ve yeniden ata
            addButton.setOnClickListener(null) // Önceki listener'ı temizle
            addButton.setOnClickListener { onAddClick(habit) }

            // 4. Tüm item'a tıklanabilirlik eklemek isterseniz:
            itemView.setOnClickListener { onAddClick(habit) }
        }
    }

    // 5. DiffUtil için callback
    private class HabitDiffCallback : DiffUtil.ItemCallback<Habit>() {
        override fun areItemsTheSame(oldItem: Habit, newItem: Habit): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Habit, newItem: Habit): Boolean {
            return oldItem == newItem
        }
    }

    // 6. ViewHolder oluşturma
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_suggested_habit, parent, false)
        return HabitViewHolder(view)
    }

    // 7. ViewHolder'a veri bağlama
    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.bind(getItem(position), onAddClick)
    }

    // 8. Ekstra fonksiyon: Liste güncelleme
    fun updateList(newList: List<Habit>) {
        submitList(newList.toMutableList()) // DiffUtil otomatik olarak karşılaştırır
    }
}