package top.coenocyte.order.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import top.coenocyte.cloud.common.AjaxResult;

import java.io.PrintWriter;

/**
 * @author 303
 * @description  自定义的 Sentinel 限流异常处理器
 * @create 2025-10-10 19:28
 */
@Component
public class MyBlockExceptionHandle implements BlockExceptionHandler {

    private final ObjectMapper objectMapper;

    public MyBlockExceptionHandle(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String s, BlockException e) throws Exception {
        // too many requests
        httpServletResponse.setStatus(429);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();

        AjaxResult ajaxResult = AjaxResult.error(500, s + " 被 Sentinel 限制了, 原因: " + e.getClass());
        String result = objectMapper.writeValueAsString(ajaxResult);

        writer.write(result);
        writer.flush();
        writer.close();

    }
}
