package com.questionnaire.ui.questionnaire

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.questionnaire.data.model.QuestionsList
import com.questionnaire.data.repository.QuestionnaireRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * ViewModel class for Questionnaire
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-11-18
 */
class QuestionnaireViewModel @Inject constructor(private val repo: QuestionnaireRepository) : ViewModel() {

    val stateQuestionsData = MutableLiveData<QuestionsList>()
    val errorData = MutableLiveData<String>()

    // Method for get the Question list

    fun getQuestionsList() {

        repo.getQuestionnaireDetails()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onQuestionListResponse, this::onError)
    }
    private fun onError(error: Throwable) {
        errorData.value = error.localizedMessage
    }

    private fun onQuestionListResponse(questionDetails: QuestionsList) {
        stateQuestionsData.value = questionDetails

    }

}