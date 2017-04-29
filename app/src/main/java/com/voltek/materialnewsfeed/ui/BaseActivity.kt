package com.voltek.materialnewsfeed.ui

import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.voltek.materialnewsfeed.NewsApp
import com.voltek.materialnewsfeed.navigation.proxy.Navigator
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

abstract class BaseActivity : MvpAppCompatActivity(),
        Navigator {

    override fun onResume() {
        super.onResume()
        NewsApp.getNavigatorBinder().setNavigator(this)
    }

    override fun onPause() {
        super.onPause()
        NewsApp.getNavigatorBinder().removeNavigator()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
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
