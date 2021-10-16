// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.language;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import org.intellij.sdk.language.psi.SplProcname;
import org.intellij.sdk.language.psi.SplTypename;
import org.intellij.sdk.language.psi.SplVarname;
import org.intellij.sdk.language.psi.impl.SplPsiImplUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SplReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

    private final String key;

    public SplReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        SplPsiImplUtil.setName(myElement, newElementName);
        return myElement;
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        Class type;
        switch (myElement.getClass().getTypeName()){
            case "org.intellij.sdk.language.psi.impl.SplTypeusageImpl":
                type = SplTypename.class;
                break;
            case "org.intellij.sdk.language.psi.impl.SplProcusageImpl":
                type = SplProcname.class;
                break;
            default:
                type = SplVarname.class;
                break;

        }
        final List<PsiNamedElement> declarations = SplUtil.findDecl(project, myElement, type);
        List<ResolveResult> results = new ArrayList<>();
        for (PsiNamedElement declaration : declarations) {
            results.add(new PsiElementResolveResult(declaration));
        }
        return results.toArray(new ResolveResult[results.size()]);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        Project project = myElement.getProject();
        Class type;
        switch (myElement.getClass().getTypeName()){
            case "org.intellij.sdk.language.psi.impl.SplTypeusageImpl":
                type = SplTypename.class;
                break;
            case "org.intellij.sdk.language.psi.impl.SplProcusageImpl":
                type = SplProcname.class;
                break;
            default:
                type = SplVarname.class;
                break;

        }
        List<PsiNamedElement> properties = SplUtil.findDecl(project, type);
        List<LookupElement> variants = new ArrayList<>();
        for (final PsiNamedElement elementName : properties) {
            if (elementName.getName() != null && elementName.getName().length() > 0) {
                variants.add(LookupElementBuilder
                        .create(elementName).withIcon(SplIcons.FILE)
                        .withTypeText(elementName.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }

}
