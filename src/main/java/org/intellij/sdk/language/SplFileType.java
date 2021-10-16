package org.intellij.sdk.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SplFileType extends LanguageFileType {

    public static final SplFileType INSTANCE = new SplFileType();

    private SplFileType() {
        super(SplLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public  String getName(){
        return "SPL File";
    }

    @NotNull
    @Override
    public  String getDescription(){
        return "SPL language file";
    }

    @NotNull
    @Override
    public  String getDefaultExtension(){
        return "spl";
    }

    @Nullable
    @Override
    public  Icon getIcon(){
        return SplIcons.FILE;
    }
}
