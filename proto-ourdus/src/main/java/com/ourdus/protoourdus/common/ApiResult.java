package com.ourdus.protoourdus.common;

import org.springframework.http.HttpStatus;

public class ApiResult<T> {

    private final boolean success;

    private final T response;

    private final ApiError apiError;

    private ApiResult(boolean success, T response, ApiError apiError) {
        this.success = success;
        this.response = response;
        this.apiError = apiError;
    }

    public static <T> ApiResult<T> OK(T response){ //parameter와 return이 동일해서 <T> ApiResult<T>
        return new ApiResult<>(true, response, null);
    }

    public static ApiResult<?> ERROR(String errorMessage, HttpStatus status){ //Gerneric<?> 사용
        return new ApiResult<>(false, null, new ApiError(errorMessage, status));
    }

    public boolean isSuccess() {
        return success;
    }

    public T getResponse() {
        return response;
    }

    public ApiError getApiError() {
        return apiError;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "success=" + success +
                ", response=" + response +
                ", apiError=" + apiError +
                '}';
    }
}
