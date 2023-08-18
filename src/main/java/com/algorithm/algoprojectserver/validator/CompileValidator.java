package com.algorithm.algoprojectserver.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CompileValidator {

    private final int CODE_MAXLENGTH = 1000;

    private final String[] FILTER_TEXT = new String[]{
      "exit", "runtime", "download"
    };

    public boolean compileValid(String code) {

        if (code.length() > CODE_MAXLENGTH) {
            return false;
        }

        for (String containsText : FILTER_TEXT) {
            if (code.contains(containsText)) {
                return false;
            }
        }

        return true;
    }

}
