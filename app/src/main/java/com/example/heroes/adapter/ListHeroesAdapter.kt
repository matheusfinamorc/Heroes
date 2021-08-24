package com.example.heroes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.heroes.R
import com.example.heroes.model.HeroesItem
import kotlinx.android.synthetic.main.item_hero.view.*

class ListHeroesAdapter(
    private val context: Context,
    private val heroes: MutableList<HeroesItem> = mutableListOf(),
    //var onItemClickListener: (hero: HeroesItem) -> Unit = {}
) : RecyclerView.Adapter<ListHeroesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListHeroesAdapter.ViewHolder {
        val viewCriada = LayoutInflater.from(context).inflate(
            R.layout.item_hero,
            parent,
            false
        )
        return ViewHolder(viewCriada)
    }

    override fun onBindViewHolder(holder: ListHeroesAdapter.ViewHolder, position: Int) {
        holder.vincula(heroes[position])
    }

    override fun getItemCount() = heroes.size

    fun appendSearch(heroes: List<HeroesItem>) {
        this.heroes.clear()
        this.heroes.addAll(heroes)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private lateinit var heroes: HeroesItem
        private val name by lazy { itemView.tv_name_hero }


        fun vincula(heroesItem: HeroesItem) {
            this.heroes = heroesItem
            name.text = heroes.name
        }

    }


}
