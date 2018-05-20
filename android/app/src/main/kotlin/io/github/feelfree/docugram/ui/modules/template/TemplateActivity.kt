package io.github.feelfree.docugram.ui.modules.template

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.github.feelfree.docugram.R
import io.github.feelfree.docugram.base.BaseActivity
import io.github.feelfree.docugram.models.Template
import io.github.feelfree.docugram.ui.adapter.TemplateAdapter
import io.github.feelfree.docugram.utils.isVisible
import io.github.feelfree.docugram.utils.prepare
import kotlinx.android.synthetic.main.activity_template.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class TemplateActivity : BaseActivity(), TemplateView {
    companion object {
        val EXTRA_ID = "EXTRA_ID"
        fun createIntent(context: Context, id : String): Intent {
            val intent = Intent(context, TemplateActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            return intent

        }
    }

    lateinit var id : String

    override fun showFields(template : Template) {
        loadingView.isVisible = false
        adapter.showFields(template.fields)
    }

    override fun close() {
        finish()
    }

    @Inject
    lateinit var adapter : TemplateAdapter


    @Inject lateinit var presenter : TemplatePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_template)
        setSupportActionBar(toolbar)

        val intent = intent
        val action = intent.action
        if (action != null) {
            id = intent.data.toString().substringAfter("/form/").removeSuffix("/")
        } else {
            id = intent.getStringExtra(EXTRA_ID)
        }
        adapter.submitListener = {
            presenter.sendSubmission(id, adapter.fields)
        }
        presenter.subscribe(this)
        // Setup RecyclerView
        recyclerView.prepare()
        recyclerView.adapter = adapter

        // Load data
        loadingView.isVisible = true
        presenter.loadTemplate(id)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }
}