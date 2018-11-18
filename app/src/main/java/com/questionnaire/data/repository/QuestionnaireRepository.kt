package com.questionnaire.data.repository

import com.questionnaire.data.model.QuestionsList
import io.reactivex.Single
/**
 * Repository class for Questionnaire
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-11-18
 */
interface QuestionnaireRepository {
    fun getQuestionnaireDetails(): Single<QuestionsList>
}