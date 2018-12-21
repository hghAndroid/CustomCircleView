package com.itedu.mycustomview.bean;

import android.os.Build;

/*
 *
 * 项目名:MyCustomView
 * 包名:com.itedu.mycustomview.bean
 * 创建时间:2018/12/714:37
 * 描述:
 *
 */public class ComputerBean {
    private int mCpu;
    private int mRam;
    private String mBrand;

    public ComputerBean() {

    }

    public void setmCpu(int mCpu) {
        this.mCpu = mCpu;
    }

    public void setmRam(int mRam) {
        this.mRam = mRam;
    }

    public void setmBrand(String mBrand) {
        this.mBrand = mBrand;
    }

    @Override
    public String toString() {
        return "ComputerBean{" +
                "mCpu=" + mCpu +
                ", mRam=" + mRam +
                ", mBrand='" + mBrand + '\'' +
                '}';
    }
}
