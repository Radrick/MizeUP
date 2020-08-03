package br.com.mizeup.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.mizeup.R
import br.com.mizeup.model.ObjectiveModel
import kotlinx.android.synthetic.main.activity_objective_on_boarding.*

class ObjectiveOnBoardingActivity : AppCompatActivity(), View.OnClickListener {

    private var mObjective = ObjectiveModel("", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objective_on_boarding)

        btn_scape_onboarding.setOnClickListener(this)
        btn_next_objective.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_scape_onboarding -> startActivity(Intent(this, MainActivity::class.java))
            R.id.btn_next_objective -> onCheckObjective()
        }
    }

    private fun onCheckObjective(){ //view: View) {

        var other_objective = fText_description_other_objective.text.toString()

        if (rButton_school.isChecked) {
            mObjective.objective = rButton_school.text.toString()
            nextActivity()
        } else if (rButton_university.isChecked) {
            mObjective.objective = rButton_university.text.toString()
            nextActivity()
        } else if (rButton_concourse.isChecked) {
            mObjective.objective = rButton_concourse.text.toString()
            nextActivity()
        } else if (rButton_language_course.isChecked) {
            mObjective.objective = rButton_language_course.text.toString()
            nextActivity()
        } else if (rButton_other.isChecked) {
            if (other_objective.isNotEmpty()) {
                mObjective.objective = rButton_other.text.toString()
                mObjective.descriptionObjective = other_objective
                nextActivity()
            } else {
                Toast.makeText(this, getString(R.string.especify_an_objective), Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(this, getString(R.string.choose_an_option), Toast.LENGTH_SHORT).show()
        }
    }

    private fun nextActivity(){
        startActivity(Intent(this, MainActivity::class.java))
    }
}