package org.intellij.sdk.language;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.intellij.sdk.language.psi.SplTypes;
import org.jetbrains.annotations.NotNull;

public class SplCompletionContributor extends CompletionContributor {

    public SplCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(SplTypes.ARR),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create(SplTypes.ARRAY));
                    }
                }
        );
    }

}
