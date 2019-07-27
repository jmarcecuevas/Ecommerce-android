package com.marcecuevas.easybuy.main

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.marcecuevas.easybuy.R
import com.marcecuevas.easybuy.data.dao.ProductDAO
import com.marcecuevas.easybuy.data.dao.ProductDAOImpl
import com.marcecuevas.easybuy.data.network.ProductREST
import com.marcecuevas.easybuy.data.repository.ProductRepository
import com.marcecuevas.easybuy.data.repository.ProductRepositoryImpl
import com.marcecuevas.easybuy.viewModel.ProductViewModelFactory
import com.marcecuevas.hotelsapp.data.network.ConnectivityInterceptor
import com.marcecuevas.hotelsapp.data.network.ConnectivityInterceptorImpl
import com.marcecuevas.hotelsapp.utils.Font
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class EasyBuyApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@EasyBuyApplication))

        bind() from singleton { ProductREST(instance()) }
        bind<ProductDAO>() with singleton { ProductDAOImpl(instance()) }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind<ProductRepository>() with singleton { ProductRepositoryImpl(instance()) }
        bind() from provider { ProductViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        Font.instance.setFamilyName(applicationContext,"Gotham")

        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath(Font.instance.defaultPath())
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
}