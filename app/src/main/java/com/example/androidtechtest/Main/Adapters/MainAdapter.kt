package com.example.androidtechtest.Main.Adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.fitCenterTransform
import com.example.androidtechtest.Models.Team
import com.example.androidtechtest.databinding.AdapterTeamsBinding

class MainAdapter (onTeamListener: OnTeamListener) : RecyclerView.Adapter<MainViewHolder>() {
    var teams = mutableListOf<Team>()
    private var onTeamListener = onTeamListener
    fun setTeamsList(teams: List<Team>) {
        this.teams = teams.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterTeamsBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding, onTeamListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val team = teams[position]
        var progressBar = holder.binding.progressBar

        holder.binding.name.text = "Name: " + team.strAlternate
        holder.binding.stadium.text = "Stadium: " + team.strStadium
        var reqOpt: RequestOptions = RequestOptions
            .fitCenterTransform()
            .transform(RoundedCorners(5))
            .diskCacheStrategy(DiskCacheStrategy.ALL) // It will cache your image after loaded for first time
        // Overrides size of downloaded image and converts it's bitmaps to your desired image size;
        Glide.with(holder.itemView.context).load(team.strTeamBadge).listener(object :
            RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.visibility = View.GONE
                return false
            }
        }).apply(reqOpt).transition(
            DrawableTransitionOptions.withCrossFade())
            .into(holder.binding.badge)

    }

    override fun getItemCount(): Int {
        return teams.size
    }
}

class MainViewHolder constructor(val binding: AdapterTeamsBinding, onTeamListener: OnTeamListener) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    var onTeamListener = onTeamListener
    init {
        binding.root.setOnClickListener(this)
    }
    override fun onClick(p0: View?) {
        onTeamListener.onTeamClick(adapterPosition)
    }
}

interface OnTeamListener {
    fun onTeamClick(position: Int)
}