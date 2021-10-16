package org.intellij.sdk.language;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import org.intellij.sdk.language.psi.SplVardecl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SplRefactoringSupportProvider extends RefactoringSupportProvider {

    @Override
    public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement elementToRename, @Nullable PsiElement context) {
        return (elementToRename instanceof PsiElement);
    }

}
