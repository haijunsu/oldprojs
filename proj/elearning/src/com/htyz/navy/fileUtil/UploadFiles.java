package com.htyz.navy.fileUtil;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

public class UploadFiles {

    protected byte binArray[];
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected ServletContext application;
    private int i_totalBytes;
    private int i_currentIndex;
    private int i_startData;
    private int i_endData;
    private String strBoundary;
    private long ll_totalMaxFileSize;
    private long ll_maxFileSize;
    private Vector v_deniedFilesList;
    private Vector v_allowedFilesList;
    private boolean denyPhysicalPath;
    private boolean forcePhysicalPath;
    private String strContentDisposition;
    public static final int SAVE_AUTO = 0;
    public static final int SAVE_VIRTUAL = 1;
    public static final int SAVE_PHYSICAL = 2;
    private Files files;
    private Request formRequest;
    public UploadFiles() {
        i_totalBytes = 0;
        i_currentIndex = 0;
        i_startData = 0;
        i_endData = 0;
        strBoundary = new String();
        ll_totalMaxFileSize = 0L;
        ll_maxFileSize = 0L;
        v_deniedFilesList = new Vector();
        v_allowedFilesList = new Vector();
        denyPhysicalPath = false;
        forcePhysicalPath = false;
        strContentDisposition = new String();
        files = new Files();
        formRequest = new Request();
    }
    public void downloadField(
        ResultSet rs,
        String columnName,
        String contentType,
        String destFileName)
        throws SQLException, IOException, ServletException {
        if (rs == null)
            throw new IllegalArgumentException("The RecordSet cannot be null (1045).");
        if (columnName == null)
            throw new IllegalArgumentException("The columnName cannot be null (1050).");
        if (columnName.length() == 0)
            throw new IllegalArgumentException("The columnName cannot be empty (1055).");
        byte b[] = rs.getBytes(columnName);
        if (contentType == null)
            response.setContentType("application/x-msdownload");
        else
            if (contentType.length() == 0)
                response.setContentType("application/x-msdownload");
            else
                response.setContentType(contentType);
        response.setContentLength(b.length);
        if (destFileName == null)
            response.setHeader("Content-Disposition", "attachment;");
        else
            if (destFileName.length() == 0)
                response.setHeader("Content-Disposition", "attachment;");
            else
                response.setHeader(
                    "Content-Disposition",
                    "attachment; filename=".concat(String.valueOf(destFileName)));
        response.getOutputStream().write(b, 0, b.length);
    }
    public void downloadFile(String sourceFilePathName)
        throws FileUtilException, IOException, ServletException {
        downloadFile(sourceFilePathName, null, null);
    }
    public void downloadFile(String sourceFilePathName, String contentType)
        throws FileUtilException, IOException, ServletException {
        downloadFile(sourceFilePathName, contentType, null);
    }
    public void downloadFile(
        String sourceFilePathName,
        String contentType,
        String destFileName)
        throws FileUtilException, IOException, ServletException {
        downloadFile(sourceFilePathName, contentType, destFileName, 65000);
    }
    public void downloadFile(
        String sourceFilePathName,
        String contentType,
        String destFileName,
        int blockSize)
        throws FileUtilException, IOException, ServletException {
        if (sourceFilePathName == null)
            throw new IllegalArgumentException(
                String.valueOf(
                    (new StringBuffer("File '")).append(sourceFilePathName).append(
                        "' not found (1040).")));
        if (sourceFilePathName.equals(""))
            throw new IllegalArgumentException(
                String.valueOf(
                    (new StringBuffer("File '")).append(sourceFilePathName).append(
                        "' not found (1040).")));
        if (!isVirtual(sourceFilePathName) && denyPhysicalPath)
            throw new SecurityException("Physical path is denied (1035).");
        if (isVirtual(sourceFilePathName))
            sourceFilePathName = application.getRealPath(sourceFilePathName);
        java.io.File file = new java.io.File(sourceFilePathName);
        FileInputStream fileIn = new FileInputStream(file);
        long fileLen = file.length();
        int readBytes = 0;
        int totalRead = 0;
        byte b[] = new byte[blockSize];
        if (contentType == null)
            response.setContentType("application/x-msdownload");
        else
            if (contentType.length() == 0)
                response.setContentType("application/x-msdownload");
            else
                response.setContentType(contentType);
        response.setContentLength((int) fileLen);
        strContentDisposition =
            strContentDisposition != null ? strContentDisposition : "attachment;";
        if (destFileName == null)
            response.setHeader(
                "Content-Disposition",
                String.valueOf(
                    (new StringBuffer(String.valueOf(strContentDisposition)))
                        .append(" filename=")
                        .append(getFileName(sourceFilePathName))));
        else
            if (destFileName.length() == 0)
                response.setHeader("Content-Disposition", strContentDisposition);
            else
                response.setHeader(
                    "Content-Disposition",
                    String.valueOf(
                        (new StringBuffer(String.valueOf(strContentDisposition)))
                            .append(" filename=")
                            .append(destFileName)));
        while ((long) totalRead < fileLen) {
            readBytes = fileIn.read(b, 0, blockSize);
            totalRead += readBytes;
            response.getOutputStream().write(b, 0, readBytes);
        }
        fileIn.close();
    }
    public void fieldToFile(
        ResultSet rs,
        String columnName,
        String destFilePathName)
        throws SQLException, FileUtilException, IOException, ServletException {
        try {
            if (application.getRealPath(destFilePathName) != null)
                destFilePathName = application.getRealPath(destFilePathName);
            InputStream is_data = rs.getBinaryStream(columnName);
            FileOutputStream file = new FileOutputStream(destFilePathName);
            int c;
            while ((c = is_data.read()) != -1)
                file.write(c);
            file.close();
        } catch (Exception e) {
            throw new FileUtilException("Unable to save file from the DataBase (1020).");
        }
    }
    public byte getBinaryData(int index) {
        byte retval;
        try {
            retval = binArray[index];
        } catch (Exception e) {
            throw new ArrayIndexOutOfBoundsException("Index out of range (1005).");
        }
        return retval;
    }
    private String getContentDisp(String dataHeader) {
        String value = new String();
        int start = 0;
        int end = 0;
        start = dataHeader.indexOf(":") + 1;
        end = dataHeader.indexOf(";");
        value = dataHeader.substring(start, end);
        return value;
    }
    private String getContentType(String dataHeader) {
        String token = new String();
        String value = new String();
        int start = 0;
        int end = 0;
        token = "Content-Type:";
        start = dataHeader.indexOf(token) + token.length();
        if (start != -1) {
            end = dataHeader.length();
            value = dataHeader.substring(start, end);
        }
        return value;
    }
    private String getDataFieldValue(String dataHeader, String fieldName) {
        String token = new String();
        String value = new String();
        int pos = 0;
        int i = 0;
        int start = 0;
        int end = 0;
        token =
            String.valueOf(
                (new StringBuffer(String.valueOf(fieldName))).append("=").append('"'));
        pos = dataHeader.indexOf(token);
        if (pos > 0) {
            i = pos + token.length();
            start = i;
            token = "\"";
            end = dataHeader.indexOf(token, i);
            if (start > 0 && end > 0)
                value = dataHeader.substring(start, end);
        }
        return value;
    }
    private String getDataHeader() {
        int start = i_currentIndex;
        int end = 0;
        int len = 0;
        boolean found = false;
        while (!found)
            if (binArray[i_currentIndex] == 13 && binArray[i_currentIndex + 2] == 13) {
                found = true;
                end = i_currentIndex - 1;
                i_currentIndex = i_currentIndex + 2;
            } else {
                i_currentIndex++;
            }
        String dataHeader = new String(binArray, start, (end - start) + 1);
        return dataHeader;
    }
    private void getDataSection() {
        boolean found = false;
        String dataHeader = new String();
        int searchPos = i_currentIndex;
        int keyPos = 0;
        int boundaryLen = strBoundary.length();
        i_startData = i_currentIndex;
        i_endData = 0;
        do {
            if (searchPos >= i_totalBytes)
                break;
            if (binArray[searchPos] == (byte) strBoundary.charAt(keyPos)) {
                if (keyPos == boundaryLen - 1) {
                    i_endData = ((searchPos - boundaryLen) + 1) - 3;
                    break;
                }
                searchPos++;
                keyPos++;
            } else {
                searchPos++;
                keyPos = 0;
            }
        } while (true);
        i_currentIndex = i_endData + boundaryLen + 3;
    }
    private String getFileExt(String fileName) {
        String value = new String();
        int start = 0;
        int end = 0;
        if (fileName == null)
            return null;
        start = fileName.lastIndexOf('.') + 1;
        end = fileName.length();
        value = fileName.substring(start, end);
        if (fileName.lastIndexOf('.') > 0)
            return value;
        else
            return "";
    }
    private String getFileName(String filePathName) {
        String token = new String();
        String value = new String();
        int pos = 0;
        int i = 0;
        int start = 0;
        int end = 0;
        pos = filePathName.lastIndexOf('/');
        if (pos != -1)
            return filePathName.substring(pos + 1, filePathName.length());
        pos = filePathName.lastIndexOf('\\');
        if (pos != -1)
            return filePathName.substring(pos + 1, filePathName.length());
        else
            return filePathName;
    }
    public Files getFiles() {
        return files;
    }
    protected String getPhysicalPath(String filePathName, int option)
        throws IOException {
        String path = new String();
        String fileName = new String();
        String fileSeparator = new String();
        boolean isPhysical = false;
        fileSeparator = System.getProperty("file.separator");
        if (filePathName == null)
            throw new IllegalArgumentException("There is no specified destination file (1140).");
        if (filePathName.equals(""))
            throw new IllegalArgumentException("There is no specified destination file (1140).");
        if (filePathName.lastIndexOf("\\") >= 0) {
            path = filePathName.substring(0, filePathName.lastIndexOf("\\"));
            fileName = filePathName.substring(filePathName.lastIndexOf("\\") + 1);
        }
        if (filePathName.lastIndexOf("/") >= 0) {
            path = filePathName.substring(0, filePathName.lastIndexOf("/"));
            fileName = filePathName.substring(filePathName.lastIndexOf("/") + 1);
        }
        path = path.length() != 0 ? path : "/";
        java.io.File physicalPath = new java.io.File(path);
        if (physicalPath.exists())
            isPhysical = true;
        if (option == 0) {
            if (isVirtual(path)) {
                path = application.getRealPath(path);
                if (path.endsWith(fileSeparator))
                    path = path + fileName;
                else
                    path =
                        String.valueOf(
                            (new StringBuffer(String.valueOf(path))).append(fileSeparator).append(
                                fileName));
                return path;
            }
            if (isPhysical) {
                if (denyPhysicalPath)
                    throw new IllegalArgumentException("Physical path is denied (1125).");
                else
                    return filePathName;
            } else {
                throw new IllegalArgumentException("This path does not exist (1135).");
            }
        }
        if (option == 1) {
            if (isVirtual(path)) {
                path = application.getRealPath(path);
                if (path.endsWith(fileSeparator))
                    path = path + fileName;
                else
                    path =
                        String.valueOf(
                            (new StringBuffer(String.valueOf(path))).append(fileSeparator).append(
                                fileName));
                return path;
            }
            if (isPhysical)
                throw new IllegalArgumentException("The path is not a virtual path.");
            else
                throw new IllegalArgumentException("This path does not exist (1135).");
        }
        if (option == 2) {
            if (isPhysical)
                if (denyPhysicalPath)
                    throw new IllegalArgumentException("Physical path is denied (1125).");
                else
                    return filePathName;
            if (isVirtual(path))
                throw new IllegalArgumentException("The path is not a physical path.");
            else
                throw new IllegalArgumentException("This path does not exist (1135).");
        } else {
            return null;
        }
    }
    public Request getRequest() {
        return formRequest;
    }
    public int getSize() {
        return i_totalBytes;
    }
    private String getSubTypeMIME(String ContentType) {
        String value = new String();
        int start = 0;
        int end = 0;
        start = ContentType.indexOf("/") + 1;
        if (start != -1) {
            end = ContentType.length();
            return ContentType.substring(start, end);
        } else {
            return ContentType;
        }
    }
    private String getTypeMIME(String ContentType) {
        String value = new String();
        int pos = 0;
        pos = ContentType.indexOf("/");
        if (pos != -1)
            return ContentType.substring(1, pos);
        else
            return ContentType;
    }
    /**
     * @deprecated Method init is deprecated
     */

