package br.fatec.FileLibrary;

import java.io.*;

public class FileLibrary implements I_FileLibrary
{
    String defaultPath;
    String defaultFile;
    String defaultFilePath;
    String sysSeparator;

    public FileLibrary(String srcFilePath)
    {
        this.sysSeparator = File.separator;
        this.defaultFilePath = srcFilePath;
        this.defaultPath = getPath(srcFilePath);
        this.defaultFile = getFile(srcFilePath);
    }

    public String getContentFile() throws Exception
    {
        File file = openExistentFile(defaultFilePath);
        FileReader read = new FileReader(file);
        BufferedReader buffer = new BufferedReader(read);

        String line;
        StringBuilder content = new StringBuilder();

        line = buffer.readLine();
        while (line != null)
        {
            content.append(line).append("\n");
            line = buffer.readLine();
        }

        return content.toString();
    }

    private File openExistentFile(String filePath) throws Exception
    {
        File file = new File(filePath);

        if (!file.isFile() || !file.exists())
            throw new Exception("An error encountered in openExistentFile\n" +
                    ":File no exist:\n" + "File: " + filePath + " no exists or not found");
        return file;
    }

    public void writeInFile(String destinyFilePath, String content) throws Exception
    {
        String fileName, pathName;
        pathName = getPath(destinyFilePath);
        fileName = getFile(destinyFilePath);

        File directory = new File(pathName);
        if (!directory.isDirectory() || !directory.exists())
            throw new Exception("An error encountered in WriteInFile\n:Directory no exists:\n" +
                    "Directory: " + pathName + " no exists or not found");

        File file = new File(pathName, fileName);
        FileWriter write = new FileWriter(file);
        PrintWriter fileWriter = new PrintWriter(write);

        fileWriter.write(content);
        fileWriter.flush();
        fileWriter.close();
        write.close();
    }

    private String getPath(String fullPath)
    {
        String[] allPaths = fullPath.split(sysSeparator);
        StringBuilder newPath = new StringBuilder();
        int lastPathIndex = allPaths.length - 1;

        for (int i = 0; i < lastPathIndex; i++)
            newPath.append(allPaths[i]).append(sysSeparator);
        return newPath.toString();
    }

    private String getFile(String fullPath)
    {
        String[] allPaths = fullPath.split(sysSeparator);
        int fileIndex = allPaths.length - 1;
        return allPaths[fileIndex];
    }
}
