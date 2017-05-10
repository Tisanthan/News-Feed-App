package com.voltek.materialnewsfeed.presentation.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding2.view.RxView
import com.voltek.materialnewsfeed.R
import com.voltek.materialnewsfeed.data.entity.Article
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_article.view.*
import kotlinx.android.synthetic.main.item_title.view.*

class ListAdapter(private val mContext: Context, private var mItems: MutableList<Article>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TITLE = 0
        private const val ARTICLE = 1
    }

    private var mOnItemClickSubject: PublishSubject<Article> = PublishSubject.create()

    fun getOnItemClickObservable(): Observable<Article> = mOnItemClickSubject

    class ViewHolderTitle(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.tv_source_title
    }

    class ViewHolderArticle(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.im_image
        val title: TextView = view.tv_title
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        when (viewType) {
            TITLE -> {
                val view = layoutInflater.inflate(R.layout.item_title, parent, false)
                return ViewHolderTitle(view)
            }
            else -> {
                val view = layoutInflater.inflate(R.layout.item_article, parent, false)
                return ViewHolderArticle(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (holder?.itemViewType) {
            TITLE -> bindTitle(holder as ViewHolderTitle, position)
            ARTICLE -> bindArticle(holder as ViewHolderArticle, position)
        }
    }

    private fun bindTitle(holder: ViewHolderTitle, position: Int) {
        val item = mItems[position]
        holder.title.text = item.source
    }

    private fun bindArticle(holder: ViewHolderArticle, position: Int) {
        val item = mItems[position]

        RxView.clicks(holder.itemView).subscribe({ mOnItemClickSubject.onNext(item) })

        Glide
                .with(mContext)
                .load(item.urlToImage)
                .placeholder(R.drawable.im_news_placeholder)
                .centerCrop()
                .dontAnimate()
                .into(holder.image)

        holder.title.text = item.title
    }

    override fun getItemCount(): Int = mItems.size

    override fun getItemViewType(position: Int): Int =
            if (mItems[position].source.isEmpty()) ARTICLE else TITLE

    fun replace(items: List<Article>) {
        mItems.clear()
        mItems.addAll(items)
        notifyDataSetChanged()
    }
}
