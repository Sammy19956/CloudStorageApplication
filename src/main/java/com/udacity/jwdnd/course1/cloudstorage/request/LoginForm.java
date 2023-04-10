package com.udacity.jwdnd.course1.cloudstorage.request;

public class LoginForm {

    private static Boolean signupSuccess;

    // Getter and Setter methods
    public static Boolean getSignupSuccess() {
        return signupSuccess;
    }

    public static void setSignupSuccess(Boolean signupSuccess) {
        LoginForm.signupSuccess = signupSuccess;
    }
}
