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
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.IncorrectOperationException;
import org.intellij.sdk.language.psi.SplProcdecl;
import org.intellij.sdk.language.psi.SplProcname;
import org.intellij.sdk.language.psi.SplVardecl;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

class SplRemoveProcdeclQuickFix extends BaseIntentionAction {

    private final SplProcname key;

    SplRemoveProcdeclQuickFix(SplProcname key) {
        this.key = key;
    }

    @NotNull
    @Override
    public String getText() {
        return "Remove unused procedure declaration '" + key.getText() + "'";
    }

    @NotNull
    @Override
    public String getFamilyName() {
        return "Remove unused procedure declaration";
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
                removeProcdecl(project, virtualFiles.iterator().next());
            } else {
                final FileChooserDescriptor descriptor =
                        FileChooserDescriptorFactory.createSingleFileDescriptor(SplFileType.INSTANCE);
                descriptor.setRoots(ProjectUtil.guessProjectDir(project));
                final VirtualFile file1 = FileChooser.chooseFile(descriptor, project, null);
                if (file1 != null) {
                    removeProcdecl(project, file1);
                }
            }
        });
    }

    private void removeProcdecl(final Project project, final VirtualFile file) {
        WriteCommandAction.writeCommandAction(project).run(() -> {
            SplProcdecl procdecl = (SplProcdecl) key.getParent();
            procdecl.getParent().getNode().removeChild(procdecl.getNode());
            FileEditorManager.getInstance(project).getSelectedTextEditor().getCaretModel().moveCaretRelatively(2, 0, false, false, false);
        });
    }

}
