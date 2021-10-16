// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.language;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.PsiElement;
import org.intellij.sdk.language.psi.*;
import org.jetbrains.annotations.NotNull;
import org.intellij.sdk.language.SplAnnotator;

import java.util.*;

public class SplLineMarkerProvider extends RelatedItemLineMarkerProvider {

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result) {
        if (!(element.getParent() instanceof SplProcusage)) {
            return;
        }

        // The literal expression must start with the Spl language literal expression
        SplProcusage procusage = (SplProcusage) element.getParent();
        PsiElement value = Objects.requireNonNull(procusage.getNode().findChildByType(SplTypes.IDENT)).getPsi();
        if ((value == null || !SplAnnotator.bibProcs.containsKey(value.getText()))) {
            return;
        }

        // Add the property to a collection of line marker info
        NavigationGutterIconBuilder<PsiElement> builder =
                NavigationGutterIconBuilder.create(SplIcons.FILE)
                        .setTargets(value)
                        .setTooltipText(SplAnnotator.bibProcs.get(value.getText()));
        result.add(builder.createLineMarkerInfo(element));

    }

}
