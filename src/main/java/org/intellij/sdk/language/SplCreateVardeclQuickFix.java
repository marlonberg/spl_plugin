package org.intellij.sdk.language;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.IncorrectOperationException;
import org.intellij.sdk.language.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

class SplCreateVardeclQuickFix extends BaseIntentionAction {

    private final SplVarusage key;

    SplCreateVardeclQuickFix(SplVarusage key) {
        this.key = key;
    }

    @NotNull
    @Override
    public String getText() {
        return "Create Variable Declaration '" + key.getText() + "'";
    }

    @NotNull
    @Override
    public String getFamilyName() {
        return "Create Variable Declaration";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        return true;
    }

    @Override
    public void invoke(@NotNull final Project project, final Editor editor, PsiFile file) throws
            IncorrectOperationException {
        ApplicationManager.getApplication().invokeLater(() -> {
            Collection<VirtualFile> virtualFiles =
                    FileTypeIndex.getFiles(SplFileType.INSTANCE, GlobalSearchScope.allScope(project));
            if (virtualFiles.size() == 1) {
                createVardecl(project, virtualFiles.iterator().next());
            } else {
                final FileChooserDescriptor descriptor =
                        FileChooserDescriptorFactory.createSingleFileDescriptor(SplFileType.INSTANCE);
                descriptor.setRoots(ProjectUtil.guessProjectDir(project));
                final VirtualFile file1 = FileChooser.chooseFile(descriptor, project, null);
                if (file1 != null) {
                    createVardecl(project, file1);
                }
            }
        });
    }

    private void createVardecl(final Project project, final VirtualFile file) {
        WriteCommandAction.writeCommandAction(project).run(() -> {
            SplProcdecl proc = key.getProc();
            SplVardecl vardecl = SplElementFactory.createVardecl(project, key.getText());
            proc.getNode().addChild(SplElementFactory.createCRLF(project).getNode(), proc.getNode().findChildByType(SplTypes.LCURL).getTreeNext());
            proc.getNode().addChild(SplElementFactory.createWhitespace(project).getNode(), proc.getNode().findChildByType(SplTypes.LCURL).getTreeNext().getTreeNext());
            proc.getNode().addChild(SplElementFactory.createWhitespace(project).getNode(), proc.getNode().findChildByType(SplTypes.LCURL).getTreeNext().getTreeNext());
            proc.getNode().addChild(SplElementFactory.createVardecl(project, key.getText()).getNode(), proc.getNode().findChildByType(SplTypes.LCURL).getTreeNext().getTreeNext().getTreeNext().getTreeNext());
            ((Navigatable) vardecl.getLastChild().getNavigationElement()).navigate(true);
            FileEditorManager.getInstance(project).getSelectedTextEditor().getCaretModel().moveCaretRelatively(2, 0, false, false, false);
        });
    }

}
