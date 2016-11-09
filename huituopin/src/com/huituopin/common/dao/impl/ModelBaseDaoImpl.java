package com.huituopin.common.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.huituopin.common.utils.Analytical;

/**
 * jdbc连接元数据库
 */
public class ModelBaseDaoImpl {

    public Connection conn;

    public PreparedStatement pstmt;

    public Statement ps;

    public ResultSet rs;

    /**
     * 得到connection
     * 
     * @return
     * @throws Exception
     */
    public Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(Analytical.readjdbcFile("datasource.driverClassNameDB2"));
            String url = Analytical.readjdbcFile("datasource.url2");
            String user = Analytical.readjdbcFile("datasource.usernameDB2");
            String password = Analytical.readjdbcFile("datasource.passwordDB2");
            try {
                conn = DriverManager.getConnection(url, user, password);
                // conn = DriverManager
                // .getConnection("jdbc:oracle:thin:@localhost:1521:orcl?user=userY&password=userY");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭连接
     */
    public void closeAll() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
