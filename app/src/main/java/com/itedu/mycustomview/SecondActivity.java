package com.itedu.mycustomview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.itedu.mycustomview.demo.MyObservable;
import com.itedu.mycustomview.fragment.ItsFragment;
import com.itedu.mycustomview.fragment.MyFragment;
import com.itedu.mycustomview.fragment.OtherFragment;
import com.itedu.mycustomview.fragment.YouFragment;
import com.itedu.mycustomview.weight.FragmentTabHost;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity {

    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(android.R.id.tabs)
    TabWidget tabs;
    @BindView(android.R.id.tabhost)
    FragmentTabHost tabhost;

    @BindString(R.string.my)
    String mMy;
    @BindString(R.string.you)
    String mYou;
    @BindString(R.string.other)
    String mOther;
    @BindString(R.string.its)
    String mIts;

    @BindDrawable(R.drawable.my_selector)
    Drawable myDrawable;
    @BindDrawable(R.drawable.you_selector)
    Drawable youDrawable;
    @BindDrawable(R.drawable.other_selector)
    Drawable otherDrawable;
    @BindDrawable(R.drawable.its_selector)
    Drawable itsDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        tabhost.setup(SecondActivity.this, getSupportFragmentManager(), android.R.id.tabcontent);
        tabhost.getTabWidget().setDividerDrawable(null);//去掉分割线

        for (int i = 0; i < 4; i++) {
            TabHost.TabSpec tabSpec;
            switch (i) {
                case 0:
                    tabSpec = tabhost.newTabSpec(mMy)
                            .setIndicator(getTabView(mMy, myDrawable));
                    tabhost.addTab(tabSpec,MyFragment.class,null);
                    break;
                case 1:
                    tabSpec = tabhost.newTabSpec(mYou)
                            .setIndicator(getTabView(mYou, youDrawable));
                    tabhost.addTab(tabSpec,YouFragment.class,null);
                    break;
                case 2:
                    tabSpec = tabhost.newTabSpec(mOther)
                            .setIndicator(getTabView(mOther, otherDrawable));
                    tabhost.addTab(tabSpec,OtherFragment.class,null);
                    break;
                case 3:
                    tabSpec = tabhost.newTabSpec(mIts)
                            .setIndicator(getTabView(mIts, itsDrawable));
                    tabhost.addTab(tabSpec,ItsFragment.class,null);
                    break;
            }
        }


    }

    private View getTabView(String name, Drawable drawable) {
        View view = LinearLayout.inflate(getApplicationContext(), R.layout.tab_view, null);
        ImageView tabDrawable = view.findViewById(R.id.tab_drawable);
        TextView tabName = view.findViewById(R.id.tab_name);
        tabDrawable.setImageDrawable(drawable);
        tabName.setText(name);
        return view;
    }
}
