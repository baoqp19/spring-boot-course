package vn.hoidanit.jobhunter.util;

import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.hoidanit.jobhunter.domain.respone.RestResponse;
import vn.hoidanit.jobhunter.util.annotation.ApiMessage;

// khi controller nào được gọi thì phải đi qua cái này trước 
@ControllerAdvice
public class FormatResResponse implements ResponseBodyAdvice<Object> {

    // nó hỗ trợ tất cả các kiểu giá trị trả về,
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        int status = servletResponse.getStatus();

        RestResponse<Object> res = new RestResponse<Object>();
        res.setStatusCode(status);

        // nếu body là string hoặc đối tượng Resource thì in ra body
        if (body instanceof Resource || body instanceof String) {
            return body;
        }

        // case error https://localhost8080/v3/api-docs chạy sẽ không bị lỗi và hiển thị
        // đầy đủ thông tin backend
        String path = request.getURI().getPath();
        if (path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui")) {
            return body;
        }

        // body là dữ liệu trả về từ controller API

        if (status >= 400) {
            return body;
        } else {
            // get message in ApiMessage trong từng api
            ApiMessage message = returnType.getMethodAnnotation(ApiMessage.class);
            res.setMessage(message != null ? message.value() : "CALL API SUCCESS");
            res.setData(body);
        }
        return res;
    }

}
