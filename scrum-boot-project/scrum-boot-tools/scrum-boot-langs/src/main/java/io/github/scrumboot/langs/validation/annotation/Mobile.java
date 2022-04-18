package io.github.scrumboot.langs.validation.annotation;

import io.github.scrumboot.langs.validation.MobileValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 *
 * @author Bing D. Yee
 * @date 2020/08/16
 */
@Documented
@Constraint(validatedBy = MobileValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Mobile {

    String message() default "Invalid Phone Number!";

    Class[] groups() default {};

    Class[] payload() default {};

}
