package com.sp.core.backend;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

public class ClubUrlNameUtil {

    public static String convertClubNameToUniqUrlFrendly(String fullName) {
        String firstWord = StringUtils.substringBefore(fullName, " ");
        String restWords = StringUtils.removeStart(fullName, firstWord);
        String[] nameSegments = StringUtils.split(restWords, " ");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(capitalizeAndLowerCase(firstWord));
        Arrays.stream(nameSegments).forEach(nameSegment -> stringBuffer.append(capitalizeAndLowerCase(nameSegment).charAt(0)));
        return stringBuffer.toString();
    }

    private static String capitalizeAndLowerCase(String input) {
//        return StringUtils.stripAccents(StringUtils.lowerCase(input));
        return "";
    }

}