    public final void init(ServletConfig config) throws ServletException {
        application = config.getServletContext();
    }
    public final void initialize(PageContext pageContext) throws ServletException {
        application = pageContext.getServletContext();
        request = (HttpServletRequest) pageContext.getRequest();
        response = (HttpServletResponse) pageContext.getResponse();
    }
    public final void initialize(
        ServletConfig config,
        HttpServletRequest req,
        HttpServletResponse res)
        throws ServletException {
        application = config.getServletContext();
        request = req;
        response = res;
    }
    /**
     * @deprecated Method initialize is deprecated
     */

    public final void initialize(
        ServletContext application,
        HttpSession session,
        HttpServletRequest req,
        HttpServletResponse res,
        JspWriter out)
        throws ServletException {
        application = application;
        request = req;
        response = res;
    }
    private boolean isVirtual(String pathName) {
        if (application.getRealPath(pathName) != null) {
            java.io.File virtualFile = new java.io.File(application.getRealPath(pathName));
            return virtualFile.exists();
        } else {
            return false;
        }
    }
    public int save(String destPathName)
        throws FileUtilException, IOException, ServletException {
        return save(destPathName, 0);
    }
    public int save(String destPathName, int option)
        throws FileUtilException, IOException, ServletException {
        int count = 0;
        if (destPathName == null)
            destPathName = application.getRealPath("/");
        if (destPathName.indexOf("/") != -1) {
            if (destPathName.charAt(destPathName.length() - 1) != '/')
                destPathName = String.valueOf(destPathName).concat("/");
        } else
            if (destPathName.charAt(destPathName.length() - 1) != '\\')
                destPathName = String.valueOf(destPathName).concat("\\");
        for (int i = 0; i < files.getCount(); i++)
            if (!files.getFile(i).isMissing()) {
                files.getFile(i).saveAs(destPathName + files.getFile(i).getFileName(), option);
                count++;
            }

        return count;
    }
    /**
     * @deprecated Method service is deprecated
     */

