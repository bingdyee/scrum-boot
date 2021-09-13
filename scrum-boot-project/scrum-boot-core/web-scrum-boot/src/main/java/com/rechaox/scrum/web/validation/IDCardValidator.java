package com.rechaox.scrum.web.validation;

import com.rechaox.scrum.constant.RegexConstant;
import com.rechaox.scrum.util.StringUtils;
import com.rechaox.scrum.web.validation.annotation.IDCard;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 *
 *
 * @author Bing D. Yee
 * @date 2020/08/16
 */
public class IDCardValidator implements ConstraintValidator<IDCard, String> {

    private static final Pattern PATTERN = Pattern.compile(RegexConstant.ID_REG);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isBlank(value) ? Boolean.FALSE :
                PATTERN.matcher(value).matches();
    }

}

