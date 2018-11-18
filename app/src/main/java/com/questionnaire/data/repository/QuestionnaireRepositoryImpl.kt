package com.questionnaire.data.repository

import android.content.Context
import com.google.gson.Gson
import com.questionnaire.R
import com.questionnaire.data.model.QuestionsList
import io.reactivex.Single

/**
 * repositoryImpl class for Questionnaire
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-11-18
 */
class QuestionnaireRepositoryImpl(
        private val context: Context) : QuestionnaireRepository {
    override fun getQuestionnaireDetails(): Single<QuestionsList> {
        val myJson = context.resources.openRawResource(R.raw.questionnaire).bufferedReader().use { it.readText() }
        return Single.just(Gson().fromJson(myJson, QuestionsList::class.java))
    }
}