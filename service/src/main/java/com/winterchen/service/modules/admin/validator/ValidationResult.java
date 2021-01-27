package com.winterchen.service.modules.admin.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hongjun500
 * @date 2021/1/27 14:34
 * @tool ThinkPadX1隐士
 * Created with 2019.3.2.IntelliJ IDEA
 * Description: 数据校验结果
 */
public class ValidationResult {
    /**
     * 是否有错误
     */
    private Boolean hasError = false;

    /**
     * 错误信息
     */
    private Map<String, String> errorMsgMap = new HashMap<>();

    public Boolean getHasError() {
        return hasError;
    }

    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }

    /**
     * 通用的通过格式化字符串信息获取错误结果的msg方法
     * @return errorMsg
     */
    public String getErrorMsg(){
        return StringUtils.join(errorMsgMap.values().toArray(),",");
    }
}
