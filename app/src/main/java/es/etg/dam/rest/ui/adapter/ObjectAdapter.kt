package es.etg.dam.rest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.etg.dam.rest.R
import es.etg.dam.rest.database.ObjectEntity

class ObjectAdapter(
    context: Context,
    private val lista: List<ObjectEntity>
    ) : ArrayAdapter<ObjectEntity>(context, 0, lista) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_object, parent, false)

        val item = lista[position]
        val txtNombre = view.findViewById<TextView>(R.id.txtNombre)

        txtNombre.text = item.name

        return view
    }
}
