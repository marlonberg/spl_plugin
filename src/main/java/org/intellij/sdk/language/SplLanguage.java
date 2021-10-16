package org.intellij.sdk.language;

import com.intellij.lang.Language;

public class SplLanguage extends Language {

    public static final SplLanguage INSTANCE = new SplLanguage();

    private SplLanguage() {
        super("SPL");
    }
}
