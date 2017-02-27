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
public interface IAuthorDao {

    List<Author> getAuthorList(String tableName, int maxRecords) throws ClassNotFoundException, SQLException;

    DbAccessor getDb();

    String getDriverClass();

    String getPassword();

    String getUrl();

    String getUserName();

    void setDb(DbAccessor db);

    void setDriverClass(String driverClass);

    void setPassword(String password);

    void setUrl(String url);

    void setUserName(String userName);
    void addAuthor(String tableName, List<String> columnNames, List<Object> values) throws ClassNotFoundException, SQLException;
    void removeAuthorById(String tableName, String pkColName, Object pkValue) throws ClassNotFoundException, SQLException;
    void modifyAuthorInfo(String tableName, List<String> newAuthorInfoSubjects, List<Object> newAuthorInfoValues, String oldAuthorInfoSubject, Object oldAuthorInfoValue) throws ClassNotFoundException, SQLException;
    }
