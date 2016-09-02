package com.example.yytian.simplemocktest.retrofit;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import rx.Observable;
import rx.Subscriber;
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
    public static <T> Observable.Transformer<BaseEntity, T> handleResult(Class<T> tClass) {

        return new Observable.Transformer<BaseEntity, T>() {
            @Override
            public Observable<T> call(Observable<BaseEntity> tObservable) {
                return tObservable.flatMap(new Func1<BaseEntity, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseEntity response) {
                        String data = response.data.toString();
                        Gson gson = new Gson();
                        if (response.ret==1) {
                            T dataEntity = gson.fromJson(data, new TypeToken<T>() {
                            }.getType());
                            return createSuccessData(dataEntity);
                        } else if(response.ret==2){
                            ErrorEntity errorEntity = gson.fromJson(data, ErrorEntity.class);
                            return createErrorData(errorEntity);
                        }
                        return null;
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };

    }

    /*private static <T> Observable<T> createSuccessData(JsonObject data) {
        return null;
    }*/

    private static  Observable createErrorData(ErrorEntity errorEntity) {
        return Observable.create(new Observable.OnSubscribe() {
            @Override
            public void call(Object o) {

            }
        });
    }

    private static <T> Observable<T> createSuccessData(final T data) {
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

    }


}
