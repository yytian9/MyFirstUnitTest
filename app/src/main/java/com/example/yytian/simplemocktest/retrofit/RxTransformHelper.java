package com.example.yytian.simplemocktest.retrofit;


import com.google.gson.JsonObject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxTransformHelper {
    /**
     * 对结果进行预处理
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<BaseEntity, T> handleResult() {

        return new Observable.Transformer<BaseEntity, T>() {
            @Override
            public Observable<T> call(Observable<BaseEntity> tObservable) {
                return tObservable.flatMap(new Func1<BaseEntity, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseEntity response) {

                        if (response.ret==1) {
                            return createSuccessData(response.data);
                        } else if(response.ret==2){
                            return createErrorData(response.data);
                        }
                        return null;
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };

    }

    private static <T> Observable<T> createSuccessData(JsonObject data) {
        return null;
    }

    private static <T> Observable<T> createErrorData(JsonObject data) {
        return null;
    }

    /*private static <T> Observable<T> createSuccessData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

    }*/


}
