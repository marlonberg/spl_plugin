package org.intellij.sdk.language.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import org.intellij.sdk.language.psi.*;
import org.jetbrains.annotations.NotNull;

public class SplPsiImplUtil {
    public static PsiReference @NotNull [] getReferences(PsiElement element){
        return com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry.getReferencesFromProviders(element);
    }

    public static SplProcdecl getProc(PsiElement var) {
        PsiElement parent = var.getParent();
        while (!(parent instanceof SplProcdecl)) {
            parent = parent.getParent();
        }
        return (SplProcdecl) parent;
    }


    public static String getName(PsiNamedElement element) {
        ASTNode keyNode = element.getNode().findChildByType(SplTypes.IDENT);
        if (keyNode != null) {
            return keyNode.getText();
        } else {
            return null;
        }
    }

    public static PsiElement setName(PsiElement element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(SplTypes.IDENT);
        if (keyNode != null) {
            if (element instanceof SplVarname || element instanceof SplVarusage) {
                SplVarname var = SplElementFactory.createVar(element.getProject(), newName);
                ASTNode newKeyNode = var.getFirstChild().getNode();
                element.getNode().replaceChild(keyNode, newKeyNode);
            } else if (element instanceof SplProcname || element instanceof SplProcusage){
                SplProcname proc = SplElementFactory.createProc(element.getProject(), newName);
                ASTNode newKeyNode = proc.getFirstChild().getNode();
                element.getNode().replaceChild(keyNode, newKeyNode);
            } else if (element instanceof SplTypename || element instanceof SplTypeusage){
                SplTypename type = SplElementFactory.createType(element.getProject(), newName);
                ASTNode newKeyNode = type.getFirstChild().getNode();
                element.getNode().replaceChild(keyNode, newKeyNode);
            }

        }
        return element;
    }

    public static PsiElement getNameIdentifier(PsiNamedElement element) {
        ASTNode keyNode = element.getNode().findChildByType(SplTypes.IDENT);
        if (keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }

}
