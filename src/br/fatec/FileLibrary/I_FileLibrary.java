package br.fatec.FileLibrary;

import java.io.File;

public interface I_FileLibrary
{
    public String getContentFile() throws Exception;
    public void writeInFile(String filePath, String content) throws Exception;
}
