package cn.mrain22.security.validate;

import org.springframework.security.core.AuthenticationException;

/**
 * @author mrain
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
