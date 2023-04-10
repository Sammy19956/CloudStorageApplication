package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.request.LoginForm;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private Boolean signupSuccess;

    // Getter and Setter methods
    public Boolean getSignupSuccess() {
        return LoginForm.getSignupSuccess();
    }

    public void setSignupSuccess(Boolean signupSuccess) {
        LoginForm.setSignupSuccess(signupSuccess);
    }
}
