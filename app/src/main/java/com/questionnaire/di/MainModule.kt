package com.questionnaire.di

import com.questionnaire.ui.questionnaire.QuestionnaireFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class MainModule {
    @ContributesAndroidInjector
    internal abstract fun contributeQuestionnaireFragmentInjector(): QuestionnaireFragment
}