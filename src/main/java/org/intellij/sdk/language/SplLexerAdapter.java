package org.intellij.sdk.language;

import com.intellij.lexer.FlexAdapter;

public class SplLexerAdapter extends FlexAdapter{

    public  SplLexerAdapter() {
        super(new SplLexer(null));
    }
}
