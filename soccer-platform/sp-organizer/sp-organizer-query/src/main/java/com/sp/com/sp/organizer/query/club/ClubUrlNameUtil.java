package com.sp.organizer.backend.club;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by lejsz on 2016. 11. 28..
 */
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
        return StringUtils.stripAccents(StringUtils.lowerCase(input));
    }

}
