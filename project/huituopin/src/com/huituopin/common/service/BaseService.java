package com.huituopin.common.service;

import java.util.List;

import javax.persistence.Entity;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import com.huituopin.common.exception.ParameterException;

@Entity
public class BaseService {
    protected Validator validator;

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    protected Boolean validateObject(Object o) throws ParameterException {

        List<ConstraintViolation> violations = validator.validate(o);
        if (violations != null && !violations.isEmpty()) {
            ParameterException parameterException = new ParameterException();
            for (ConstraintViolation violation : violations) {
                String ovalField = violation.getContext().toString();
                String fieldNameTemp = ovalField.substring(0, ovalField.lastIndexOf("."));
                Integer index = fieldNameTemp.lastIndexOf(".");
                String fieldName = ovalField.substring(index + 1, ovalField.length());
                String message = violation.getMessage();
                parameterException.addErrorParameter(fieldName, message);
            }
            if (parameterException.hasErrorParameter()) {
                throw parameterException;
            }
        }
        return Boolean.TRUE;
    }
}
