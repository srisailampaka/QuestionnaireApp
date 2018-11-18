package com.questionnaire.di

import android.content.Context
import com.questionnaire.data.repository.QuestionnaireRepository
import com.questionnaire.data.repository.QuestionnaireRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideNewsRepo(context: Context): QuestionnaireRepository = QuestionnaireRepositoryImpl(context)
}