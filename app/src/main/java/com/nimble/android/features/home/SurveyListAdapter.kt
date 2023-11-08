package com.nimble.android.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nimble.android.api.response.survey.Survey
import com.nimble.android.databinding.SurveysCardItemBinding

class SurveyListAdapter(private val clickListener: OnClickListener):
    ListAdapter<Survey, SurveyListAdapter.SurveyViewHolder>(DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurveyViewHolder {
        return SurveyViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SurveyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Survey>() {
        override fun areItemsTheSame(oldItem: Survey, newItem: Survey): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Survey, newItem: Survey): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class OnClickListener(val clickListener: (survey: Survey) -> Unit) {
        fun onClick(survey: Survey) = clickListener(survey)
    }

    class SurveyViewHolder private constructor(private val binding: SurveysCardItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Survey, clickListener: OnClickListener) {
            binding.survey = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun create(parent: ViewGroup) : SurveyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SurveysCardItemBinding.inflate(layoutInflater, parent, false)
                return SurveyViewHolder(binding)
            }
        }
    }
}