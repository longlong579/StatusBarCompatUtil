package mm.self.xhl.com.toolbartest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xhl.statusbarcompatutil.StatusBarCompat;

import mm.self.xhl.com.toolbartest.R;


/**
 * Test for DrawerLayout and CoordinatorLayout
 * 抽屉高度全屏 内容用StatusView占位 布局设置fitSystemWindow
 */
public class DrawerFragment2 extends Fragment {

    public DrawerFragment2() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drawer_t, container, false);
    }

    DrawerLayout drawer;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        ViewGroup contentView = (ViewGroup) getActivity().findViewById(android.R.id.content);
        ((AppCompatActivity) getActivity()).setSupportActionBar((Toolbar) view.findViewById(R.id.toolbar));
        drawer=view.findViewById(R.id.drawer);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         StatusBarCompat.translucentStatusBar(getActivity(), false);//全屏透明 注意设置布局的fitSystemWindow 和添加StatusView
        //StatusBarCompat.setColorForDrawerLayout(getActivity(),drawer, getResources().getColor(R.color.colorAccent), 123);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
//            StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getContext(), R.color.colorAccent));
        }
    }
}
