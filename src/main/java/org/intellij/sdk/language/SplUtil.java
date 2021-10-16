package org.intellij.sdk.language;

import com.google.common.collect.Lists;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import org.intellij.sdk.language.psi.*;
import org.intellij.sdk.language.psi.impl.SplVarnameImpl;
import org.intellij.sdk.language.psi.impl.SplVarusageImpl;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SplUtil {

    public static List<PsiNamedElement> findDecl(Project project, PsiElement usage, Class type) {
        String name = usage.getText();
        List<PsiNamedElement> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(SplFileType.INSTANCE, GlobalSearchScope.allScope(project));
        if (type == SplVarname.class) {
            SplVarusageImpl varusage = (SplVarusageImpl) usage;
            ArrayList<PsiNamedElement> elements = extractFromFile(varusage.getProc(), new ArrayList(), type);

            for (PsiNamedElement element : elements) {
                if (name.equals(element.getName())) {
                    result.add(element);
                }
            }
        } else {
            for (VirtualFile virtualFile : virtualFiles) {
                SplFile splFile = (SplFile) PsiManager.getInstance(project).findFile(virtualFile);
                if (splFile != null) {
                    ArrayList<PsiNamedElement> elements = extractFromFile(splFile, new ArrayList(), type);

                    for (PsiNamedElement element : elements) {
                        if (name.equals(element.getName())) {
                            result.add(element);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static List<PsiElement> findUsage(Project project, PsiElement decl, Class type) {
        String name = decl.getText();
        List<PsiElement> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(SplFileType.INSTANCE, GlobalSearchScope.allScope(project));
        if (type == SplVarname.class) {
            SplVarnameImpl vardecl = (SplVarnameImpl) decl;
            ArrayList<PsiNamedElement> elements = extractFromFile(vardecl.getProc(), new ArrayList(), type);

            for (PsiNamedElement element : elements) {
                if (name.equals(element.getName())) {
                    result.add(element);
                }
            }
        } else {
            for (VirtualFile virtualFile : virtualFiles) {
                SplFile splFile = (SplFile) PsiManager.getInstance(project).findFile(virtualFile);
                if (splFile != null) {
                    ArrayList<PsiElement> elements;

                    elements = extractFromFile(splFile, new ArrayList<PsiElement>(), type);

                    for (PsiElement element : elements) {
                        if (Objects.equals(name, element.getText())) {
                            result.add(element);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static List<PsiNamedElement> findDecl(Project project, Class type) {
        List<PsiNamedElement> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(SplFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            SplFile splFile = (SplFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (splFile != null) {
                ArrayList<PsiNamedElement> vars = extractFromFile(splFile, new ArrayList(), type);
                result.addAll(vars);
            }
        }
        return result;
    }

    private static ArrayList<PsiElement> extractFromFile(PsiElement element, ArrayList<PsiElement> elements, Class type) {
        PsiElement[] children = PsiTreeUtil.getChildrenOfType(element, type);
        if (children != null) {
            elements.addAll(Arrays.asList(children));
        }
        for (PsiElement child : element.getChildren()) {
            extractFromFile(child, elements, type);
        }
        return elements;

    }

//    private static ArrayList<SplVarname> extractVarDeclsFromFile(SplFile splFile) {
//        ArrayList<SplVarname> vars = new ArrayList<>();
//
//        for (PsiElement decl : Objects.requireNonNull(PsiTreeUtil.getChildrenOfType(splFile, SplDecl.class))) {
//            if (decl != null) {
//                PsiElement[] vardecllists = PsiTreeUtil.getChildrenOfType(decl, SplVardecllist.class);
//                if (vardecllists != null) {
//                    for (PsiElement vardecllist : vardecllists) {
//                        if (vardecllist != null) {
//                            SplVardecl[] vardecls = PsiTreeUtil.getChildrenOfType(vardecllist, SplVardecl.class);
//                            if (vardecls != null) {
//                                for (SplVardecl vardecl : vardecls) {
//                                    SplVarname[] varnames = PsiTreeUtil.getChildrenOfType(vardecl, SplVarname.class);
//                                    if (varnames != null) {
//                                        vars.addAll(Arrays.asList(varnames));
//                                    }
//                                }
//                            }
//
//                        }
//                    }
//                }
//
//            }
//        }
//        return vars;
//    }

    /**
     * Attempts to collect any comment elements above the Spl key/value pair.
     */
    public static @NotNull String findDocumentationComment(SplVarname var) {
        List<String> result = new LinkedList<>();
        PsiElement element = var.getPrevSibling();
        while (element instanceof PsiComment || element instanceof PsiWhiteSpace) {
            if (element instanceof PsiComment) {
                String commentText = element.getText().replaceFirst("[!# ]+", "");
                result.add(commentText);
            }
            element = element.getPrevSibling();
        }
        return StringUtil.join(Lists.reverse(result), "\n ");
    }

}
