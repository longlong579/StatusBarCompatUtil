# StatusBarCompatUtil
适配4.4以上状态栏的颜色 透明状态 隐藏显示 黑色字体 Fragment中使用  无需退出自动切换不同的状态


  
![image](https://github.com/longlong579/StatusBarCompatUtil/blob/master/1.gif)/
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
  
  

