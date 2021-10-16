// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.intellij.sdk.language.psi.SplNamedElement;
import org.jetbrains.annotations.NotNull;

public abstract class SplNamedElementImpl extends ASTWrapperPsiElement implements SplNamedElement {

    public SplNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }

}
