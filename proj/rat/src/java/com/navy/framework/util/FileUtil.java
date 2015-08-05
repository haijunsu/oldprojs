/*
 * @(#)FileUtil.java  2005-2-24
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header$
 * $Log$
 */
package com.navy.framework.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

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
public class FileUtil {

	public static String readTextFile(File f) throws IOException {

		StringBuffer buf = new StringBuffer();

		BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(f)));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
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

	public static void copy(String inputFilename, String outputFilename)
			throws IOException {
		FileUtil.copy(new File(inputFilename), new File(outputFilename));
	}

	public static void copy(File input, File output) throws IOException {
		if (input.isDirectory() && output.isDirectory()) {
			FileUtil.copyDir(input, output);
		} else {
			FileUtil.copyFile(input, output);
		}
	}

    public static void copyFile(File from, File to) throws IOException {
        InputStream in = null;
        OutputStream out = null;

        try {
            in = new FileInputStream(from);
        } catch (IOException ex) {
            throw new IOException(
                    "Utilities.copyFile: opening input stream '"
                    + from.getPath()
                    + "', "
                    + ex.getMessage());
        }

        try {
            out = new FileOutputStream(to);
        } catch (Exception ex) {
            try {
                in.close();
            } catch (IOException ex1) {
            }
            throw new IOException(
                    "Utilities.copyFile: opening output stream '"
                    + to.getPath()
                    + "', "
                    + ex.getMessage());
        }

        copyInputToOutput(in, out, from.length());
    }

    //------------------------------------------------------------------------
    /**
     * Utility method to copy an input stream to an output stream.
     * Wraps both streams in buffers. Ensures right numbers of bytes copied.
     */
    public static void copyInputToOutput(
            InputStream input,
            OutputStream output,
            long byteCount)
            throws IOException {
        int bytes;
        long length;

        BufferedInputStream in = new BufferedInputStream(input);
        BufferedOutputStream out = new BufferedOutputStream(output);

        byte[] buffer;
        buffer = new byte[8192];

        for (length = byteCount; length > 0;) {
            bytes = (int) (length > 8192 ? 8192 : length);

            try {
                bytes = in.read(buffer, 0, bytes);
            } catch (IOException ex) {
                try {
                    in.close();
                    out.close();
                } catch (IOException ex1) {
                }
                throw new IOException(
                        "Reading input stream, " + ex.getMessage());
            }

            if (bytes < 0)
                break;

            length -= bytes;

            try {
                out.write(buffer, 0, bytes);
            } catch (IOException ex) {
                try {
                    in.close();
                    out.close();
                } catch (IOException ex1) {
                }
                throw new IOException(
                        "Writing output stream, " + ex.getMessage());
            }
        }

        try {
            in.close();
            out.close();
        } catch (IOException ex) {
            throw new IOException("Closing file streams, " + ex.getMessage());
        }
    }

	public static void copyDir(File inputDir, File outputDir)
			throws IOException {
		File[] files = inputDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File destFile = new File(outputDir.getAbsolutePath()
					+ File.separator + files[i].getName());
			if (!destFile.exists()) {
				if (files[i].isDirectory()) {
					destFile.mkdir();
				}
			}
			FileUtil.copy(files[i], destFile);
		}
	}

	public static boolean dirContainsFiles(File dir, String extension,
			boolean recursive) {
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile() && files[i].getName().endsWith(extension))
				return true;
			if (recursive && files[i].isDirectory())
				return FileUtil
						.dirContainsFiles(files[i], extension, recursive);
		}

		return false;
	}

	public static String readPropertyInXMLFile(File file, String property)
			throws IOException {
		String content = FileUtil.readTextFile(file);
		int startTagIdx = content.indexOf("<" + property + ">");
		int endTagIdx = content.indexOf("</" + property + ">");
		if (startTagIdx == -1)
			throw new IOException("Property " + property
					+ " not found in file " + file);

		return content
				.substring(startTagIdx + property.length() + 2, endTagIdx);
	}

	public static void removeDir(File dir) throws IOException {
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				FileUtil.removeDir(files[i]);
			} else {
				files[i].delete();
			}
		}
		dir.delete();
	}
}