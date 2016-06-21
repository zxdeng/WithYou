package com.poison.withyou.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Description：
 * Created by poison on 2016/6/21 0021.
 */
public abstract class BaseActivity extends AppCompatActivity{

    protected SweetAlertDialog mDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDialog!=null){
            mDialog.dismiss();
        }
    }

    /**
     * 获取布局文件id
     * @return 布局文件id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     *  加载dialog
     * @param msg 提示信息
     */
    protected void showLoadDialog(String msg){
        mDialog = null;
        mDialog = new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE);
        mDialog.setTitleText(msg)
                .setCancelable(false);
        mDialog.show();
    }

    /**
     * 显示加载情况
     * @param msg 提示信息
     * @param isSuccess 加载成功与否
     */
    protected void hiddenLoadDialog(String msg,boolean isSuccess){
        if (mDialog != null){
            mDialog.setTitleText(msg)
                    .setConfirmText("确定");
            if (isSuccess){
                mDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
            }else {
                mDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
            }
        }

    }

    /**
     * 显示一般的对话框消息,无回调
     * @param msg 显示的消息
     */
    protected void showDialog(String msg){
        mDialog = null;
        mDialog = new SweetAlertDialog(this);
        mDialog.setTitleText(msg);
        mDialog.setConfirmText("确定");
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.show();
    }

    protected void showCutomDialog(String title, String msg, final SweetAlertDialog.OnSweetClickListener confirmListenr){
        mDialog = null;
        mDialog = new SweetAlertDialog(this);
        mDialog.setTitleText(title)
                .setContentText(msg)
                .setConfirmText("")
                .setCancelText("")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mDialog.dismiss();
                        mDialog.cancel();
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        confirmListenr.onClick(sweetAlertDialog);
                    }
                })
                .show();
    }



}
