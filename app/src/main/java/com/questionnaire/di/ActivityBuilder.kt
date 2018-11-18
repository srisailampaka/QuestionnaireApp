package com.questionnaire.di

import com.questionnaire.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
internal abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun contributeMainInjector(): MainActivity


}