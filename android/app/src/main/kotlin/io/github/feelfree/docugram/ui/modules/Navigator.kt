package io.github.feelfree.docugram.ui.modules

import android.app.Activity
import io.github.feelfree.docugram.ui.modules.mainactivity.MainActivity

interface NavigatorApi {
    fun openMainActivity(activity : Activity)
}

class Navigator : NavigatorApi {
    override fun openMainActivity(activity: Activity) {
        activity.startActivity(MainActivity.createIntent(activity))
    }
}