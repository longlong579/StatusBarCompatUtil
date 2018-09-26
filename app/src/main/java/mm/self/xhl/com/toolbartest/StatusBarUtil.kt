package mm.self.xhl.com.toolbartest

import android.annotation.TargetApi
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.support.v4.view.ViewCompat
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout

/**
 * @author xhl
 * @desc 2018/9/25 14:21
 */
object StatuBarUtil {

    private val TAG_FAKE_STATUS_BAR_VIEW = "TAG_FAKE_STATUS_BAR_VIEW"
    private val TAG_MARGIN_ADDED = "TAG_MARGIN_ADDED"

    fun setStatusBarColor(activity: Activity, statusColor: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setStatusBarColorUpLoop(activity, statusColor)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setStatusBarColorUpKik(activity, statusColor)
        }
    }


    //大于5.0时 设置状态栏颜色
    @TargetApi(21)
    private fun setStatusBarColorUpLoop(activity: Activity, statusColor: Int) {
        val window = activity.window
        //取消状态栏透明
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //设置状态栏颜色
        window.statusBarColor = statusColor
        //设置系统状态栏处于可见状态
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        //让view不根据系统窗口来调整自己的布局
        val mContentView = window.findViewById<ViewGroup>(Window.ID_ANDROID_CONTENT)
        val mChildView = mContentView.getChildAt(0)
        if (mChildView != null) {
            mChildView.fitsSystemWindows = false
            //ViewCompat.setFitsSystemWindows(mChildView, false)
            ViewCompat.requestApplyInsets(mChildView)
        }
    }


    //大于4.4-5.0时设置toolBar颜色 不需要在xml中用 fitSystem
    @TargetApi(19)
    private fun setStatusBarColorUpKik(activity: Activity, statusColor: Int) {
        val window = activity.window
        //设置Window为透明
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        val mContentView = window.findViewById<View>(Window.ID_ANDROID_CONTENT) as ViewGroup
        //获取父布局
        val mContentChild = mContentView.getChildAt(0)
        //获取状态栏高度
        val statusBarHeight = getStatusBarHeight(activity)

        //如果已经存在假状态栏则移除，防止重复添加
        removeFakeStatusBarViewIfExist(activity)
        //添加一个View来作为状态栏的填充
        addFakeStatusBarView(activity, statusColor, statusBarHeight)
        //设置子控件到状态栏的间距
        addMarginTopToContentChild(mContentChild, statusBarHeight)
        //不预留系统栏位置
        if (mContentChild != null) {
            ViewCompat.setFitsSystemWindows(mContentChild, false)
        }
        //如果在Activity中使用了ActionBar则需要再将布局与状态栏的高度跳高一个ActionBar的高度，否则内容会被ActionBar遮挡
        val action_bar_id = activity.resources.getIdentifier("action_bar", "id", activity.packageName)
        val view = activity.findViewById<View>(action_bar_id)
        if (view != null) {
            val typedValue = TypedValue()
            if (activity.theme.resolveAttribute(R.attr.actionBarSize, typedValue, true)) {
                val actionBarHeight = TypedValue.complexToDimensionPixelSize(typedValue.data, activity.resources.displayMetrics)
                setContentTopPadding(activity, actionBarHeight)
            }
        }
    }

    private fun removeFakeStatusBarViewIfExist(activity: Activity) {
        val window = activity.window
        val mDecorView = window.decorView as ViewGroup

        val fakeView = mDecorView.findViewWithTag<View>(TAG_FAKE_STATUS_BAR_VIEW)
        if (fakeView != null) {
            mDecorView.removeView(fakeView)
        }
    }

    private fun addFakeStatusBarView(activity: Activity, statusBarColor: Int, statusBarHeight: Int): View {
        val window = activity.window
        val mDecorView = window.decorView as ViewGroup

        val mStatusBarView = View(activity)
        val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight)
        layoutParams.gravity = Gravity.TOP
        mStatusBarView.layoutParams = layoutParams
        mStatusBarView.setBackgroundColor(statusBarColor)
        mStatusBarView.tag = TAG_FAKE_STATUS_BAR_VIEW

        mDecorView.addView(mStatusBarView)
        return mStatusBarView
    }

    private fun addMarginTopToContentChild(mContentChild: View?, statusBarHeight: Int) {
        if (mContentChild == null) {
            return
        }
        if (TAG_MARGIN_ADDED != mContentChild.tag) {
            val lp = mContentChild.layoutParams as FrameLayout.LayoutParams
            lp.topMargin += statusBarHeight
            mContentChild.layoutParams = lp
            mContentChild.tag = TAG_MARGIN_ADDED
        }
    }

    private fun removeMarginTopOfContentChild(mContentChild: View?, statusBarHeight: Int) {
        if (mContentChild == null) {
            return
        }
        if (TAG_MARGIN_ADDED == mContentChild.tag) {
            val lp = mContentChild.layoutParams as FrameLayout.LayoutParams
            lp.topMargin += -statusBarHeight
            mContentChild.layoutParams = lp
            mContentChild.tag=null
        }

    }

    private fun setContentTopPadding(activity: Activity, padding: Int) {
        val mContentView = activity.window.findViewById<View>(Window.ID_ANDROID_CONTENT) as ViewGroup
        mContentView.setPadding(0, padding, 0, 0)
    }

    fun getStatusBarHeight(activity: Activity): Int {
        var result = 0
        val resourceId = activity.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = activity.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }


    /**
     * 状态栏透明
     */
    public fun translucentStatusBar(activity: Activity,hideStatusBarBackground: Boolean)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            translucentStatusBarUpLoop(activity,hideStatusBarBackground)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            translucentStatusBarUpKit(activity)
        }
    }

    @TargetApi(19)
    private fun translucentStatusBarUpKit(activity: Activity) {
        val window = activity.window
        //设置Window为透明
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        val mContentView = activity.findViewById<View>(Window.ID_ANDROID_CONTENT) as ViewGroup
        val mContentChild = mContentView.getChildAt(0)

        //移除已经存在假状态栏则,并且取消它的Margin间距
        removeFakeStatusBarViewIfExist(activity)
        removeMarginTopOfContentChild(mContentChild, getStatusBarHeight(activity))
        if (mContentChild != null) {
            //fitsSystemWindow 为 false, 不预留系统栏位置.
            ViewCompat.setFitsSystemWindows(mContentChild, false)
        }
    }

    @TargetApi(21)
    private fun translucentStatusBarUpLoop(activity: Activity, hideStatusBarBackground: Boolean) {
        val window = activity.window
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (hideStatusBarBackground) {
            //如果为全透明模式，取消设置Window半透明的Flag
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            //设置状态栏为透明
            window.statusBarColor = Color.TRANSPARENT
            //设置window的状态栏不可见
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        } else {
            //如果为半透明模式，添加设置Window半透明的Flag
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            //设置系统状态栏处于可见状态
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
        //view不根据系统窗口来调整自己的布局
        val mContentView = window.findViewById<View>(Window.ID_ANDROID_CONTENT) as ViewGroup
        val mChildView = mContentView.getChildAt(0)
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false)
            ViewCompat.requestApplyInsets(mChildView)
        }
    }
}
