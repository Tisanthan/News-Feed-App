package com.voltek.materialnewsfeed.presentation.list

import com.voltek.materialnewsfeed.data.entity.Article
import com.voltek.materialnewsfeed.navigation.proxy.Command

class CommandOpenArticleDetails(val article: Article) : Command()