package io.github.feelfree.docugram.base

import io.reactivex.disposables.CompositeDisposable

// Should be extended by all Presenters.
open class BasePresenter<T : BaseView> {
    var compositeObservable = CompositeDisposable()
    var view : T? = null
    val isSubscribed : Boolean
        get() = view != null

    open fun subscribe(view: T) {
        this.view = view
    }

    open fun unsubscribe() {
        view = null
        compositeObservable.clear()
    }
}