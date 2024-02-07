package com.alibou.security.service;

import com.alibou.security.payload.ApiResult;
import com.alibou.security.payload.WebinarDto;
import com.alibou.security.payload.WebinarDtoResponse;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface WebinarService {

    List<WebinarDtoResponse> findAll();

    WebinarDtoResponse findById(Integer integer);


    ApiResult<String> create(WebinarDto entity) throws IOException;


    WebinarDtoResponse update(Integer integer, WebinarDto entity) throws IOException;

    void delete(Integer integer) throws IOException;

    WebinarDtoResponse updateUser(Integer webinarId, Integer userId);

    Set<String> listOfCities();

    Set<String> listOfFields();
}
