package com.example.ideasai.di

import com.example.ideasai.core.data.datasource.FavoriteDataSource
import com.example.ideasai.core.data.datasource.FavoriteDataSourceImpl
import com.example.ideasai.core.data.datasource.GenerativeAiDataSource
import com.example.ideasai.core.data.datasource.GenerativeAiDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {

    @Binds
    abstract fun bindGenerativeAiDataSource(
        generativeAiDataSourceImpl: GenerativeAiDataSourceImpl
    ): GenerativeAiDataSource

    @Binds
    abstract fun bindFavoriteDataSource(
        favoriteDataSourceImpl: FavoriteDataSourceImpl
    ): FavoriteDataSource
}