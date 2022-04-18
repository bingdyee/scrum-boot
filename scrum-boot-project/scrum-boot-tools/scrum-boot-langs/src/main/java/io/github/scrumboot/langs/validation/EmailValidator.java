package io.github.scrumboot.langs.validation;

import com.google.common.base.Strings;
import io.github.scrumboot.langs.constant.RegexConstant;

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
        return Strings.isNullOrEmpty(value) ? Boolean.FALSE :
                PATTERN.matcher(value).matches();
    }

}
