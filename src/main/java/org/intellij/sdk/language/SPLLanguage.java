package org.intellij.sdk.language;

import com.intellij.lang.Language;

public class SPLLanguage extends Language {

    public static final SPLLanguage INSTANCE = new SPLLanguage();

    private SPLLanguage() {
        super("SPL");
    }
}
