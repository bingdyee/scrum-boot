package com.rechaox.scrum.web.validation.annotation;

import com.rechaox.scrum.web.validation.UsernameValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * @author Bing D. Yee
 * @since 2021/09/05
 */
@Documented
@Constraint(validatedBy = UsernameValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Username {

    String message() default "Invalid username!";

    Class[] groups() default {};

    Class[] payload() default {};

}
