# StatusBarCompatUtil
适配4.4以上状态栏的颜色 透明状态 隐藏显示 黑色字体 Fragment中使用  无需退出自动切换不同的状态


## 截图 
<image src="./1.gif" width="270"> 
	
* 一：状态栏设置颜色导航栏
* 二：状态栏和导航栏用图片作为背景。
* 三：内容侵入状态栏，比如产品详情页。
* 四：状态栏底色白色，字体深色。
* 五：和DrawerLayout结合使用。
* 六：和Fragment结合使用。
	
#依赖
Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```groovy
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  
  Step 2. Add the dependency 
  ```groovy
  dependencies 
  {
    implementation ('com.github.longlong579:StatusBarCompatUtil:v1.0.0', { exclude group: 'com.android.support' })
	}
	```
  
  ##用法
  
  *一：Activity中
  *1： StatusBarCompat.setStatusBarColor(this,resources.getColor(R.color.colorAccent))//设置颜色状态栏
  
  *2： StatusBarCompat.setStatusBarDarkFont(this,true)//黑色字体  注意放后面 白色字体为false
  
  *3：StatusBarCompat.translucentStatusBar(this,true)//沉浸式状态栏 状态栏会沉浸到顶部 true:5.0以上全透明 false：半透明
  
  *4： StatusBarCompat.setTranslucentForImageView(this,true,0,toorBar)//指定要偏移的View 偏移高度为状态栏高度
  
  *5：在同一个界面中切换不同状态时 注意：（如果用了*4的偏移 则勿忘还原）
    StatusBarCompat.clearOffsetMarginView(this,toorBar)//如果用了setTranslucentForImageView，在同一界面中切换不同状态时勿忘还原margain
    StatusBarCompat.translucentStatusBar(this,true)
    
   *二：Fragment中使用
   添加了StatusView 作为状态栏的偏移替代 见代码

