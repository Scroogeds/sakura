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
public class ExportEntity {

    private String className;

    private String operate;

    private Object data;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
