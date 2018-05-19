package io.github.feelfree.docugram.api

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx2.Rx2Apollo
import io.reactivex.Single
import io.reactivex.functions.Function

class ApolloRxWrapper <T: Any> : Function<Response<T>, T> {
    override fun apply(t: Response<T>): T {
        return t.data()!!
    }
}

fun <T : Any> createApolloCall(call : ApolloCall<T>) : Single<T> {
    return Single.fromObservable(Rx2Apollo.from(call))
            .map(ApolloRxWrapper())
}