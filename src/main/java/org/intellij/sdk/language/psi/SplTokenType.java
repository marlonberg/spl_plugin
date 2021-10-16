package org.intellij.sdk.language.psi;

import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.SplLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class SplTokenType extends IElementType {

    public  SplTokenType(@NotNull @NonNls String debugName) {
        super(debugName, SplLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "SplTokenType." + super.toString();
    }

}
