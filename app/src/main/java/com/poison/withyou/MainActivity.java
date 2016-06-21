package com.poison.withyou;

import android.widget.TextView;
import android.widget.Toast;

import com.poison.withyou.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv)
    TextView mTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mTv.setText("测试ButterKnife");
    }

    @OnClick(R.id.tv)
    public void onClick() {
//        Toast.makeText(this, "测试ButterKnife", Toast.LENGTH_SHORT).show();
        showDialog("测试ButterKnife!");
    }
}
