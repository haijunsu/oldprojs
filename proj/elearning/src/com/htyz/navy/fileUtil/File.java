package com.htyz.navy.fileUtil;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;

public class File
{
    private UploadFiles uploadFilesParent;
    private int i_startData;
    private int i_endData;
    private int i_size;
    private String strFieldName;
    private String strFileName;
    private String strFileExt;
    private String strFilePathName;
    private String strContentType;
    private String strContentDisp;
    private String strTypeMime;
    private String strSubTypeMime;
    private String strContentString;
    private boolean isMissing;
    public static final int SAVEAS_AUTO = 0;
    public static final int SAVEAS_VIRTUAL = 1;
    public static final int SAVEAS_PHYSICAL = 2;

    File()
    {
        i_startData = 0;
        i_endData = 0;
        i_size = 0;
        strFieldName = new String();
        strFileName = new String();
        strFileExt = new String();
        strFilePathName = new String();
        strContentType = new String();
        strContentDisp = new String();
        strTypeMime = new String();
        strSubTypeMime = new String();
        strContentString = new String();
        isMissing = true;
    }

    public void saveAs(String destFilePathName)
        throws FileUtilException, IOException
    {
        saveAs(destFilePathName, 0);
    }

    public void saveAs(String destFilePathName, int optionSaveAs)
        throws FileUtilException, IOException
    {
        String path = new String();
        path = uploadFilesParent.getPhysicalPath(destFilePathName, optionSaveAs);
        if (path == null)
            throw new IllegalArgumentException("There is no specified destination file (1140).");
        try
        {
            java.io.File file = new java.io.File(path);
            FileOutputStream fileOut = new FileOutputStream(file);
            fileOut.write(uploadFilesParent.binArray, i_startData, i_size);
            fileOut.close();
        }
        catch (IOException e)
        {
            throw new FileUtilException("File can't be saved (1120).");
        }
    }

    public void fileToField(ResultSet rs, String columnName)
        throws SQLException, FileUtilException, IOException, ServletException
    {
        long numBlocks = 0L;
        int blockSize = 0x10000;
        int leftOver = 0;
        int pos = 0;
        if (rs == null)
            throw new IllegalArgumentException("The RecordSet cannot be null (1145).");
        if (columnName == null)
            throw new IllegalArgumentException("The columnName cannot be null (1150).");
        if (columnName.length() == 0)
            throw new IllegalArgumentException("The columnName cannot be empty (1155).");
        numBlocks = BigInteger.valueOf(i_size).divide(BigInteger.valueOf(blockSize)).longValue();
        leftOver = BigInteger.valueOf(i_size).mod(BigInteger.valueOf(blockSize)).intValue();
        try
        {
            for (int i = 1; (long)i < numBlocks; i++)
            {
                rs.updateBinaryStream(columnName, new ByteArrayInputStream(uploadFilesParent.binArray, pos, blockSize), blockSize);
                pos = pos != 0 ? pos : 1;
                pos = i * blockSize;
            }

            if (leftOver > 0)
                rs.updateBinaryStream(columnName, new ByteArrayInputStream(uploadFilesParent.binArray, pos, leftOver), leftOver);
        }
        catch (SQLException e)
        {
            byte binByte2[] = new byte[i_size];
            System.arraycopy(uploadFilesParent.binArray, i_startData, binByte2, 0, i_size);
            rs.updateBytes(columnName, binByte2);
        }
        catch (Exception e)
        {
            throw new FileUtilException("Unable to save file in the DataBase (1130).");
        }
    }

    public boolean isMissing()
    {
        return this.isMissing;
    }

    public String getFieldName()
    {
        return this.strFieldName;
    }

    public String getFileName()
    {
        return this.strFileName;
    }

    public String getFilePathName()
    {
        return this.strFilePathName;
    }

    public String getFileExt()
    {
        return this.strFileExt;
    }

    public String getContentType()
    {
        return this.strContentType;
    }

    public String getContentDisp()
    {
        return this.strContentDisp;
    }

    public String getContentString()
    {
        String strTMP = new String(uploadFilesParent.binArray, i_startData, i_size);
        return strTMP;
    }

    public String getTypeMIME()
        throws IOException
    {
        return this.strTypeMime;
    }

    public String getSubTypeMIME()
    {
        return this.strSubTypeMime;
    }

    public int getSize()
    {
        return this.i_size;
    }

    protected int getStartData()
    {
        return this.i_startData;
    }

    protected int getEndData()
    {
        return this.i_endData;
    }

    protected void setParent(UploadFiles parent)
    {
        this.uploadFilesParent = parent;
    }

    protected void setStartData(int startData)
    {
        this.i_startData = startData;
    }

    protected void setEndData(int endData)
    {
        this.i_endData = endData;
    }

    protected void setSize(int size)
    {
        this.i_size = size;
    }

    protected void setIsMissing(boolean isMissing)
    {
        this.isMissing = isMissing;
    }

    protected void setFieldName(String fieldName)
    {
        this.strFieldName = fieldName;
    }

    protected void setFileName(String fileName)
    {
        this.strFileName = fileName;
    }

    protected void setFilePathName(String filePathName)
    {
        this.strFilePathName = filePathName;
    }

    protected void setFileExt(String fileExt)
    {
        this.strFileExt = fileExt;
    }

    protected void setContentType(String contentType)
    {
        this.strContentType = contentType;
    }

    protected void setContentDisp(String contentDisp)
    {
        this.strContentDisp = contentDisp;
    }

    protected void setTypeMIME(String TypeMime)
    {
        this.strTypeMime = TypeMime;
    }

    protected void setSubTypeMIME(String subTypeMime)
    {
        this.strSubTypeMime = subTypeMime;
    }

    public byte getBinaryData(int index)
    {
        if (i_startData + index > i_endData)
            throw new ArrayIndexOutOfBoundsException("Index Out of range (1115).");
        if (i_startData + index <= i_endData)
            return uploadFilesParent.binArray[i_startData + index];
        else
            return 0;
    }

}