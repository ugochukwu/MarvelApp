package com.onwordiesquire.mobile.marvelapp.presentation.characterSearch

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.onwordiesquire.mobile.marvelapp.R
import com.onwordiesquire.mobile.marvelapp.data.model.MarvelCharacter
import com.onwordiesquire.mobile.marvelapp.data.model.RecentSearch

/**
 * Created by michel.onwordi on 13/07/2017.
 */
class ItemListAdapter(val listener: (name: String) -> Unit) : RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun setData(recentSearches:List<RecentSearch>){

    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit @BindView(R.id.character_name) var textView: TextView
        lateinit var data: MarvelCharacter

        fun bind(character: MarvelCharacter) {
            data = character
            textView.text = character.name
        }

        @OnClick(R.id.name_container)
        fun onItemClick() {
            listener(data.name)
        }
    }

    interface ItemClickListener {
        fun onItemClick(characterName: String)
    }
}