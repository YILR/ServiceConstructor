package com.project.json.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RefUtil {

    public static <T> List<T> mapRefVal(String val){
        List list = null;
        if(Objects.nonNull(val)){
            String[] vals = val.trim().split(" ");
            if(vals.length > 1){
                list = Arrays.stream(vals).map(v -> {
                    if(isBoolean(v))
                        return Boolean.parseBoolean(v);
                    return v;
                }).collect(Collectors.toList());
            }else {
               if(isBoolean(val)){
                   list = new ArrayList<Boolean>();
                   list.add(Boolean.parseBoolean(val));
               }else {
                   list = new ArrayList<String>();
                   list.add(val);
               }
            }
        }
        return list;
    }

    private static boolean isBoolean(String val){
        return val.equalsIgnoreCase("true") || val.equalsIgnoreCase("false");
    }
}
