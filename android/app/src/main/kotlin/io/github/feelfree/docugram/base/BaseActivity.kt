package io.github.feelfree.docugram.base

import android.support.v7.app.AlertDialog
import dagger.android.support.DaggerAppCompatActivity

// This class should be extended in all activities in this app. Place global-activity settings here
// Every activity that extends this class should be defined in ActivityBuilder
abstract class BaseActivity : DaggerAppCompatActivity() {
    fun showErrorDialog(e : Throwable) {
        AlertDialog.Builder(this)
                .setMessage(e.message)
                .setTitle("Error occured")
                .setPositiveButton(android.R.string.ok, null)
                .create()
                .show()
        throw e
    }
}