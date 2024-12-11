package com.franc.global.util;

import com.franc.global.error.BizException;
import com.franc.global.error.ErrorCode;

import java.util.Objects;

public class FrancUtil<T> {

    /**
     * Null인지?
     * @param t
     * @return
     * @param <T>
     */
    public static <T> boolean isNull(T t) {
        if(Objects.isNull(t)) return true;
        return false;
    }

    /**
     * Null이 아닌지?
     * @param t
     * @return
     * @param <T>
     */
    public static <T> boolean isNotNull(T t) {
        if(Objects.isNull(t)) return false;
        return true;
    }

    /**
     * Null 체크 (Null인 경우 해당하는 errorCode로 예외발생)
     * @param t
     * @param errorCode
     * @return
     * @param <T>
     */
    public static <T> T isNullThrowBizException(T t, ErrorCode errorCode) throws BizException {
        if(isNotNull(t)) return t;

        throw new BizException(errorCode);
    }
}
