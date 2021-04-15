//package com.learn.global;
//
//import com.aoyang.common.result.Result;
//import com.aoyang.shop.common.GlobalException;
//import com.aoyang.shop.common.GlobalExceptionCode;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
///**
// * @description: 全局返回值统一包装
// * @author:
// * @createDate: 2021/4/14
// */
//@Slf4j
//@RestControllerAdvice(basePackages = "com.aoyang.shop")
//public class ResponseConfig implements ResponseBodyAdvice<Object> {
//
//    /**
//     * 哪些返回值类型需要进行处理
//     *
//     * @param methodParameter
//     * @param aClass
//     * @return
//     */
//    @Override
//    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
//
//        /*
//        不能够包装字符串类型，否则会因为转换器不匹配导致字符串转换异常
//        若必须返回单个字符串，需要使用 new Result("");
//         */
//        final String returnTypeName = methodParameter.getParameterType().getName();  //这里是接收到的返回值的类型
//
//
//        if (Result.class.getName().equals(returnTypeName)
//                || "java.lang.String".equals(returnTypeName)) {
//            return Boolean.FALSE;
//        }
//
//        return Boolean.TRUE;
//    }
//
//    /**
//     * 在返回之前
//     *
//     * @param o
//     * @param methodParameter
//     * @param mediaType
//     * @param aClass
//     * @param serverHttpRequest
//     * @param serverHttpResponse
//     * @return
//     */
//    @Override
//    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
//
//        /*
//        若返回值类型为 void 则默认为成功
//         */
//        final String returnTypeName = methodParameter.getParameterType().getName();  //这里是接收到的返回值的类型
//        log.info("返回值类型:[{}]", returnTypeName);
//        /*
//        若返回值不是 json 类型则直接返回
//        否则会出现类型转换异常
//        接口不能直接返回字符串类型，否则默认的转换器无法进行解析并报错，若必须返回单个字符串，需要使用 new GlobalResponseWrapper().data(""); , 在前面的判断会直接将包装器返回
//         */
//        if (!mediaType.includes(MediaType.APPLICATION_JSON)) {
//            return o;
//        }
//
//        if (GlobalExceptionCode.class.getName().equals(returnTypeName)) {
//            Result result = new Result();
//            result.setCode(((GlobalExceptionCode) o).getCode());
//            result.setMessage(((GlobalExceptionCode) o).getMsg());
//            return new Result();
//        }
//
//        Result result = new Result();
//        result.setCode(GlobalExceptionCode.SUCCESS.getCode());
//        result.setMessage(GlobalExceptionCode.SUCCESS.getMsg());
//        result.setData(o);
//        return result;
//    }
//
//    /**
//     * 对异常的处理
//     *
//     * @param e
//     * @param req
//     * @return
//     */
//    @ExceptionHandler(Exception.class)
//    public Result handleException(Exception e, HttpServletRequest req) {
//        Result result = new Result();
//        //业务异常
//        if (e instanceof GlobalException) {
//            //全局GlobalException异常拦截处理
//            result.setCode(((GlobalException) e).getCode().getCode());
//            result.setMessage(((GlobalException) e).getCode().getMsg());
//        } else if (e instanceof MethodArgumentNotValidException) {
//            //@Valid校验请求参数抛出的异常
//            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
//            List<ObjectError> allErrors = bindingResult.getAllErrors();
//            StringBuffer errorMsg = new StringBuffer("");
//            if (allErrors != null && allErrors.size() > 0) {
//                for (ObjectError allError : allErrors) {
//                    if (StringUtils.isEmpty(allError.toString())) {
//                        errorMsg.append(allError.getDefaultMessage());
//                    } else {
//                        errorMsg.append("," + allError.getDefaultMessage());
//                    }
//                }
//            }
//            result.setCode(GlobalExceptionCode.REQUEST_PARAM_ERROR.getCode());
//            result.setMessage(errorMsg.toString());
//        } else if (e instanceof Exception) {//系统异常
//            //其他异常
//            result.setCode(GlobalExceptionCode.ERROR.getCode());
//            result.setMessage(e.toString());
//        } else {
//            result.setCode(GlobalExceptionCode.ERROR.getCode());
//            result.setMessage(GlobalExceptionCode.ERROR.getMsg());
//        }
//        return result;
//
//    }
//}
