/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comdotcom.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ryan Schissel
 */
public interface DbAccessor {

    void closeConnection() throws SQLException;

    //SKIP #4 ON HOMEWORK (
    List<Map<String, Object>> findRecordsFor(String tableName, int maxRecords) throws SQLException;

    //needs validation
    //consider custom exception
    void openConnection(String driverClass, String url, String userName, String password) throws ClassNotFoundException, SQLException;
    
    void deleteByColumnValue(String tableName, String whereClauseColumnName, Object whereClauseColumnValue) throws ClassNotFoundException, SQLException ;
    
    void createNewRecord(String tableName, List<String> columnNames, List<Object> values) throws SQLException;

    public abstract void updateByColumnValue(String tableName, List<String> columnNames, List<Object> newValues, String whereClauseColumnNames, Object whereClauseValue) throws SQLException;
}
