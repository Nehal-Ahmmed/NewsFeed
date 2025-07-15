package com.nhbhuiyan.newsfeed.di

import android.app.Application
import androidx.room.Room
import com.nhbhuiyan.newsfeed.data.local.NewsDao
import com.nhbhuiyan.newsfeed.data.local.NewsDatabase
import com.nhbhuiyan.newsfeed.data.local.NewsTypeConverter
import com.nhbhuiyan.newsfeed.data.manager.LocalUserManagerImpl
import com.nhbhuiyan.newsfeed.data.remote.dto.NewsApi
import com.nhbhuiyan.newsfeed.data.repository.NewsRepositoryImpl
import com.nhbhuiyan.newsfeed.domain.manager.LocalUserManager
import com.nhbhuiyan.newsfeed.domain.repository.NewsRepository
import com.nhbhuiyan.newsfeed.domain.usecase.app_entry.AppEntryUsecases
import com.nhbhuiyan.newsfeed.domain.usecase.app_entry.ReadAppEntry
import com.nhbhuiyan.newsfeed.domain.usecase.app_entry.SaveAppEntry
import com.nhbhuiyan.newsfeed.domain.usecase.news.DeleteArticle
import com.nhbhuiyan.newsfeed.domain.usecase.news.GetNews
import com.nhbhuiyan.newsfeed.domain.usecase.news.NewsUsecases
import com.nhbhuiyan.newsfeed.domain.usecase.news.SearchNews
import com.nhbhuiyan.newsfeed.domain.usecase.news.SelectArticle
import com.nhbhuiyan.newsfeed.domain.usecase.news.SelectArticles
import com.nhbhuiyan.newsfeed.domain.usecase.news.UpsertArticle
import com.nhbhuiyan.newsfeed.util.Constants.BASE_URL
import com.nhbhuiyan.newsfeed.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    //provider func for local user manager  and api calling matters:
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager) = AppEntryUsecases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUsecases {
        return NewsUsecases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsDao),
            selectArticles = SelectArticles(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            selectArticle = SelectArticle(newsDao)
        )
    }

    //provider func for local room database:
    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}