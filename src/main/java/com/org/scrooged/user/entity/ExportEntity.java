package com.org.scrooged.user.entity;

/**
 * <p>Title: ExportEntity</p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai oneself 2019</p>
 *
 * @author luyangqian
 * version 1.0
 * <pre>History
 *          2019-08-27   luyangqian  Created
 * </pre>
 */
public class ExportEntity<T> {

    private String operate;

    private T t;

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
