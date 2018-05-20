package io.github.feelfree.docugram.ui.modules.notifications

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.feelfree.docugram.R
import io.github.feelfree.docugram.base.BaseActivity
import io.github.feelfree.docugram.base.BaseFragment
import io.github.feelfree.docugram.models.Notification
import io.github.feelfree.docugram.ui.adapter.NotificationAdapter
import io.github.feelfree.docugram.utils.CredentialPreferencesApi
import io.github.feelfree.docugram.utils.UserManagerApi
import io.github.feelfree.docugram.utils.isVisible
import io.github.feelfree.docugram.utils.prepare
import kotlinx.android.synthetic.main.fragment_feed.*
import javax.inject.Inject


class NotificationFragment : BaseFragment(), NotificationView {
    override fun showErrorDialog(e: Throwable) {
        (activity!! as BaseActivity).showErrorDialog(e)
    }

    companion object {
        fun newInstance() : Fragment {
            return NotificationFragment()
        }
    }

    @Inject
    lateinit var presenter : NotificationPresenter

    @Inject
    lateinit var credentialPreferencesApi: CredentialPreferencesApi

    @Inject
    lateinit var adapter : NotificationAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadingView.isVisible = true
        presenter.subscribe(this)
        // Setup RecyclerView
        recyclerView.prepare()
        recyclerView.adapter = adapter


        // Load data
        loadingView.isVisible = true
        presenter.getNotifications(credentialPreferencesApi.email!!)
    }

    override fun showNotifications(notification: List<Notification>) {
        adapter.showNotifications(notification)
        loadingView.isVisible = false
    }
}