    public void service(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {
        this.request = req;
        this.response = res;
    }
    public void setAllowedFilesList(String allowedFilesList) {
        String ext = "";
        if (allowedFilesList != null) {
            ext = "";
            for (int i = 0; i < allowedFilesList.length(); i++)
                if (allowedFilesList.charAt(i) == ',') {
                    if (!this.v_allowedFilesList.contains(ext))
                        this.v_allowedFilesList.addElement(ext);
                    ext = "";
                } else {
                    ext = ext + allowedFilesList.charAt(i);
                }

            if (ext != "")
                this.v_allowedFilesList.addElement(ext);
        } else {
            this.v_allowedFilesList = null;
        }
    }
    public void setContentDisposition(String contentDisposition) {
        this.strContentDisposition = contentDisposition;
    }
    public void setDeniedFilesList(String deniedFilesList)
        throws SQLException, IOException, ServletException {
        String ext = "";
        if (deniedFilesList != null) {
            ext = "";
            for (int i = 0; i < deniedFilesList.length(); i++)
                if (deniedFilesList.charAt(i) == ',') {
                    if (!this.v_deniedFilesList.contains(ext))
                        this.v_deniedFilesList.addElement(ext);
                    ext = "";
                } else {
                    ext = ext + deniedFilesList.charAt(i);
                }

            if (ext != "")
                this.v_deniedFilesList.addElement(ext);
        } else {
            this.v_deniedFilesList = null;
        }
    }
    public void setDenyPhysicalPath(boolean deny) {
        this.denyPhysicalPath = deny;
    }
    public void setForcePhysicalPath(boolean force) {
        this.forcePhysicalPath = force;
    }
    public void setMaxFileSize(long maxFileSize) {
        this.ll_maxFileSize = maxFileSize;
    }
    public void setTotalMaxFileSize(long totalMaxFileSize) {
        this.ll_totalMaxFileSize = totalMaxFileSize;
    }
    public void upload() throws FileUtilException, IOException, ServletException {
        int totalRead = 0;
        int readBytes = 0;
        long totalFileSize = 0L;
        boolean found = false;
        String dataHeader = new String();
        String fieldName = new String();
        String fileName = new String();
        String fileExt = new String();
        String filePathName = new String();
        String contentType = new String();
        String contentDisp = new String();
        String typeMIME = new String();
        String subTypeMIME = new String();
        boolean isFile = false;
        i_totalBytes = request.getContentLength();
        binArray = new byte[i_totalBytes];
        for (; totalRead < i_totalBytes; totalRead += readBytes)
            try {
                request.getInputStream();
                readBytes =
                    request.getInputStream().read(binArray, totalRead, i_totalBytes - totalRead);
            } catch (Exception e) {
                throw new FileUtilException("Unable to upload.");
            }

        for (; !found && i_currentIndex < i_totalBytes; i_currentIndex++)
            if (binArray[i_currentIndex] == 13)
                found = true;
            else
                strBoundary = strBoundary + (char) binArray[i_currentIndex];

        if (i_currentIndex == 1)
            return;
        i_currentIndex++;
        do {
            if (i_currentIndex >= i_totalBytes)
                break;
            dataHeader = getDataHeader();
            i_currentIndex = i_currentIndex + 2;
            isFile = dataHeader.indexOf("filename") > 0;
            fieldName = getDataFieldValue(dataHeader, "name");
            if (isFile) {
                filePathName = getDataFieldValue(dataHeader, "filename");
                fileName = getFileName(filePathName);
                fileExt = getFileExt(fileName);
                contentType = getContentType(dataHeader);
                contentDisp = getContentDisp(dataHeader);
                typeMIME = getTypeMIME(contentType);
                subTypeMIME = getSubTypeMIME(contentType);
            }
            getDataSection();
            if (isFile && fileName.length() > 0) {
                if (v_deniedFilesList.contains(fileExt))
                    throw new SecurityException("The extension of the file is denied to be uploaded (1015).");
                if (!v_allowedFilesList.isEmpty() && !v_allowedFilesList.contains(fileExt))
                    throw new SecurityException("The extension of the file is not allowed to be uploaded (1010).");
                if (ll_maxFileSize > (long) 0
                    && (long) ((i_endData - i_startData) + 1) > ll_maxFileSize)
                    throw new SecurityException(
                        String.valueOf(
                            (new StringBuffer("Size exceeded for this file : ")).append(fileName).append(
                                " (1105).")));
                totalFileSize += (i_endData - i_startData) + 1;
                if (ll_totalMaxFileSize > (long) 0 && totalFileSize > ll_totalMaxFileSize)
                    throw new SecurityException("Total File Size exceeded (1110).");
            }
            if (isFile) {
                com.htyz.navy.fileUtil.File newFile = new com.htyz.navy.fileUtil.File();
                newFile.setParent(this);
                newFile.setFieldName(fieldName);
                newFile.setFileName(fileName);
                newFile.setFileExt(fileExt);
                newFile.setFilePathName(filePathName);
                newFile.setIsMissing(filePathName.length() == 0);
                newFile.setContentType(contentType);
                newFile.setContentDisp(contentDisp);
                newFile.setTypeMIME(typeMIME);
                newFile.setSubTypeMIME(subTypeMIME);
                if (contentType.indexOf("application/x-macbinary") > 0)
                    i_startData = i_startData + 128;
                newFile.setSize((i_endData - i_startData) + 1);
                newFile.setStartData(i_startData);
                newFile.setEndData(i_endData);
                files.addFile(newFile);
            } else {
                String value = new String(binArray, i_startData, (i_endData - i_startData) + 1);
                formRequest.putParameter(fieldName, value);
            }
            if ((char) binArray[i_currentIndex + 1] == '-')
                break;
            i_currentIndex = i_currentIndex + 2;
        } while (true);
    }
    public void uploadInFile(String destFilePathName)
        throws FileUtilException, IOException {
        int intsize = 0;
        int pos = 0;
        int readBytes = 0;
        if (destFilePathName == null)
            throw new IllegalArgumentException("There is no specified destination file (1025).");
        if (destFilePathName.length() == 0)
            throw new IllegalArgumentException("There is no specified destination file (1025).");
        if (!isVirtual(destFilePathName) && denyPhysicalPath)
            throw new SecurityException("Physical path is denied (1035).");
        intsize = request.getContentLength();
        binArray = new byte[intsize];
        for (; pos < intsize; pos += readBytes)
            try {
                readBytes = request.getInputStream().read(binArray, pos, intsize - pos);
            } catch (Exception e) {
                throw new FileUtilException("Unable to upload.");
            }

        if (isVirtual(destFilePathName))
            destFilePathName = application.getRealPath(destFilePathName);
        try {
            java.io.File file = new java.io.File(destFilePathName);
            FileOutputStream fileOut = new FileOutputStream(file);
            fileOut.write(binArray);
            fileOut.close();
        } catch (Exception e) {
            throw new FileUtilException("The Form cannot be saved in the specified file (1030).");
        }
    }
}
