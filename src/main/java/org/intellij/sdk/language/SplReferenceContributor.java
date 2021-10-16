package org.intellij.sdk.language;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.intellij.sdk.language.psi.SplProcusage;
import org.intellij.sdk.language.psi.SplTypes;
import org.intellij.sdk.language.psi.SplTypeusage;
import org.intellij.sdk.language.psi.SplVarusage;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SplReferenceContributor extends PsiReferenceContributor {

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(SplVarusage.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                                 @NotNull ProcessingContext context) {

                        SplVarusage varusage = (SplVarusage) element;
                        PsiElement value = Objects.requireNonNull(varusage.getNode().findChildByType(SplTypes.IDENT)).getPsi();


                        TextRange property = new TextRange(0,
                                value.getText().length());

                        return new PsiReference[]{new SplReference(varusage, property)};

                    }
                });
        
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(SplProcusage.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                                 @NotNull ProcessingContext context) {

                        SplProcusage procusage = (SplProcusage) element;
                        PsiElement value = Objects.requireNonNull(procusage.getNode().findChildByType(SplTypes.IDENT)).getPsi();


                        TextRange property = new TextRange(0,
                                value.getText().length());

                        return new PsiReference[]{new SplReference(procusage, property)};

                    }
                });
        
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(SplTypeusage.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                                 @NotNull ProcessingContext context) {

                        SplTypeusage typeusage = (SplTypeusage) element;
                        PsiElement value = Objects.requireNonNull(typeusage.getNode().findChildByType(SplTypes.IDENT)).getPsi();


                        TextRange property = new TextRange(0,
                                value.getText().length());

                        return new PsiReference[]{new SplReference(typeusage, property)};

                    }
                });
    }

}
