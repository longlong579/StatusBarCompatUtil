package mm.self.xhl.com.toolbartest

import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import android.os.Build.VERSION_CODES.LOLLIPOP
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.view.View.SYSTEM_UI_FLAG_VISIBLE
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toorBar)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            val window  = getWindow()
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        }
        StatuBarUtil.setStatusBarColor(this, resources.getColor(R.color.colorAccent))

        txt.setOnClickListener {
            StatuBarUtil.translucentStatusBar(this,true)
        }
        txt2.setOnClickListener {
            StatuBarUtil.setStatusBarColor(this,resources.getColor(R.color.colorAccent))
        }
    }


}
