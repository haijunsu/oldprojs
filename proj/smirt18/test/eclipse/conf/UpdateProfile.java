/*
 * @(#)UpdateProfile.java  2005-2-24
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header$
 * $Log$
 */
package eclipse.conf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

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
public class UpdateProfile {

    static Properties properties = new Properties();

    static String JAVA_HOME = System.getProperties().getProperty("java.home");

    public static void main(String[] args) {

        if (args.length == 0) {
            userage();
            return;
        }
        FileInputStream propertiesFile;
        try {
            propertiesFile = new FileInputStream(args[0] + "\\config_files.txt");
            properties.load(propertiesFile);
            propertiesFile.close();
            properties.put("current.dir", args[0]);
            updateWorkSpace();
            File f = new File(properties.getProperty("current.dir")
                    + "\\workspace");
            if (f.exists()) {
//                updateObjectLearnJ2ee();
//                updateObjectLearnJBOSS();
//                updateObjectLearnTomcat();
                updateSysdeo();
                updateEnvVar();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     */
    private static void userage() {
        System.out
                .println("useage: java -classpath eclipseConfig.jar eclipse.conf.UpdateProfile <path>");

    }

    static void updateWorkSpace() {
        try {
            StringBuffer buf = new StringBuffer();
            String _workfile = properties.getProperty("current.dir") + "\\"
                    + properties.getProperty("eclipse.recent.workspace.file");
            File f = new File(_workfile);
            if (!f.exists()) {
                return;
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    new FileInputStream(f)));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {

                if (inputLine.trim().startsWith("<workspace path=")) {
                    inputLine = "<workspace path=\""
                            + properties.getProperty("current.dir")
                            + "\\workspace\" />";
                }

                buf.append(inputLine);
                buf.append('\n');
            }

            in.close();
            toTextFile(f, buf.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void updateSysdeo() {
        String _sysdeoFile = properties.getProperty("current.dir") + "\\"
                + properties.getProperty("sysdeo.eclipse.tomcat.config.file");
        String _tomcatHome = getTomcatHome();
        try {
            FileInputStream _propertiesFile = new FileInputStream(_sysdeoFile);
            Properties _properties = new Properties();
            _properties.load(_propertiesFile);
            _properties.put("contextsDir", _tomcatHome
                    + "\\conf\\Catalina\\localhost");
            _properties.put("tomcatDir", _tomcatHome);
            _properties.put("tomcatConfigFile", _tomcatHome
                    + "\\conf\\server.xml");
            FileOutputStream _propertiesOutFile = new FileOutputStream(
                    _sysdeoFile);
            _properties.store(_propertiesOutFile, "#navyupdate");
            _propertiesFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void updateEnvVar() {
        String _varFile = properties.getProperty("current.dir") + "\\"
                + properties.getProperty("eclipse.env.vars.file");
        String _varJboss = properties.getProperty("eclipse.env.var.jboss.name");
        String _varPrefix = "org.eclipse.jdt.core.classpathVariable.";
        try {
            FileInputStream _propertiesFile = new FileInputStream(_varFile);
            Properties _properties = new Properties();
            _properties.load(_propertiesFile);
            _properties.put(_varPrefix + "JDK_TOOLS", getJdkTools());
            _properties.put(_varPrefix + "TOMCAT_HOME", getTomcatHome());
            //_properties.put(_varPrefix + _varJboss.trim(), getJbossHome());
            FileOutputStream _propertiesOutFile = new FileOutputStream(_varFile);
            _properties.store(_propertiesOutFile, "#navyupdate");
            _propertiesFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String getJdkTools() {
        String _strTemp = JAVA_HOME;
        _strTemp = _strTemp.replace('\\', '/');
        if (_strTemp.endsWith("jre")) {
            _strTemp = _strTemp.substring(0, _strTemp.length() - 4);
        }
        _strTemp = _strTemp + "/lib/tools.jar";
        return _strTemp;
    }

    static String getTomcatHome() {
        String _tomcatHome = properties.getProperty("current.dir") + "\\"
                + properties.getProperty("tomcat.home");
        return _tomcatHome;
    }

    static String getJbossHome() {
        String _jbossHome = properties.getProperty("current.dir") + "\\"
                + properties.getProperty("jboss.home");
        return _jbossHome;
    }

    static void updateObjectLearnJ2ee() {
        String _j2eeFile = properties.getProperty("current.dir") + "\\"
                + properties.getProperty("object.learn.j2ee.j2ee.config.file");
        try {
            FileInputStream _propertiesFile = new FileInputStream(_j2eeFile);
            Properties _properties = new Properties();
            _properties.load(_propertiesFile);
            _properties.put("TOOLS_JAR", getJdkTools());
            FileOutputStream _propertiesOutFile = new FileOutputStream(
                    _j2eeFile);
            _properties.store(_propertiesOutFile, "#navyupdate");
            _propertiesFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void updateObjectLearnTomcat() {
        try {
            String _tomcatHome = getTomcatHome();
            String _objectLearnTomcatFile = properties
                    .getProperty("current.dir")
                    + "\\"
                    + properties
                            .getProperty("object.learn.j2ee.tomcat.config.file");
            File _f = new File(_objectLearnTomcatFile);
            String _contents = readTextFile(_f, _tomcatHome);
            toTextFile(_f, _contents);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void updateObjectLearnJBOSS() {
        try {
            String _jbossHome = getJbossHome();
            String _objectLearnJbossFile = properties
                    .getProperty("current.dir")
                    + "\\"
                    + properties
                            .getProperty("object.learn.j2ee.jboss.config.file");
            File _f = new File(_objectLearnJbossFile);
            String _contents = readTextFile(_f, _jbossHome);
            toTextFile(_f, _contents);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String readTextFile(File f, String serverHome)
            throws IOException {

        StringBuffer buf = new StringBuffer();

        BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(f)));
        String inputLine;
        boolean _isServerRoot = false;
        boolean _isClassPath = false;
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.trim().equals("<property id=\"serverRootDirectory\"")) {
                _isServerRoot = true;
            }
            if (inputLine.trim().equals("<property id=\"classPath\"")) {
                _isClassPath = true;
            }

            if (inputLine.trim().startsWith("default")
                    && (_isClassPath || _isServerRoot)) {
                inputLine = "\tdefault=\"" + serverHome + "\" />";
                inputLine = inputLine.replace('\\', '/');
                if (_isClassPath)
                    _isClassPath = false;
                if (_isServerRoot)
                    _isServerRoot = false;
            }

            buf.append(inputLine);
            buf.append('\n');
        }
        in.close();
        return buf.toString();

    }

    public static void toTextFile(File f, String content) throws IOException {
        FileWriter out = new FileWriter(f);
        out.write(content);
        out.close();
    }

}