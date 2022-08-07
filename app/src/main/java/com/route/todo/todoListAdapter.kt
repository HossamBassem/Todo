package com.route.todo

import android.graphics.Color
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.route.todo.DataBase.Model.Todo

class todoListAdapter(var items:MutableList<Todo>?,var done:MutableList<Todo>?): RecyclerView.Adapter<todoListAdapter.ViewHolder>() {
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
val title:TextView =itemView.findViewById(R.id.title)
        val description:TextView=itemView.findViewById(R.id.discription)
var mark_as_done:MaterialButton=itemView.findViewById(R.id.mark_as_done)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_todo,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item=items!!.get(position)
        holder.title.setText(item.name)
        holder.description.setText(item.detail)
        holder.mark_as_done.setOnClickListener(object :View.OnClickListener {
            override fun onClick(p0: View?) {
                holder.mark_as_done.setIconTintResource(R.color.green)
                notifyDataSetChanged()


            }

        })



    }
    fun changeData(newItems:MutableList<Todo>,itemDone:MutableList<Todo>){
done=itemDone
items=newItems
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int =items?.size?:0
}