package com.htyz.navy.fileUtil;

import java.io.IOException;
import java.util.*;


public class Files
{

    private UploadFiles uploadFilesParent;
    private Hashtable h_files;
    private int i_counter;
    Files()
    {
        h_files = new Hashtable();
        i_counter = 0;
    }

    protected void addFile(com.htyz.navy.fileUtil.File newFile)
    {
        if (newFile == null)
        {
            throw new IllegalArgumentException("newFile cannot be null.");
        } else
        {
            h_files.put(new Integer(i_counter), newFile);
            i_counter++;
            return;
        }
    }

    public com.htyz.navy.fileUtil.File getFile(int index)
    {
        if (index < 0)
            throw new IllegalArgumentException("File's index cannot be a negative value (1210).");
        com.htyz.navy.fileUtil.File retval = (com.htyz.navy.fileUtil.File)h_files.get(new Integer(index));
        if (retval == null)
            throw new IllegalArgumentException("Files' name is invalid or does not exist (1205).");
        else
            return retval;
    }

    public int getCount()
    {
        return this.i_counter;
    }

    public long getSize()
        throws IOException
    {
        long tmp = 0L;
        for (int i = 0; i < i_counter; i++)
            tmp += getFile(i).getSize();

        return tmp;
    }

    public Collection getCollection()
    {
        return this.h_files.values();
    }

    public Enumeration getEnumeration()
    {
        return this.h_files.elements();
    }

}