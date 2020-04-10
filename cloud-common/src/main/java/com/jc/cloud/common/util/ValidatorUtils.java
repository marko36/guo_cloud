package com.jc.cloud.common.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 * <p>
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
    * @throws Exception 校验不通过，则报RRException异常
     */
    public static void validateEntity(Object object)
            throws Exception {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, Default.class);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder("[");
            for (ConstraintViolation<Object> constraint : constraintViolations) {
                msg.append(constraint.getMessage()).append(",");
            }
          StringBuilder msg1 = new StringBuilder(msg.substring(0,msg.length()-1));
            msg1.append("]");
            throw new ValidationException(msg1.toString());
        }
    }
}