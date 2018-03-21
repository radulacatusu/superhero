package com.payworks.superhero.utils;

import com.payworks.superhero.api.model.SuperheroDTO;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@Component
public class URLHelper {

    public String superheroUrlHelper(SuperheroDTO dto, HttpServletRequest request) {
        StringBuilder resourcePath = new StringBuilder();

        resourcePath.append(request.getRequestURL());
        resourcePath.append("/");
        resourcePath.append(dto.getName());

        return resourcePath.toString();
    }
}
