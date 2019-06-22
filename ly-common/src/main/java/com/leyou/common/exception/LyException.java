package com.leyou.common.exception;

import com.leyou.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.common.exception
 * @ClassName: LyException
 * @Author:
 * @Description:
 * @Date: 2019-04-18 7:12
 * @Version: 1.0
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LyException extends RuntimeException {

    private ExceptionEnum exceptionEnum;
}
