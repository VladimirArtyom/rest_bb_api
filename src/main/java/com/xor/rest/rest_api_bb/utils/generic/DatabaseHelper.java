package com.xor.rest.rest_api_bb.utils.generic;

import jakarta.persistence.Query;

import java.util.List;

public class DatabaseHelper {

    public static <T extends Query> T set_parameters(T typedQuery, List<String> listOfKeys, List<Object> listOfValues) {

        for (int i = 0; i < listOfKeys.size(); i++) {
            Object value = listOfValues.get(i);
            String key = listOfKeys.get(i);
            typedQuery.setParameter(key, value);
        }
        return typedQuery;
    }
}
