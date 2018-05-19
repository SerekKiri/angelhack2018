package io.github.feelfree.docugram.ui.modules

import android.app.Activity
import io.github.feelfree.docugram.ui.modules.mainactivity.MainActivity
import io.github.feelfree.docugram.ui.modules.template.TemplateActivity

interface NavigatorApi {
    fun openMainActivity(activity : Activity)
    fun openTemplateActivity(activity: Activity, id : String)
}

class Navigator : NavigatorApi {
    override fun openTemplateActivity(activity: Activity, id: String) {
        activity.startActivity(TemplateActivity.createIntent(activity, id))
    }

    override fun openMainActivity(activity: Activity) {
        activity.startActivity(MainActivity.createIntent(activity))
    }


}