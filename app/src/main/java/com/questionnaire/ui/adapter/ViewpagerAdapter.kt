package com.questionnaire.ui.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.questionnaire.R
import com.questionnaire.data.model.Question

/**
 * viewpager class for Questionnaire
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-11-18
 */
class ViewpagerAdapter(val questionsList: List<Question>) : PagerAdapter() {
    private val answers = HashMap<Int, Int>()
    private var score: Int = 0

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = container.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_question, null)
        val question: TextView = view.findViewById(R.id.question)
        if (position < questionsList.size) {
            val radioGroup: RadioGroup = view.findViewById(R.id.options_group)
            val optionList = questionsList[position].options
            for (i in 0 until optionList.size) {
                val radioButton = RadioButton(radioGroup.context)
                radioButton.text = optionList[i].answer
                radioButton.textSize = 12f
                radioButton.id = i
                radioGroup.addView(radioButton)
            }
            if (answers[position] != null) {
                radioGroup.check(answers[position]!!)
            }
            radioGroup.setOnCheckedChangeListener({ radioGroup, i ->
                val selectedId = radioGroup.checkedRadioButtonId
                val radioButton = radioGroup.findViewById(selectedId) as RadioButton
                //answerList.get(holder.options.tag as Int).answer = radioButton.text.toString()
                answers!![position] = selectedId
                countTheScore(answers)
            })
            question.text = questionsList[position].question

        } else {
            when {
                score < 7 -> question.text = view.context.getString(R.string.dont_match)
                score in 7..10 -> question.text = view.context.getString(R.string.looks_good)
                else -> question.text = view.context.getString(R.string.excellent)
            }

        }
        container?.addView(view)
        return view
    }

    private fun countTheScore(answers: HashMap<Int, Int>) {
        score = 0

        for ((key, value) in answers) {
            score += questionsList[key].options[value].marks
        }

    }

    override fun isViewFromObject(view: View, view2: Any): Boolean {
        return view === view2 as View
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return questionsList.size + 1
    }
}