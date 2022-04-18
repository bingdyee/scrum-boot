package io.github.scrumboot.langs.validation.annotation;


import io.github.scrumboot.langs.validation.IDCardValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 *
 *
 * @author Bing D. Yee
 * @date 2020/08/16
 */
@Documented
@Constraint(validatedBy = IDCardValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface IDCard {

    String message() default "Invalid ID Card!";

    Class[] groups() default {};

    Class[] payload() default {};

}
