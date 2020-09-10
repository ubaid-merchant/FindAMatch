package com.ubaidmerchant.findamatch.ui.main.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.ubaidmerchant.findamatch.R
import com.ubaidmerchant.findamatch.databinding.ItemCardBinding
import com.ubaidmerchant.findamatch.model.ResultsModel
import com.ubaidmerchant.findamatch.utils.gone
import com.ubaidmerchant.findamatch.utils.visible

/**
 * [RecyclerView.ViewHolder] implementation to inflate View for RecyclerView.
 * See [CardListAdapter]]
 */
class CardViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(result: ResultsModel, onItemClicked: (ResultsModel) -> Unit) {
        if (result.isSelected) {
            binding.clActions.gone()
            binding.tvProfileStatus.text = result.status
            binding.tvProfileStatus.visible()
        }

        binding.tvProfileName.text = result.name?.getName()
        binding.tvProfileDescription.text = result.location?.getDescription()
        binding.ivProfilePic.load(result.picture?.large) {
            placeholder(R.color.placeholderColor)
            error(R.color.placeholderColor)
        }

        binding.ivAccept.setOnClickListener {
            onItemClicked(result)
            binding.clActions.gone()
            binding.tvProfileStatus.text = "Accepted"
            binding.tvProfileStatus.visible()
        }

        binding.ivDecline.setOnClickListener {
            onItemClicked(result)
            binding.clActions.gone()
            binding.tvProfileStatus.text = "Declined"
            binding.tvProfileStatus.visible()
        }
    }
}
