// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.intellij.sdk.language.psi.SplTypes.*;
import org.intellij.sdk.language.psi.*;

public class SplVarnameImpl extends SplNamedElementImpl implements SplVarname {

  public SplVarnameImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SplVisitor visitor) {
    visitor.visitVarname(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SplVisitor) accept((SplVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public SplProcdecl getProc() {
    return SplPsiImplUtil.getProc(this);
  }

  @Override
  public String getName() {
    return SplPsiImplUtil.getName(this);
  }

  @Override
  public PsiElement setName(String newName) {
    return SplPsiImplUtil.setName(this, newName);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return SplPsiImplUtil.getNameIdentifier(this);
  }

}
