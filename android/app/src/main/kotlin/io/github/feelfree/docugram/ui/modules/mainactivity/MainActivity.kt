package io.github.feelfree.docugram.ui.modules.mainactivity

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.design.internal.NavigationMenuView
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import io.github.feelfree.docugram.R
import io.github.feelfree.docugram.base.BaseActivity
import io.github.feelfree.docugram.ui.modules.NavigatorApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object {
        fun createIntent(context : Context) : Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    @Inject
    lateinit var navigator : NavigatorApi
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }

    private val navHeader by lazy { navigationView.getHeaderView(0) }
    private val actionBarToggle by lazy {
        ActionBarDrawerToggle(this,
                drawer_layout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        (navigationView.getChildAt(0) as NavigationMenuView).isVerticalScrollBarEnabled = false
        toolbar.tag = toolbar.overflowIcon // We want to save original overflow icon drawable into memory.
        setupNavigation()
        fab.setOnClickListener {
            navigator.openTemplateActivity(this, "ddd")
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        actionBarToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        actionBarToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (actionBarToggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    private fun setupNavigation() {
        drawer_layout.addDrawerListener(actionBarToggle)
        navigationView.setNavigationItemSelectedListener(this)
    }

    /*override fun openFragment(fragment: Fragment) {
        supportActionBar?.subtitle = null
        fab.isVisible = false
        fab.setOnClickListener(null)
        fab.isVisible = fragment is BaseNavigationView && userManagerApi.isUserAuthorized()
        val ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
        ft.replace(R.id.contentView, fragment)
        ft.commit()
        closeDrawer()
    }*/

    fun closeDrawer() =
            drawer_layout.closeDrawers()

    fun deselectItems() {
        val menu = navigationView.menu
        for (i in 0 until menu.size()) {
            menu.getItem(i).isChecked = false
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) closeDrawer()
        else {
            finish()
        }
    }
}