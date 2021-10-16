package org.intellij.sdk.language.psi;

import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.SplLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class SplElementType extends IElementType {

    public SplElementType(@NotNull @NonNls String debugName) {
        super(debugName, SplLanguage.INSTANCE);
    }

}
