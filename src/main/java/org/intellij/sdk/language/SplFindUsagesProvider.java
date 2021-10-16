package org.intellij.sdk.language;

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.TokenSet;
import org.intellij.sdk.language.psi.SplProcname;
import org.intellij.sdk.language.psi.SplTypename;
import org.intellij.sdk.language.psi.SplVarname;
import org.intellij.sdk.language.psi.SplTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SplFindUsagesProvider implements FindUsagesProvider {

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        return psiElement instanceof PsiNamedElement;
    }

    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement psiElement) {
        return null;
    }

    @NotNull
    @Override
    public String getType(@NotNull PsiElement element) {
        if (element instanceof SplVarname) {
            return "SPL Variable";
        } else if (element instanceof SplProcname){
            return "SPL Prozedur";
        } else if (element instanceof SplTypename){
            return "SPL Typ";
        } else {
            return "";
        }
    }

    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
        if (element instanceof SplVarname) {
            return ((SplVarname) element).getName();
        } else if (element instanceof SplProcname){
            return ((SplProcname) element).getName();
        } else if (element instanceof SplTypename){
            return ((SplTypename) element).getName();
        } else {
            return "";
        }
    }

    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
            return "";
    }

}
