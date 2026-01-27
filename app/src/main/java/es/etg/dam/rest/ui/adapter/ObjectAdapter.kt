package es.etg.dam.rest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.etg.dam.rest.R
import es.etg.dam.rest.database.ObjectEntity

class ObjectAdapter(private val list: List<ObjectEntity>) :
    RecyclerView.Adapter<ObjectAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.txtName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_object, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].name
    }

    override fun getItemCount(): Int = list.size
}
