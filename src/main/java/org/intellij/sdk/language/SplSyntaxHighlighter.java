package org.intellij.sdk.language;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.psi.SplTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class SplSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey OPERATOR =
            createTextAttributesKey("SPL_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey SEMIC =
            createTextAttributesKey("SPL_SEMIC", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey KEY =
            createTextAttributesKey("SPL_KEY", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey INTLIT =
            createTextAttributesKey("SPL_INTLIT", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("SPL_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("SPL_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);


    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] OPERATOR_KEYS = new TextAttributesKey[]{OPERATOR};
    private static final TextAttributesKey[] SEMIC_KEYS = new TextAttributesKey[]{SEMIC};
    private static final TextAttributesKey[] KEY_KEYS = new TextAttributesKey[]{KEY};
    private static final TextAttributesKey[] INTLIT_KEYS = new TextAttributesKey[]{INTLIT};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new SplLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(SplTypes.ASGN)
                || tokenType.equals(SplTypes.COLON)
                || tokenType.equals(SplTypes.PLUS)
                || tokenType.equals(SplTypes.MINUS)
                || tokenType.equals(SplTypes.SLASH)
                || tokenType.equals(SplTypes.STAR)
                || tokenType.equals(SplTypes.GT)
                || tokenType.equals(SplTypes.GE)
                || tokenType.equals(SplTypes.LT)
                || tokenType.equals(SplTypes.LE)
                || tokenType.equals(SplTypes.EQ)
                || tokenType.equals(SplTypes.UMINUS)) {
            return OPERATOR_KEYS;
        } else if (
                tokenType.equals(SplTypes.COMMA)
                        || tokenType.equals(SplTypes.SEMIC)
        ) {
            return SEMIC_KEYS;
        } else if (

                tokenType.equals(SplTypes.INTLIT)
        ) {
            return INTLIT_KEYS;
        } else if (tokenType.equals(SplTypes.TYPE)
                || tokenType.equals(SplTypes.ARRAY)
                || tokenType.equals(SplTypes.IF)
                || tokenType.equals(SplTypes.OF)
                || tokenType.equals(SplTypes.ELSE)
                || tokenType.equals(SplTypes.PROC)
                || tokenType.equals(SplTypes.REF)
                || tokenType.equals(SplTypes.VAR)
                || tokenType.equals(SplTypes.WHILE)) {
            return KEY_KEYS;
        } else if (tokenType.equals(SplTypes.COMMENT)) {
            return COMMENT_KEYS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }

}
