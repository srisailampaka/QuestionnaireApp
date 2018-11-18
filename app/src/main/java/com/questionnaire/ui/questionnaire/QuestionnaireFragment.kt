package com.questionnaire.ui.questionnaire

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.questionnaire.R
import com.questionnaire.data.model.Question
import com.questionnaire.data.model.QuestionsList
import com.questionnaire.ui.adapter.ViewpagerAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.questionnaire_fragment.*
import java.util.*
import javax.inject.Inject

private val TAG = QuestionnaireFragment::class.java.name


/**
 * Fragment class for Questionnaire list to show
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-11-18
 */
class QuestionnaireFragment : Fragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: QuestionnaireViewModel
    private var questionList = ArrayList<Question>()


    companion object {
        fun newInstance() = QuestionnaireFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.questionnaire_fragment, container, false)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(QuestionnaireViewModel::class.java)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerViewModel()
            viewModel.getQuestionsList()

    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private val stateQuestionObserver = Observer<QuestionsList> {
        Log.d(TAG, "data -> ${it!!.questions.size} ")
        questionList = it.questions as ArrayList<Question>
        //setAdapter()
        viewPager.adapter = ViewpagerAdapter(it.questions)
    }


    private val errorStateObserver = Observer<String> {
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()

    }

    private fun observerViewModel() {
        viewModel.stateQuestionsData.observe(this, stateQuestionObserver)

        viewModel.errorData.observe(this, errorStateObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.stateQuestionsData.removeObserver(stateQuestionObserver)
        viewModel.errorData.removeObserver(errorStateObserver)
    }
}