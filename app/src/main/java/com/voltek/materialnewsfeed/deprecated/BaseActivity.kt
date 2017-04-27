package com.voltek.materialnewsfeed.deprecated

import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.voltek.materialnewsfeed.NewsApp
import com.voltek.materialnewsfeed.R
import com.voltek.materialnewsfeed.navigation.proxy.Navigator
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

@Deprecated("Migrate to new MVP architecture")
abstract class BaseActivity : AppCompatActivity(), Navigator {

    override fun onResume() {
        super.onResume()
        NewsApp.getRouterBinder().setNavigator(this)
    }

    override fun onPause() {
        super.onPause()
        NewsApp.getRouterBinder().removeNavigator()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    protected fun setupToolbar(
            displayHomeAsUpEnabled: Boolean = false,
            displayShowHomeEnabled: Boolean = false) {

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)
        supportActionBar?.setDisplayShowHomeEnabled(displayShowHomeEnabled)
    }

    protected fun replaceFragment(fragment: Fragment,
                                  @IdRes id: Int,
                                  tag: String,
                                  shouldAddToBackStack: Boolean = false,
                                  shouldAnimate: Boolean = true) {

        val transaction = supportFragmentManager.beginTransaction()

        if (shouldAnimate)
            //transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)

        if (shouldAddToBackStack)
            transaction.addToBackStack(tag)

        transaction.replace(id, fragment, tag)
        transaction.commit()
    }
}