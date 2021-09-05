package com.yomursin.scrum.web.validation;

import com.yomursin.scrum.constant.RegexConstant;
import com.yomursin.scrum.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Email;
import java.util.regex.Pattern;

/**
 *
 * @author Bing D. Yee
 * @date 2020/08/16
 */
public class EmailValidator  implements ConstraintValidator<Email, String> {

    private static final Pattern PATTERN = Pattern.compile(RegexConstant.EMAIL_REG);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.isBlank(value) ? Boolean.FALSE :
                PATTERN.matcher(value).matches();
    }

}
