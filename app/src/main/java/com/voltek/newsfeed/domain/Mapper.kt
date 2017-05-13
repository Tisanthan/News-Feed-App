package com.voltek.newsfeed.domain

import com.voltek.newsfeed.data.entity.ArticleRAW
import com.voltek.newsfeed.data.entity.SourceRAW
import com.voltek.newsfeed.domain.entity.ArticleUI
import com.voltek.newsfeed.domain.entity.SourceUI

object Mapper {

    fun ArticleRAWtoArticleUI(item: ArticleRAW): ArticleUI =
            ArticleUI(
                    title = item.title,
                    description = item.description,
                    url = item.url,
                    urlToImage = item.urlToImage
            )


    fun SourceRAWtoSourceUI(item: SourceRAW): SourceUI =
            SourceUI(
                    id = item.id,
                    name = item.name,
                    description = item.description,
                    url = item.url,
                    category = item.category,
                    country = item.country,
                    isEnabled = item.isEnabled
            )
}
