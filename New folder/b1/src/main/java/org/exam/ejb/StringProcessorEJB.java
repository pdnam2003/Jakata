package org.exam.ejb;

import jakarta.ejb.Stateless;
import java.util.Locale;

@Stateless
public class StringProcessorEJB {

    public String processName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            return "Xin chào, vui lòng cung cấp tham số name trong URL!";
        }

        String upperName = fullName.toUpperCase(Locale.ROOT).trim();
        int characterCount = countNonSpaceCharacters(upperName);
        return String.format("Xin chào %s, tên của bạn có %d ký tự!", upperName, characterCount);
    }

    private int countNonSpaceCharacters(String value) {
        return (int) value.chars()
                .filter(ch -> ch != ' ')
                .count();
    }
}
