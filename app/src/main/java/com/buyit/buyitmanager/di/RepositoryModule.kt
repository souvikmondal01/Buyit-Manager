package com.buyit.buyitmanager.di

import com.buyit.buyitmanager.data.repositories.CategoryRepository
import com.buyit.buyitmanager.data.repositories.CategoryRepositoryImp
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCategoryRepository(
        db: FirebaseFirestore
    ): CategoryRepository {
        return CategoryRepositoryImp(db)
    }
}