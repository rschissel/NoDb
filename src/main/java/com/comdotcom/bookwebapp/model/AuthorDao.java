/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comdotcom.bookwebapp.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 *
 * @author Ryan Schissel
 */
public class AuthorDao implements IAuthorDao {
    private DbAccessor db;
    private String driverClass;
    private String url;
    private String userName;
    private String password;

    public AuthorDao(DbAccessor db, String driverClass, String url, String userName, String password) {
        this.db = db;
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.password = password;
    } 
    
    @Override
    public List<Author> getAuthorList(String tableName, int maxRecords) throws ClassNotFoundException, SQLException{
        
        List<Author> authorList = new ArrayList<>();
        db.openConnection(driverClass, url, userName, password);
        List<Map<String, Object>> rawData = db.findRecordsFor(tableName, maxRecords);
        db.closeConnection();
        
        for (Map<String, Object> recData  : rawData){
            Author author = new Author();
            
            author.setAuthorId((Integer) recData.get("author_id"));
            Object objName = recData.get("author_name");
            String name = objName != null ? objName.toString() : "";
            author.setAuthorName(name);
            
            Object objDate = recData.get("date_added");
            Date dateAdded = objDate !=null ? (Date) objDate : null;
            author.setDateAdded(dateAdded);
            authorList.add(author);
        }
        return authorList;
    }
    
    @Override
    public DbAccessor getDb() {
        return db;
    }

    @Override
    public void setDb(DbAccessor db) {
        this.db = db;
    }

    @Override
    public String getDriverClass() {
        return driverClass;
    }

    @Override
    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    
    public static void main(String[] args) throws Exception{
        DbAccessor db = new MySqlDbAccessor();
        
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/book";
        String userName = "root";
	String password = "admin";
        List<Object> values = new ArrayList<>();
        values.add("Ronda McChickenfri-berger");
        values.add(new Date());
        IAuthorDao dao = new AuthorDao(db, driverClassName, url, userName, password);
        dao.addAuthor("author", Arrays.asList("author_name", "date_added"), values);
        List<Author> authors = dao.getAuthorList("author", 50);
        
        for (Author a :authors){
            System.out.println(a);
        }
     
    }

    @Override
    public void removeAuthorById(String tableName, String pkColName, Object pkValue) throws ClassNotFoundException, SQLException{
      db.openConnection(driverClass, url, userName, password);
      db.deleteByColumnValue(tableName, pkColName, pkValue);
      db.closeConnection();
    }

    @Override
    public void modifyAuthorInfo(String tableName, List<String> newAuthorInfoSubjects, List<Object> newAuthorInfoValues, String oldAuthorInfoSubject, Object oldAuthorInfoValue) throws ClassNotFoundException, SQLException{
        db.openConnection(driverClass, url, userName, password);
        db.updateByColumnValue(tableName, newAuthorInfoSubjects, newAuthorInfoValues, oldAuthorInfoSubject, oldAuthorInfoValue);
        db.closeConnection();
    }
public void addAuthor(String tableName, List<String> columnNames, List<Object> values) throws ClassNotFoundException, SQLException{
 db.openConnection(driverClass, url, userName, password);
 db.createNewRecord(tableName, columnNames, values);
 db.closeConnection();
}
   

    

    
    
}
