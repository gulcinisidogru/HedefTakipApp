import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hedeftakipapp.Habit
import com.example.hedeftakipapp.R

class HabitsAdapter : ListAdapter<Habit, HabitsAdapter.HabitViewHolder>(HabitDiffCallback()) {

    class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.textViewHabitName)
        private val descTextView: TextView = itemView.findViewById(R.id.textViewHabitDescription)

        fun bind(habit: Habit) {
            nameTextView.text = habit.name
            descTextView.text = habit.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_habit_profile, parent, false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class HabitDiffCallback : DiffUtil.ItemCallback<Habit>() {
    override fun areItemsTheSame(oldItem: Habit, newItem: Habit) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Habit, newItem: Habit) = oldItem == newItem
}