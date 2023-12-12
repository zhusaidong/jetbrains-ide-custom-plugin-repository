package cn.zhusaidong.idea.plugins.repositories.api.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zhusaidong
 * @date 2022/8/26
 */
public class HttpUtil {
    public static void copyHeaders(HttpServletResponse response, ResponseEntity<?> responseEntity) {
        HttpHeaders headers = responseEntity.getHeaders();
        for (String key : headers.keySet()) {
            List<String> list = headers.get(key);
            if (list != null && !list.isEmpty()) {
                response.setHeader(key, list.get(0));
            }
        }
    }

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        if (servletRequestAttributes == null) {
            return null;
        }
        return servletRequestAttributes.getRequest();
    }

    public static HttpServletResponse getResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        if (servletRequestAttributes == null) {
            return null;
        }
        return servletRequestAttributes.getResponse();
    }
}
