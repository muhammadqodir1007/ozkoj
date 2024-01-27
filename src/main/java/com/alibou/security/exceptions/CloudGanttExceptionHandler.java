//package com.alibou.security.exceptions;
//
//
//import com.alibou.security.payload.ApiResult;
//import io.jsonwebtoken.JwtException;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class CloudGanttExceptionHandler {
//
//
//    @ExceptionHandler()
//    public ResponseEntity<ApiResult<ErrorData>> handleException(JwtException ex) {
//        ex.printStackTrace();
//
//        return null;
//
//    }
//
//    @ExceptionHandler(value = RestException.class)
//    public ResponseEntity<ApiResult<ErrorData>> handleException(RestException ex) {
//        ex.printStackTrace();
//
//        return new ResponseEntity<>(
//                ApiResult.errorResponse(ex.getMessage(), ex.getStatus().value()),
//                ex.getStatus());
//    }
//
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiResult<ErrorData>> handleException(MethodArgumentNotValidException ex) {
//        List<ErrorData> errors = new ArrayList<>();
//        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
//            errors.add(
//                    new ErrorData(
//                            fieldError.getDefaultMessage(),
//                            HttpStatus.BAD_REQUEST.value()
//                    )
//            );
//        });
//        return new ResponseEntity<>(ApiResult.errorResponse(errors), HttpStatus.BAD_REQUEST);
//    }
//}
