/*
 * Copyright 2017 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mm.self.xhl.com.toolbartest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.xhl.statusbarcompatutil.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

import mm.self.xhl.com.toolbartest.R;

/**
 * Created by YanZhenjie on 2017/12/13.
 */
public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentA());
        fragmentList.add(new FragmentB());

        Adapter adapter = new Adapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        StatusBarCompat.translucentStatusBar(this,true);//状态栏透明 无阴影 Fragment的状态栏颜色由自身设置（添加StatusView，否则滑动切换状态栏只有一种状态）
        //StatusBarCompat.setStatusBarDarkFont(this,true);//设置此Activity的状态栏字体为黑色（因为状态栏是系统的，Activity中所有的Fragment实际上是共用一个状态栏的）
    }

    private static class Adapter extends FragmentPagerAdapter {

        private List<Fragment> mFragmentList;

        public Adapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            mFragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentList.get(position).getClass().getSimpleName();
        }
    }

}