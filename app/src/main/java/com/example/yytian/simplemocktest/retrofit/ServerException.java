package com.example.yytian.simplemocktest.retrofit;

public class ServerException extends Exception {

    private static final int NO_DATA = 2;

    public ServerException(String msg){
        super(msg);
    }
    public ServerException(int resultCode){
        this(getApiExceptionMessage(resultCode));
    }

    /**
     * 转换错误数据
     *
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code) {
        String message = "";
        switch (code) {
            case NO_DATA:
                message = "参数错误";
                break;
            default:
                message = "error";
                break;

        }
        return message;
    }
}
