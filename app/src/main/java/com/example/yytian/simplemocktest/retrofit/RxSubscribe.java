package com.example.yytian.simplemocktest.retrofit;

import rx.Subscriber;

public abstract class RxSubscribe<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
    }
    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onNext(T t) {
        _onNext(t);
    }
    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (false) { //这里自行替换判断网络的代码
            _onError("网络不可用");
        } else if (e instanceof ServerException) {
            _onError(e.getMessage());
        } else {
            _onError("请求失败，请稍后再试...");
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

}
