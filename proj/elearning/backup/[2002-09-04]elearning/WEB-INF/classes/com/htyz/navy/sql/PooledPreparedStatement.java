

package com.htyz.navy.sql;

import java.sql.*;
import java.util.Calendar;
import java.math.BigDecimal;

import java.io.InputStream;

public class PooledPreparedStatement extends PooledStatement implements PreparedStatement {
  protected PreparedStatement pstmt;

  protected PooledPreparedStatement() {
  }

  public PooledPreparedStatement(PreparedStatement stmt) {
    this.stmt = stmt;
    this.pstmt = (PreparedStatement)stmt;
  }

  public void close() throws SQLException {
    pstmt.clearParameters();
  }

  /**************************************************************************
   *
   * PreparedStatment methods
   *
   **************************************************************************/

  public ResultSet executeQuery() throws SQLException {
    return pstmt.executeQuery();
  }

  public int executeUpdate() throws SQLException {
    return pstmt.executeUpdate();
  }

  public void setNull(int index, int sqlType) throws SQLException {
    pstmt.setNull(index, sqlType);
  }

  public void setBoolean(int index, boolean x) throws SQLException {
    pstmt.setBoolean(index, x);
  }

  public void setByte(int index, byte x) throws SQLException {
    pstmt.setByte(index, x);
  }

  public void setShort(int index, short x) throws SQLException {
    pstmt.setShort(index, x);
  }

  public void setInt(int index, int x) throws SQLException {
    pstmt.setInt(index, x);
  }

  public void setLong(int index, long x) throws SQLException {
    pstmt.setLong(index, x);
  }

  public void setFloat(int index, float x) throws SQLException {
    pstmt.setFloat(index, x);
  }

  public void setDouble(int index, double x) throws SQLException {
    pstmt.setDouble(index, x);
  }

  public void setBigDecimal(int index, BigDecimal x) throws SQLException {
    pstmt.setBigDecimal(index, x);
  }

  public void setString(int index, String x) throws SQLException {
    pstmt.setString(index, x);
  }

  public void setBytes(int index, byte x[]) throws SQLException {
    pstmt.setBytes(index, x);
  }

  public void setDate(int index, Date x) throws SQLException {
    pstmt.setDate(index, x);
  }

  public void setTime(int index, Time x) throws SQLException {
    pstmt.setTime(index, x);
  }

  public void setTimestamp(int index, Timestamp x) throws SQLException {
    pstmt.setTimestamp(index, x);
  }

  public void setAsciiStream(int index, InputStream x, int length)
    throws SQLException {
    pstmt.setAsciiStream(index, x, length);
  }

  public void setUnicodeStream(int index, InputStream x, int length)
    throws SQLException {
    pstmt.setUnicodeStream(index, x, length);
  }

  public void setBinaryStream(int index, InputStream x, int length)
    throws SQLException {
    pstmt.setBinaryStream(index, x, length);
  }

  public void clearParameters() throws SQLException {
    pstmt.clearParameters();
  }

  public void setObject(int index, Object x, int target, int scale)
    throws SQLException {
    pstmt.setObject(index, x, target, scale);
  }

  public void setObject(int index, Object x, int target)
    throws SQLException {
    pstmt.setObject(index, x, target);
  }

  public void setObject(int index, Object x) throws SQLException {
    pstmt.setObject(index, x);
  }

  public boolean execute() throws SQLException {
    return pstmt.execute();
  }

public void setCharacterStream(int i, java.io.Reader r, int j) throws SQLException {
    pstmt.setCharacterStream(i,r,j);
}
public int getFetchDirection() throws SQLException {
    return pstmt.getFetchDirection();
}
public int executeBatch()[] throws SQLException {
    return pstmt.executeBatch();
}
public void setFetchSize(int i) throws SQLException {
    pstmt.setFetchSize(i);
}
public void clearBatch() throws SQLException {
    pstmt.clearBatch();
}
public void addBatch() throws SQLException {
    pstmt.addBatch();
}
public void addBatch(java.lang.String s) throws SQLException {
    pstmt.addBatch(s);
}
public void setRef(int i, java.sql.Ref r)  throws SQLException {
    pstmt.setRef(i,r);
}
public int getResultSetConcurrency() throws SQLException   {
    return pstmt.getResultSetConcurrency();
}
public void setClob(int i, java.sql.Clob c) throws SQLException {
    pstmt.setClob(i, c);
}
public void setFetchDirection(int i) throws SQLException {
    pstmt.setFetchDirection(i);
}
public void setDate(int i, java.sql.Date d, java.util.Calendar c) throws SQLException {
    pstmt.setDate(i, d, c);
}
public void setArray(int i, java.sql.Array a) throws SQLException {
    pstmt.setArray(i,a);
}
public void setBlob(int i, java.sql.Blob b) throws SQLException {
    pstmt.setBlob(i,b);
}
public int getFetchSize() throws SQLException {
    return pstmt.getFetchSize();
}
public void setTime(int i, java.sql.Time t , java.util.Calendar c) throws SQLException  {
    pstmt.setTime(i,t,c);
}
public java.sql.Connection getConnection() throws SQLException {
    return pstmt.getConnection();
}
public java.sql.ResultSetMetaData getMetaData() throws SQLException {
    return pstmt.getMetaData();
}
public int getResultSetType() throws SQLException {
    return pstmt.getResultSetType();
}

public void setTimestamp(int i , java.sql.Timestamp t, java.util.Calendar c) throws SQLException {
  pstmt.setTimestamp(i,t,c);
}

public void setNull(int i, int j, java.lang.String s) throws SQLException {
  pstmt.setNull(i,j,s);
}

}

