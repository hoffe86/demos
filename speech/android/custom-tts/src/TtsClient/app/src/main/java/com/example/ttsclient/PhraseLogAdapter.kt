package com.example.ttsclient

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PhraseLogAdapter : RecyclerView.Adapter<PhraseLogAdapter.PhraseViewHolder>() {

    // The list of PhraseItems that the adapter will display
    private val phrases: MutableList<PhraseItem> = mutableListOf()

    /**
     * ViewHolder for each phrase item in the RecyclerView.
     * It holds references to the TextViews defined in item_phrase_log.xml.
     */
    class PhraseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPhraseText: TextView = itemView.findViewById(R.id.tvPhraseText)
        val tvPhraseTimestamps: TextView = itemView.findViewById(R.id.tvPhraseTimestamps)
    }

    /**
     * Called when RecyclerView needs a new [PhraseViewHolder] of the given type to represent
     * an item.
     * @param parent The ViewGroup into which the new View will be added.
     * @param viewType The view type of the new View.
     * @return A new PhraseViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhraseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_phrase_log, parent, false)
        return PhraseViewHolder(view)
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method updates the contents of the [PhraseViewHolder.itemView] to reflect the item at the given position.
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: PhraseViewHolder, position: Int) {
        val phrase = phrases[position]

        holder.tvPhraseText.text = "${position + 1}. ${phrase.text}" // Display index + text

        // Format and display timestamps and status
        val statusText = when (phrase.status) {
            "Pending" -> "Pending"
            "In Progress" -> "In Progress..."
            "Done" -> "Done (${String.format("%.2f", phrase.durationMs ?: 0.0)} ms)"
            "Error" -> "Error!"
            "Stopped" -> "Stopped"
            else -> "Unknown Status"
        }
        val timestampsText = "Start: ${formatTimestamp(phrase.startTime)}, End: ${formatTimestamp(phrase.endTime)}"

        holder.tvPhraseTimestamps.text = "$statusText | $timestampsText"

        // Set text color based on status for visual feedback
        when (phrase.status) {
            "Pending" -> {
                holder.tvPhraseText.setTextColor(Color.GRAY)
                holder.tvPhraseTimestamps.setTextColor(Color.DKGRAY)
            }
            "In Progress" -> {
                holder.tvPhraseText.setTextColor(Color.BLUE)
                holder.tvPhraseTimestamps.setTextColor(Color.BLUE)
            }
            "Done" -> {
                holder.tvPhraseText.setTextColor(Color.BLACK)
                holder.tvPhraseTimestamps.setTextColor(Color.parseColor("#006400")) // Dark Green
            }
            "Error" -> {
                holder.tvPhraseText.setTextColor(Color.RED)
                holder.tvPhraseTimestamps.setTextColor(Color.RED)
            }
            "Stopped" -> {
                holder.tvPhraseText.setTextColor(Color.BLACK)
                holder.tvPhraseTimestamps.setTextColor(Color.GRAY)
            }
            else -> { // Default colors
                holder.tvPhraseText.setTextColor(Color.BLACK)
                holder.tvPhraseTimestamps.setTextColor(Color.DKGRAY)
            }
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     */
    override fun getItemCount(): Int {
        return phrases.size
    }

    /**
     * Updates the entire list of phrases and notifies the RecyclerView.
     * Use this when a new set of phrases is loaded (e.g., language/phrase set changed).
     */
    fun submitList(newPhrases: List<PhraseItem>) {
        phrases.clear()
        phrases.addAll(newPhrases)
        notifyDataSetChanged() // Notifies the adapter that the entire dataset has changed
    }

    /**
     * Updates a single phrase item in the list and notifies the RecyclerView to re-draw it.
     * Use this when a phrase's status or timestamps change during synthesis.
     */
    fun updatePhraseStatus(phraseId: String, newStatus: String, startTime: Long? = null, endTime: Long? = null, durationMs: Double? = null) {
        val index = phrases.indexOfFirst { it.id == phraseId }
        if (index != -1) {
            phrases[index].status = newStatus
            if (startTime != null) phrases[index].startTime = startTime
            if (endTime != null) phrases[index].endTime = endTime
            if (durationMs != null) phrases[index].durationMs = durationMs
            notifyItemChanged(index) // Notifies only the specific item that changed
        }
    }

    /**
     * Helper function to format nanosecond timestamps into a readable string (e.g., "HH:MM:SS.ms").
     * For simplicity, this currently just returns "N/A" or "Timestamp". You might want to
     * implement a more detailed time formatting here (e.g., using SimpleDateFormat).
     */
    private fun formatTimestamp(nanos: Long?): String {
        return nanos?.let {
            // For now, we'll just show a simplified relative timestamp for visual check.
            // For true logging, you'll use the absolute nanoseconds or convert to Date/Time.
            // This is just to indicate a value is present.
            val millis = it / 1_000_000L
            // Convert millis to HH:MM:SS.ms format for better readability in UI
            val seconds = (millis / 1000) % 60
            val minutes = (millis / (1000 * 60)) % 60
            val hours = (millis / (1000 * 60 * 60)) % 24
            val remainingMillis = millis % 1000

            // Format to show milliseconds
            String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, remainingMillis)
        } ?: "N/A"
    }

    // You might want a method to get a phrase by ID if needed
    fun getPhraseById(id: String): PhraseItem? {
        return phrases.find { it.id == id }
    }
}
