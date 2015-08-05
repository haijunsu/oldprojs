/*
 * @(#)Shell.java  2005-3-19
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * </p>
 * 
 * $Revision$
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class Shell {

    public static void run(String strCmd) {
        run(strCmd, null);
    }

    public static void run(String strCmd, String strOutputFile) {
        try {
            String _osName = System.getProperty("os.name");
            System.out.println("OS name: " + _osName);
            String[] cmd = new String[3];

            if (_osName.equals("Windows 95") || _osName.equals("Windows 98") || _osName.equals("Windows Me")) {
                cmd[0] = "command.com";
                cmd[1] = "/C";
                cmd[2] = strCmd;
            } else if (_osName.startsWith("Windows")) {
                cmd[0] = "cmd.exe";
                cmd[1] = "/C";
                cmd[2] = strCmd;
            } else {
                cmd = new String[] { strCmd };
            }
            String _str = "";
            for (int i = 0; i < cmd.length; i++) {
                _str += cmd[i] + " ";
            }
            System.out.println("Runing coammand: " + _str);
            FileOutputStream _fos = null;
            if (!StringUtil.isBlankString(strOutputFile)) {
                _fos = new FileOutputStream(strOutputFile);
            }
            Runtime _rt = Runtime.getRuntime();
            Process _proc = _rt.exec(_str);
            // any error message?
            StreamGobbler _errorGobbler = new StreamGobbler(_proc
                    .getErrorStream(), "ERROR");

            // any output?
            StreamGobbler _outputGobbler = new StreamGobbler(_proc
                    .getInputStream(), "OUTPUT", _fos);

            // kick them off
            _errorGobbler.start();
            _outputGobbler.start();

            // any error???
            int _exitVal = _proc.waitFor();
            System.out.println("ExitValue: " + _exitVal);
            if (_fos != null) {
                _fos.flush();
                _fos.close();
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}

class StreamGobbler extends Thread {
    InputStream m_is;

    String m_type;

    OutputStream m_os;

    StreamGobbler(InputStream is, String type) {
        this(is, type, null);
    }

    StreamGobbler(InputStream is, String type, OutputStream redirect) {
        this.m_is = is;
        this.m_type = type;
        this.m_os = redirect;
    }

    public void run() {
        try {
            PrintWriter _pw = null;
            if (m_os != null)
                _pw = new PrintWriter(m_os);

            InputStreamReader _isr = new InputStreamReader(m_is);
            BufferedReader _br = new BufferedReader(_isr);
            String _line = null;
            while ((_line = _br.readLine()) != null) {
                if (_pw != null)
                    _pw.println(_line);
                System.out.println(m_type + ">" + _line);
            }
            if (_pw != null)
                _pw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}