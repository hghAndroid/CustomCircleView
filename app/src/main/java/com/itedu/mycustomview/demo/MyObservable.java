package com.itedu.mycustomview.demo;

/*
 *
 * 项目名:MyCustomView
 * 包名:com.itedu.mycustomview.demo
 * 创建时间:2018/11/2716:31
 * 描述:
 *
 */public class MyObservable<T> {

    MyOnSubcribe<T> myOnSubcribe;

    public static <T> MyObservable<T> create(MyOnSubcribe<T> myOnSubcribe) {
        MyObservable<T> tMyObservable = new MyObservable<T>();
        tMyObservable.myOnSubcribe = myOnSubcribe;
        return tMyObservable;
    }

    public void setMyOnSubcribe(MyObserver<T> observable) {
        myOnSubcribe.call(observable);
    }

    public interface MyOnSubcribe<T> {
        void call(MyObserver<T> observable);
    }

    public interface MyObserver<T> {
        void onNext(T t);

        void onComplete();
    }

}
