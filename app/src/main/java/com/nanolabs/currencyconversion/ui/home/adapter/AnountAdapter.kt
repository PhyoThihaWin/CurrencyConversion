package com.nanolabs.currencyconversion.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nanolabs.currencyconversion.R
import com.nanolabs.currencyconversion.model.Rate
import com.nanolabs.currencyconversion.utils.formatDecimal


class AmountAdapter(val context: Context,val currency:String,val usd: Double,val rateList:List<Rate>) :
    RecyclerView.Adapter<AmountAdapter.MyViewHolder>() {




    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_rate, viewGroup, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val rate = rateList[position]
        holder.txtFullName.text = rate.fullName
        holder.txtShortName.text = rate.rateName.substring(3, 6)
        holder.txtAmount.text = (rate.rate * usd).formatDecimal()+ " "+currency
    }

    override fun getItemCount(): Int {
        return if (rateList == null) 0 else rateList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtFullName: TextView = itemView.findViewById(R.id.txt_fullName)
        val txtShortName: TextView = itemView.findViewById(R.id.txt_shortName)
        val txtAmount: TextView = itemView.findViewById(R.id.txt_amount)
    }


}


