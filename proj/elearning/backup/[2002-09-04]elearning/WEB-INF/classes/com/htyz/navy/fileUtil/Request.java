package com.htyz.navy.fileUtil;

import java.util.Enumeration;
import java.util.Hashtable;

public class Request
{
    private Hashtable h_parameters;
    private int i_counter;

    Request()
    {
        h_parameters = new Hashtable();
        i_counter = 0;
    }

    protected void putParameter(String name, String value)
    {
        if (name == null)
            throw new IllegalArgumentException("The name of an element cannot be null.");
        if (h_parameters.containsKey(name))
        {
            Hashtable values = (Hashtable)h_parameters.get(name);
            values.put(new Integer(values.size()), value);
        } else
        {
            Hashtable values = new Hashtable();
            values.put(new Integer(0), value);
            h_parameters.put(name, values);
            i_counter++;
        }
    }

    public String getParameter(String name)
    {
        if (name == null)
            throw new IllegalArgumentException("Form's name is invalid or does not exist (1305).");
        Hashtable values = (Hashtable)h_parameters.get(name);
        if (values == null)
            return null;
        else
            return (String)values.get(new Integer(0));
    }

    public Enumeration getParameterNames()
    {
        return h_parameters.keys();
    }

    public String[] getParameterValues(String name)
    {
        if (name == null)
            throw new IllegalArgumentException("Form's name is invalid or does not exist (1305).");
        Hashtable values = (Hashtable)h_parameters.get(name);
        if (values == null)
            return null;
        String strValues[] = new String[values.size()];
        for (int i = 0; i < values.size(); i++)
            strValues[i] = (String)values.get(new Integer(i));

        return strValues;
    }

}