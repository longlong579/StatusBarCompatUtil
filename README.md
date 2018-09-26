# StatusBarCompatUtil
Kotlin编写 适配4.4以上状态栏的颜色 透明状态 隐藏显示 黑色字体








#依赖
Step 1. Add the JitPack repository to your build file /
Add it in your root build.gradle at the end of repositories: /
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  Step 2. Add the dependency /
  dependencies {
	       implementation ('com.github.longlong579:StatusBarCompatUtil:v1.0.0', { exclude group: 'com.android.support' })
	}
  
