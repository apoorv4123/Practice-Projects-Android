package com.example.dynamiclistview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item.view.*

class ExpenseAdapter(val itemData: Array<Expense>) : BaseAdapter() {

    override fun getCount(): Int = itemData.size

    override fun getItem(position: Int): Any = itemData[position]

    override fun getItemId(position: Int): Long = itemData[position].hashCode().toLong()

    override fun getView(position: Int, convertView: View?, container: ViewGroup?): View {
        val itemView: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            itemView = LayoutInflater.from(container?.context).inflate(
                R.layout.list_item,
                container, false
            )

            viewHolder = ViewHolder()
            with(viewHolder) {
                itemname = itemView.findViewById(R.id.item_name)
                itemprice = itemView.findViewById(R.id.item_price)
                itemView.tag = this
            }
        } else {
            itemView = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        with(viewHolder) {
            itemname.text = itemData[position].item
            itemprice.text = itemData[position].getFormattedPrice()
        }

        return itemView
    }

    private class ViewHolder {
        lateinit var itemname: TextView
        lateinit var itemprice: TextView
    }
}
