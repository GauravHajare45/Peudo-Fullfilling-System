package com.pfs.mobilenumbervalidation.Service;

import org.springframework.stereotype.Service;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service
public class MobileNumberService {
    private static final String PHONE_NUMBER_PATTERN = "^\\+\\d{2}[0-9]{10}$";
    private final Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);

    public boolean isMobileNumberValid(String mobileNumber) {
        Matcher matcher = pattern.matcher(mobileNumber);
        return matcher.matches();
    }
}
