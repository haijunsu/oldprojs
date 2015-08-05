

package com.htyz.navy.sql;
import java.sql.*;
import java.util.Calendar;
import java.math.BigDecimal;

public class PooledStatement implements java.sql.Statement {

  protected Statement stmt;

  String lastQuerySQL;
  String lastUpdateSQL;

  protected PooledStatement() {
  }

  public PooledStatement(Statement stmt) {
    this.stmt = stmt;
  }

  public void close() throws SQLException {
    stmt.setMaxRows(0);
  }

  public Statement getStatement() {
    return stmt;
  }

  public String dumpInfo() {
    String LS = System.getProperty("line.separator");
    String report = "\t\t\tPooledStatement: " + this.toString()+ LS;
    report += "\t\t\t\t Last Query SQL: "+ lastQuerySQL+ LS;
    report += "\t\t\t\t Last Update SQL: "+ lastUpdateSQL+ LS;
    return report;
  }
  //
  //  Methods in Statement
  //

  public ResultSet executeQuery(String sql) throws SQLException {
    lastQuerySQL = sql;
    return stmt.executeQuery(sql);
  }

  public int executeUpdate(String sql) throws SQLException {
    lastUpdateSQL = sql;
    return stmt.executeUpdate(sql);
  }

  public int getMaxFieldSize() throws SQLException {
    return stmt.getMaxFieldSize();
  }

  public void setMaxFieldSize(int max) throws SQLException {
    stmt.setMaxFieldSize(max);
  }

  public int getMaxRows() throws SQLException {
    return stmt.getMaxRows();
  }

  public void setMaxRows(int max) throws SQLException {
    stmt.setMaxRows(max);
  }

  public void setEscapeProcessing(boolean enable) throws SQLException {
    stmt.setEscapeProcessing(enable);
  }

  public int getQueryTimeout() throws SQLException {
    return stmt.getQueryTimeout();
  }

  public void setQueryTimeout(int seconds) throws SQLException {
    stmt.setQueryTimeout(seconds);
  }

  public void cancel() throws SQLException {
    stmt.cancel();
  }

  public SQLWarning getWarnings() throws SQLException {
    return stmt.getWarnings();
  }

  public void clearWarnings() throws SQLException {
    stmt.clearWarnings();
  }

  public void setCursorName(String name) throws SQLException {
    stmt.setCursorName(name);
  }

  public boolean execute(String sql) throws SQLException {
    return stmt.execute(sql);
  }

  public ResultSet getResultSet() throws SQLException {
    return stmt.getResultSet();
  }

  public int getUpdateCount() throws SQLException {
    return stmt.getUpdateCount();
  }

  public boolean getMoreResults() throws SQLException {
    return stmt.getMoreResults();
  }
public void setCharacterStream(int i, java.io.Reader r, int j) throws SQLException {
    setCharacterStream(i,r,j);
}
public int getFetchDirection() throws SQLException {
    return stmt.getFetchDirection();
}
public int executeBatch()[] throws SQLException {
    return stmt.executeBatch();
}
public void setFetchSize(int i) throws SQLException {
    stmt.setFetchSize(i);
}
public void clearBatch() throws SQLException {
    stmt.clearBatch();
}
public void addBatch(java.lang.String s) throws SQLException {
    stmt.addBatch(s);
}
public int getResultSetConcurrency() throws SQLException   {
    return stmt.getResultSetConcurrency();
}
public void setFetchDirection(int i) throws SQLException {
    stmt.setFetchDirection(i);
}
public int getFetchSize() throws SQLException {
    return stmt.getFetchSize();
}
public java.sql.Connection getConnection() throws SQLException {
    return stmt.getConnection();
}
public int getResultSetType() throws SQLException {
    return stmt.getResultSetType();
}


} 