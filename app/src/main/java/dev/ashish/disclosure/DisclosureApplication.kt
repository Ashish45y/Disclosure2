package dev.ashish.disclosure

import android.app.Application
import dev.ashish.disclosure.di.component.ApplicationComponent
import dev.ashish.disclosure.di.component.DaggerApplicationComponent
import dev.ashish.disclosure.di.module.ApplicationModule

class DisclosureApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}

