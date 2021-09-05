package com.yomursin.scrum.web.validation;

import com.yomursin.scrum.constant.RegexConstant;
import com.yomursin.scrum.util.StringUtils;
import com.yomursin.scrum.web.validation.annotation.Mobile;
import com.yomursin.scrum.web.validation.annotation.Username;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author Bing D. Yee
 * @since 2021/09/05
 */
public class UsernameValidator implements ConstraintValidator<Username, String> {

    private static final Pattern PATTERN = Pattern.compile(RegexConstant.USERNAME_REG);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isBlank(value) ? Boolean.FALSE :
                PATTERN.matcher(value).matches();
    }

}
