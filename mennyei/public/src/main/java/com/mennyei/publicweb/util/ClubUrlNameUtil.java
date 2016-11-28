package com.mennyei.publicweb.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * Created by lejsz on 2016. 11. 28..
 */
public class ClubUrlNameUtil {

    public static String convertClubNameToUniqUrlFrendly(String clubFullName) {
        String firstWord = StringUtils.substringBefore(clubFullName, " ");
        String restWords = StringUtils.removeStart(clubFullName, firstWord);
        String[] nameSegments = StringUtils.split(restWords, " ");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(capitalizeAndLowerCase(firstWord));
        Arrays.stream(nameSegments).forEach(nameSegment -> stringBuffer.append(capitalizeAndLowerCase(nameSegment).charAt(0)));
        return stringBuffer.toString();
    }

    private static String capitalizeAndLowerCase(String input) {
        return StringUtils.stripAccents(StringUtils.lowerCase(input));
    }

}
