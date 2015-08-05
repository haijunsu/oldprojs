

package com.htyz.navy.sql;
import java.sql.*;
import java.util.Calendar;
import java.math.BigDecimal;

import java.io.InputStream;

public class PooledCallableStatement extends PooledPreparedStatement implements CallableStatement {

  protected CallableStatement cstmt;

  public PooledCallableStatement(CallableStatement stmt) {
    this.stmt = stmt;
    this.cstmt = (CallableStatement)cstmt;
  }

  public void close() throws SQLException {  }



  /**************************************************************************
   *
   * CallableStatment methods
   *
   **************************************************************************/

  public void registerOutParameter(int index, int type) throws SQLException {
    cstmt.registerOutParameter(index, type);
  }

  public void registerOutParameter(int index, int type, int scale) throws SQLException {
    cstmt.registerOutParameter(index, type, scale);
  }

  public boolean wasNull() throws SQLException {
    return cstmt.wasNull();
  }

  public BigDecimal getBigDecimal(int index, int scale) throws SQLException {
    return cstmt.getBigDecimal(index, scale);
  }

  public boolean getBoolean(int index) throws SQLException {
    return cstmt.getBoolean(index);
  }

  public byte getByte(int index) throws SQLException {
    return cstmt.getByte(index);
  }

  public byte[] getBytes(int index) throws SQLException {
    return cstmt.getBytes(index);
  }

  public Date getDate(int index) throws SQLException {
    return cstmt.getDate(index);
  }

  public double getDouble(int index) throws SQLException {
    return cstmt.getDouble(index);
  }

  public float getFloat(int index) throws SQLException {
    return cstmt.getFloat(index);
  }

  public int getInt(int index) throws SQLException {
    return cstmt.getInt(index);
  }

  public long getLong(int index) throws SQLException {
    return cstmt.getLong(index);
  }

  public Object getObject(int index) throws SQLException {
    return cstmt.getObject(index);
  }

  public short getShort(int index) throws SQLException {
    return cstmt.getShort(index);
  }

  public String getString(int index) throws SQLException {
    return cstmt.getString(index);
  }

  public Time getTime(int index) throws SQLException {
    return cstmt.getTime(index);
  }

  public Timestamp getTimestamp(int index) throws SQLException {
    return cstmt.getTimestamp(index);
  }


  public java.sql.Time getTime(int i , java.util.Calendar c)  throws SQLException {
    return cstmt.getTime(i,c);
  }
  public java.sql.Clob getClob(int i) throws SQLException  {
    return cstmt.getClob(i);
  }
  public java.math.BigDecimal getBigDecimal(int i)  throws SQLException {
    return cstmt.getBigDecimal(i);
  }
  public void registerOutParameter(int i, int j, java.lang.String s)  throws SQLException {
    cstmt.registerOutParameter(i,j,s);
  }
  public java.sql.Timestamp getTimestamp(int i, java.util.Calendar c)  throws SQLException {
    return cstmt.getTimestamp(i,c);
  }
  public java.sql.Blob getBlob(int i)  throws SQLException {
    return cstmt.getBlob(i);
   }
  public java.sql.Array getArray(int i)  throws SQLException {
    return cstmt.getArray(i);
  }
  public java.sql.Ref getRef(int i)  throws SQLException {
    return cstmt.getRef(i);
  }
  public java.sql.Date getDate(int i, java.util.Calendar c)  throws SQLException {
    return cstmt.getDate(i,c);
  }
  public java.lang.Object getObject(int i, java.util.Map m)  throws SQLException {
    return cstmt.getObject(i,m);
  }


  
}
