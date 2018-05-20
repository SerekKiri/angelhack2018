package io.github.feelfree.docugram.base
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {
    open fun showError(e: Throwable) {
        val baseActivity = activity as? BaseActivity
        if (activity != null && activity is BaseActivity) {
            (activity as BaseActivity).showErrorDialog(e)
        }
    }
}