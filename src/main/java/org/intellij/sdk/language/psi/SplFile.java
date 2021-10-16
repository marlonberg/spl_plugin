package org.intellij.sdk.language.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.intellij.sdk.language.SplFileType;
import org.intellij.sdk.language.SplLanguage;
import org.jetbrains.annotations.NotNull;

public class SplFile extends PsiFileBase {

    public SplFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, SplLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return SplFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Spl File";
    }
}
