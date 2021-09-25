package com.rechaox.scrum.log.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bing D. Yee
 * @since 2021/09/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MethodExecuteResult {

    private boolean success;

    private Throwable throwable;

    private String errorMsg;

}