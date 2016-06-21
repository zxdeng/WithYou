package com.poison.withyou.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.poison.loadpagerlibrary.LoadPager;

import java.util.List;

/**
 * Description：需要联网加载数据的页面基类，统一处理状态
 * Created by poison on 2016/6/21 0021.
 */
public abstract class BaseLoadDataFragment extends BaseFragment {
    private LoadPager mLoadPager = null;

    @Override
    protected void initData() {

    }

    @Override
    protected View initView(final LayoutInflater inflater, final ViewGroup container) {
        if (mLoadPager == null) {
            mLoadPager = new LoadPager(getActivity()) {
                @Override
                public View createSuccessView() {
                    return BaseLoadDataFragment.this.createSuccessView();
                }

                @Override
                protected void load() {
                    BaseLoadDataFragment.this.loadData();
                }
            };
        }else {
            //如果不为空，说明已经初始化了，就移除
            ViewParent parent = mLoadPager.getParent();
            if(parent instanceof ViewGroup){
                ViewGroup group=(ViewGroup) parent;
                group.removeView(mLoadPager);
            }
        }
        return mLoadPager;
    }

    protected abstract void loadData();

    protected abstract View createSuccessView();

    /**
     * 根据String类型的结果判断显示的页面
     *
     * @param resultData
     */
    protected void checkData(String resultData) {
        if (resultData == null) {
            showErrorView();
        } else if (resultData.isEmpty()) {
            mLoadPager.showPage(LoadPager.STATE_EMPTY);
        } else {
            mLoadPager.showPage(LoadPager.STATE_SUCCESS);
        }

    }

    /**
     * 显示错误页面
     */
    protected void showErrorView(){
        mLoadPager.showPage(LoadPager.STATE_ERROR);
    }

    /**
     * 根据List类型的结果判断显示的页面
     *
     * @param resultData 检查的数据源
     */
    protected void checkData(List resultData) {
        if (resultData == null) {
            showErrorView();
        } else if (resultData.size() == 0) {
            mLoadPager.showPage(LoadPager.STATE_EMPTY);
        } else {
            mLoadPager.showPage(LoadPager.STATE_SUCCESS);
        }
    }

    protected void show(){
        if (mLoadPager!= null){
            mLoadPager.show();
        }
    }
}
