package com.damarazka.myapplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView
import com.damarazka.doadandzikirapp.databinding.ItemArticleBinding
import com.damarazka.myapplication.model.ArticleItem
import com.damarazka.myapplication.ui.DetailArticleActivity
import com.damarazka.myapplication.utils.onItemCallBack

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    // variable to store dataset
    private var listArticle = ArrayList<ArticleItem>()
    // variable to give event click callback in Activity setOnItemCallback
    private var onItemCallback : onItemCallBack? = null

    fun setData(list: List<ArticleItem>) {
        listArticle.clear()
        listArticle.addAll(list)
    }

    //fun to set even click in item using interface
    fun setOnItemCallback(onItemCallback: onItemCallBack){
    this.onItemCallback = onItemCallback
    }

    inner class ArticleViewHolder(val view: ItemArticleBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticleViewHolder(
        ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = listArticle.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val data = listArticle[position]
        holder.view.itemArticle.setImageResource(data.imageArticle)
        // this is give click event for each item in ViewPager
        holder.itemView.setOnClickListener{
            // set event click in activity through interface
            onItemCallback?.onItemClick(data)
            // set event click directly through adapter
            //provide context for intent

        }
    }
}