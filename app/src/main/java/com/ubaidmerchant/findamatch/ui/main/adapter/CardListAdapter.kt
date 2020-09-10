package com.ubaidmerchant.findamatch.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ubaidmerchant.findamatch.databinding.ItemCardBinding
import com.ubaidmerchant.findamatch.model.ResultsModel
import com.ubaidmerchant.findamatch.ui.main.viewholder.CardViewHolder

/**
 * Adapter class [RecyclerView.Adapter] for [RecyclerView] which binds [ResultsModel] along with [CardViewHolder]
 * @param onItemClicked which will receive callback when item is clicked.
 */
class CardListAdapter(
    private val onItemClicked: (ResultsModel) -> Unit
) : ListAdapter<ResultsModel, CardViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CardViewHolder(
        ItemCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultsModel>() {
            override fun areItemsTheSame(oldItem: ResultsModel, newItem: ResultsModel): Boolean =
                oldItem.email == newItem.email

            override fun areContentsTheSame(oldItem: ResultsModel, newItem: ResultsModel): Boolean =
                oldItem == newItem
        }
    }
}
