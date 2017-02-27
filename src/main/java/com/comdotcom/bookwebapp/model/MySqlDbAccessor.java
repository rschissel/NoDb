/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comdotcom.bookwebapp.model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 *
 * @author Ryan Schissel
 */
public class MySqlDbAccessor implements DbAccessor {

    private Connection conn;

    //needs validation
    //consider custom exception
    @Override
    public void openConnection(String driverClass, String url, String userName, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }

    @Override
    public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    @Override
    public List<Map<String, Object>> findRecordsFor(String tableName, int maxRecords) throws SQLException {

        String sqlCmd = "";
        if (maxRecords > 0) {
            sqlCmd = "SELECT * FROM " + tableName + " LIMIT " + maxRecords;
        } else {
            sqlCmd = "SELECT * FROM" + tableName;
        }

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sqlCmd);

        List<Map<String, Object>> results = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        Map<String, Object> record = null;

        while (rs.next()) {
            record = new LinkedHashMap<>();
            for (int colNo = 1; colNo <= colCount; colNo++) {
                //long way
                //String colName = rsmd.getColumnName(colNo);
                //record.put(colName, rs.getObject(colNo));

                //short way
                record.put(rsmd.getColumnName(colNo), rs.getObject(colNo));
            }
            results.add(record);
        }
        return results;
    }

    @Override
    public void createNewRecord(String tableName, List<String> columnNames, List<Object> values) throws SQLException {
        // INSERT INTO author (author_name,date) VALUES(?,?,?)
        String sql = "INSERT INTO " + tableName + " ";
        StringJoiner sj = new StringJoiner(",", "(", ")");
        for (String col : columnNames) {
            sj.add(col);
        }
        sql += sj.toString();
        sj = new StringJoiner(",", "(", ")");
        sql += " VALUES ";
        for (Object val : values) {
            sj.add("?");
        }
        sql += sj.toString();
        PreparedStatement pstm = conn.prepareStatement(sql);
        for (Object val : values) {
            pstm.setObject(values.indexOf(val) + 1, val);
        }
        pstm.executeUpdate();
    }

    @Override
    public void deleteByColumnValue(String tableName, String whereClauseColumnName, Object whereClauseColumnValue) throws ClassNotFoundException, SQLException {
        PreparedStatement pstm = null;
            String sql = "DELETE FROM " + tableName + " WHERE " + whereClauseColumnName + " = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setObject(1, whereClauseColumnValue);
            pstm.executeUpdate();
    }
    @Override
    public void updateByColumnValue(String tableName, List<String> columnNames, List<Object> newValues, String whereClauseColumnName, Object whereClauseValue) throws SQLException {
        PreparedStatement pstm = null;
        // UPDATE author SET author_name = ?,author_date = ?  WHERE authorId = ?
        String sql = "UPDATE " + tableName + " SET ";
        StringJoiner sj = new StringJoiner("= ? ");
        
        for (String col : columnNames) {
            sj.add(col);
        }
        sql += sj.toString();
        sql += " = ?";
        sql += " WHERE " + whereClauseColumnName + " = ?";
        System.out.println(sql);
        pstm = conn.prepareStatement(sql);
        for (int i = 0; i < newValues.size(); i++) {
            pstm.setObject(i + 1, newValues.get(i));
        }
        pstm.setObject(newValues.size() + 1, whereClauseValue);
        pstm.executeUpdate();
    }

    public String removeEndingComma(String sql) {
        return sql.substring(0, sql.length() - 1);
    }

    public static void main(String[] args) throws Exception {
        DbAccessor db = new MySqlDbAccessor();

        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/book";
        String userName = "root";
        String password = "admin";
        db.openConnection(driverClassName, url, userName, password);

        List<String> columnNames = new ArrayList<>();
        List<Object> newValues = new ArrayList<>();
        columnNames.add("author_name");
        newValues.add("Luke Warm");
        String whereClauseColumnName = "author_id";
        Object whereClauseValue = 4;
        //db.updateByColumnValue("author", columnNames, newValues, whereClauseColumnName, whereClauseValue);

        db.deleteByColumnValue("author", whereClauseColumnName, whereClauseValue);
//        List<Map<String, Object>> records = db.findRecordsFor("author", 50);
//        db.closeConnection();
//
//        for (Map<String, Object> record : records) {
//            System.out.println(record);
//        }
    }

}
