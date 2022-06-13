package com.zhaohu.design.currentlimiting.utils;

import org.apache.commons.lang3.StringUtils;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @creator : zhaohu
 * @date : 6/13/2022
 * @description :
 */
public class URLUtils {
    public static List<String> tokenizeUrlLimit(String url) {
        if (url == null || url.length() == 0)
            return Collections.emptyList();

        String[] paths = url.split("/");
        List<String> urlList = new ArrayList<>();
        if (paths.length == 0)
            throw new InvalidParameterException("error url formant: " + url);
        for (String path : paths) {
            if ((path.contains(".") || path.contains("|") || path.contains("*")) &&
                    (!path.startsWith("(") || !path.startsWith(")"))) {
                throw new InvalidParameterException("error url formant: " + url);
            }

            if(StringUtils.isNotEmpty(path))
                urlList.add(path);
        }

        return urlList;
    }
}
