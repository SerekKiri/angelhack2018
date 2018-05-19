package io.github.feelfree.docugram.di.modules

import android.content.Context
import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import io.github.feelfree.docugram.DocugramApp
import io.github.feelfree.docugram.base.ApplicationSchedulers
import io.github.feelfree.docugram.base.Schedulers
import io.github.feelfree.docugram.utils.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

// Provides network-related classes
@Module
class NetworkModule {
    @Provides
    fun provideApplicationSchedulers() : Schedulers = ApplicationSchedulers()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(DocugramApp.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideApollo(okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient.builder()
                .serverUrl(DocugramApp.BASE_URL)
                .okHttpClient(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideCredentialPreferences(context: Context) : CredentialPreferencesApi {
        return CredentialPreferences(context)
    }

    @Provides
    @Singleton
    fun provideUserManagerApi(credentialPreferencesApi: CredentialPreferencesApi) : UserManagerApi {
        return UserManager(credentialPreferencesApi)
    }
}