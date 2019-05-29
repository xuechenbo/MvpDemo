package com.kahuanbao.com.v.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import com.kahuanbao.com.R;
import com.kahuanbao.com.utils.BottomNavigationViewHelper;
import com.kahuanbao.com.v.fragment.MainFragment;
import com.kahuanbao.com.v.fragment.MineFragment;
import com.kahuanbao.com.v.fragment.ProjectFragment;
import com.kahuanbao.com.v.fragment.SystemFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * Created by Administrator on 2019/3/30.
 *
 */

public class StartMainActivity extends FragmentActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.bottom)
    BottomNavigationView mBottomNavigationView;
    private MainFragment mainFragment;
    private ProjectFragment projectFragment;
    private SystemFragment systemFragment;
    private MineFragment mineFragment;
    private Fragment[] fragments;
    private int lastShowFragment = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plamain);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        //mBottomNavigationView 条目数多于三个 不平均分布解决
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        initFragments();
    }

    private void initFragments() {
        mainFragment = new MainFragment();
        projectFragment = new ProjectFragment();
        systemFragment = new SystemFragment();
        mineFragment = new MineFragment();
        fragments = new Fragment[]{mainFragment, projectFragment, systemFragment, mineFragment};
        lastShowFragment = 0;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, mainFragment)
                .show(mainFragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bottom_main:
                if (lastShowFragment != 0) {
                    switchFrament(lastShowFragment, 0);
                    lastShowFragment = 0;
                }
                return true;
            case R.id.bottom_project:
                if (lastShowFragment != 1) {
                    switchFrament(lastShowFragment, 1);
                    lastShowFragment = 1;
                }
                return true;
            case R.id.bottom_system:
                if (lastShowFragment != 2) {
                    switchFrament(lastShowFragment, 2);
                    lastShowFragment = 2;
                }
                return true;
            case R.id.bottom_mine:
                if (lastShowFragment != 3) {
                    switchFrament(lastShowFragment, 3);
                    lastShowFragment = 3;
                }
                return true;
        }
        return false;
    }
    /**
     * 切换Fragment
     *
     * @param lastIndex 上个显示Fragment的索引
     * @param index     需要显示的Fragment的索引
     */
    public void switchFrament(int lastIndex, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastIndex]);
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.fragment_container, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

}
