package com.example.roadsigns.adapters

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.roadsigns.R
import com.example.roadsigns.models.SignModel


internal class SignsAdapter(ctx: Context, private val signs: ArrayList<SignModel>, var isDescriptionVisible: Boolean) : RecyclerView.Adapter<SignsAdapter.ViewHolder>() {

    private val ctx: Context

    init {
        this.ctx = ctx
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.signs_holder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.ivSign.setImageResource(signs[position].id)
        holder.explanations.text = signs[position].description

        if(isDescriptionVisible){
            holder.explanations.visibility = View.VISIBLE
        } else{
            holder.explanations.visibility = View.INVISIBLE
        }

        if(signs[position].isSOS){
            holder.ivSos.visibility = View.VISIBLE
        } else{
            holder.ivSos.visibility = View.INVISIBLE
        }


        holder.ivSign.setOnLongClickListener{
            val text = signs[position].description
            Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show()
            false

        }
    }

    override fun getItemCount(): Int {
        return signs.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivSign: ImageView
        var explanations: TextView
        var ivSos: ImageView

        init {
            ivSign = itemView.findViewById(R.id.ivSign)
            explanations = itemView.findViewById(R.id.tvExplanation)
            ivSos = itemView.findViewById(R.id.ivSOS)
        }
    }

    fun changeVisibleStatus(isDescriptionVisible: Boolean){
            this.isDescriptionVisible = isDescriptionVisible
    }
}