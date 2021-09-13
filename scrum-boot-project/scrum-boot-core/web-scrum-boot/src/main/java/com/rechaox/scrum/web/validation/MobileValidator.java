package com.rechaox.scrum.web.validation;

import com.rechaox.scrum.web.validation.annotation.Mobile;
import com.rechaox.scrum.constant.RegexConstant;
import com.rechaox.scrum.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 *
 *
 * @author Bing D. Yee
 * @date 2020/08/16
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {

    private static final Pattern PATTERN = Pattern.compile(RegexConstant.MOBILE_REG);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isBlank(value) ? Boolean.FALSE :
                PATTERN.matcher(value).matches();
    }

}
