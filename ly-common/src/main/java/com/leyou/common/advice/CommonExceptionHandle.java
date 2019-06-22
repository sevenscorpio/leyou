package com.leyou.common.advice;

import com.leyou.common.exception.LyException;
import com.leyou.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.common.advice
 * @ClassName: CommonExceptionHandle
 * @Author:
 * @Description:
 * @Date: 2019-04-18 7:03
 * @Version: 1.0
 */

@ControllerAdvice
public class CommonExceptionHandle {

    @ExceptionHandler(LyException.class)
    public ResponseEntity<ExceptionResult> handleException(LyException e){

        return ResponseEntity.status(e.getExceptionEnum().getCode())
                .body(new ExceptionResult(e.getExceptionEnum()));
    }

}
