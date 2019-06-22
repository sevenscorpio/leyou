package com.leyou.common.vo;

import com.leyou.common.enums.ExceptionEnum;
import lombok.Data;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.common.vo
 * @ClassName: ExceptionResult
 * @Author:
 * @Description:
 * @Date: 2019-04-18 7:20
 * @Version: 1.0
 */

@Data
public class ExceptionResult {

    private int status;
    private String message;
    private Long timestamp;

    public ExceptionResult(ExceptionEnum em) {

        this.status = em.getCode();
        this.message = em.getMessage();
        this.timestamp = System.currentTimeMillis();
    }
}
