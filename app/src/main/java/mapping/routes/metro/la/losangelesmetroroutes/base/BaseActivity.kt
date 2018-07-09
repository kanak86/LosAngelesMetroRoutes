package mapping.routes.metro.la.losangelesmetroroutes.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

/**
 * Created by apoorvakanaksiwach on 2/8/18.
 */

abstract class BaseActivity : AppCompatActivity() {
    companion object {

        fun addFragmentToActivity(fragmentManager: FragmentManager,
                                  fragment: Fragment,
                                  frameId: Int,
                                  tag: String) {

            val transaction = fragmentManager.beginTransaction()
            transaction.replace(frameId, fragment, tag)
            transaction.commit()
        }
    }
}
