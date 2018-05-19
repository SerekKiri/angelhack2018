package io.github.feelfree.docugram.ui.modules.template

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
    @Inject
    lateinit var adapter : TemplateAdapter


    @Inject lateinit var presenter : TemplatePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_template)
        setSupportActionBar(toolbar)
        // Setup RecyclerView
        recyclerView.prepare()
        recyclerView.adapter = adapter

        swiperefresh.setOnRefreshListener {
        }

        // Load data
        loadingView.isVisible = true
        presenter.subscribe(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }
}