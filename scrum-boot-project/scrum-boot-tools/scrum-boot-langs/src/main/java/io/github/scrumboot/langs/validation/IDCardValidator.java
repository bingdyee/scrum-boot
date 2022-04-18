package io.github.scrumboot.langs.validation;

import com.google.common.base.Strings;
import io.github.scrumboot.langs.constant.RegexConstant;
import io.github.scrumboot.langs.validation.annotation.IDCard;

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

    private static final Pattern PATTERN = Pattern.compile(RegexConstant.ID_CARD_REG);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Strings.isNullOrEmpty(value) ? Boolean.FALSE :
                PATTERN.matcher(value).matches();
    }

}

