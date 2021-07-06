package org.intellij.sdk.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SPLFileType extends LanguageFileType {

    public static final SPLFileType INSTANCE = new SPLFileType();

    private SPLFileType() {
        super(SPLLanguage.INSTANCE);
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

    @NotNull
    @Override
    public  Icon getIcon(){
        return SPLIcons.FILE;
    }
}
