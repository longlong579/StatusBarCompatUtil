# StatusBarCompatUtil
适配4.4以上状态栏的颜色 透明状态 隐藏显示 黑色字体 Fragment中使用  无需退出自动切换不同的状态


## 截图 
<image src="./1.gif" width="270"> 
	
* 一：状态栏设置颜色导航栏
* 二：状态栏和导航栏用图片作为背景。
* 三：内容侵入状态栏，比如产品详情页。
* 四：状态栏底色白色，字体深色（大概是灰黑色）。
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
  
  ##注意：设置黑色/白色字体的代码放后面  具体用法看代码

