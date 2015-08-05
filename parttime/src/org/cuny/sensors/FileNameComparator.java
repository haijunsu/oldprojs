/*
 * 
 */
package org.cuny.sensors;

import java.io.File;
import java.util.Comparator;

/**
 * 
 * @author Haijun Su
 * Created date: 2013-12-11
 */
public class FileNameComparator implements Comparator<File> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
    public int compare(File f1, File f2) {
        //file > directory
        if (f1 == f2) {
            return 0;
        } else {
            if (f1.isFile()) {
                if (f2.isDirectory()) {
                    return -1;
                } else {
                    //f2 is file too.                       
                    //return 0;
                    return compareString(f1.getName(),f2.getName());
                }
            } else {
                //f1 is dir
                if (f2.isDirectory()) {
                    //f2 is directory too.
                    //return 0;
                    return compareString(f1.getName(),f2.getName());
                } else {
                    return 1;
                }
            }
        }
    }
    private int compareString(String s1, String s2){
        int result =0;
        try{
            byte[] bb1 = s1.getBytes();
            byte[] bb2 = s2.getBytes();
            int min_len = Math.max(bb1.length, bb2.length);
            for(int i=0;i<min_len;i++){
                result = bb1[i]-bb2[i];
                if(result!=0){
                    return result;
                }
            }
        }catch(Exception e){
        }
        return result;
    }
}

