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

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xhl.statusbarcompatutil.StatusView;

import mm.self.xhl.com.toolbartest.R;

/**
 * Created by YanZhenjie on 2017/12/13.
 */
public class FragmentB extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b, container, false);
    }


    public final int DEFAULT_COLOR = Color.parseColor("#342526");
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        StatusView view1=view.findViewById(R.id.viewCompatStaBar);//Fragment状态栏的颜色由此设置
        view1.setBackgroundColor(DEFAULT_COLOR);
    }
    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public  int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}