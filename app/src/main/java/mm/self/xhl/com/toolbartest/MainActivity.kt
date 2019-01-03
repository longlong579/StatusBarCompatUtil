package mm.self.xhl.com.toolbartest

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.xhl.statusbarcompatutil.StatusBarCompat
import kotlinx.android.synthetic.main.activity_main.*
import mm.self.xhl.com.toolbartest.fragment.FragmentActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toorBar)


        txt.setOnClickListener {
            StatusBarCompat.clearOffsetView(this,toorBar)//如果用了setTranslucentForImageView，在同一界面中切换不同状态时勿忘还原margain padding
            StatusBarCompat.translucentStatusBar(this,true)
            StatusBarCompat.setStatusBarDarkFont(this,true)//黑色字体  注意放后面
        }
        txt2.setOnClickListener {
            StatusBarCompat.setStatusBarColor(this,resources.getColor(R.color.colorAccent))
            StatusBarCompat.clearOffsetView(this,toorBar)
            StatusBarCompat.setStatusBarDarkFont(this,false)//白色字体
        }
        txt3.setOnClickListener {
            StatusBarCompat.clearOffsetView(this,toorBar)
            StatusBarCompat.setOffsetPaddingView(this,toorBar)
            StatusBarCompat.translucentStatusBar(this)
            //StatusBarCompat.setTranslucentForImageView(this,true,0,toorBar)
            StatusBarCompat.setStatusBarDarkFont(this,true)//黑色字体
        }

        txt4.setOnClickListener {
            startActivity(Intent(this,FragmentActivity::class.java))
        }
        txt5.setOnClickListener {
            startActivity(Intent(this,DrawerActivity::class.java))
        }
    }

}
