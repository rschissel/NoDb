/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comdotcom.bookwebapp.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ryan Schissel
 */
public class AuthorService {
    private List<Author> authorList = new ArrayList<>();
    private IAuthorDao dao;
    public List<Author> getAllAuthors(String tableName, int maxRecords) throws ClassNotFoundException, SQLException{
//    Author a1 = new Author(1,"Ronda McChiken-Berger", new Date());
//    Author a2 = new Author(2,"Jimi Johnson", new Date());
//    Author a3 = new Author(3,"Roger Rogers", new Date());
//    
//    authorList.add(0, a1);
//    authorList.add(0, a2);
//    authorList.add(0, a3);

        return dao.getAuthorList(tableName, maxRecords);
    }
    public void removeAuthorById(String tableName, String pkColName, Object pkValue) throws ClassNotFoundException, SQLException{
        dao.removeAuthorById(tableName, pkColName, pkValue);
    }
    public void modifyAuthorInfo(String tableName, List<String> newAuthorInfoSubjects, List<Object> newAuthorInfoValues, String oldAuthorInfoSubject, Object oldAuthorInfoValue) throws ClassNotFoundException, SQLException{
        dao.modifyAuthorInfo(tableName, newAuthorInfoSubjects, newAuthorInfoValues, oldAuthorInfoSubject, oldAuthorInfoValue);
    }
    public void addAuthor(String tableName, List<String> columnNames, List<Object> values) throws ClassNotFoundException, SQLException{
        dao.addAuthor(tableName, columnNames, values);
 }
    public AuthorService(IAuthorDao dao) {
        this.dao = dao;
    }
    
    public List<Author> getAuthors(){
        return authorList;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public IAuthorDao getDao() {
        return dao;
    }

    public void setDao(IAuthorDao dao) {
        this.dao = dao;
    }
    public static void main(String[] args) throws Exception{
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/book";
        String userName = "root";
	String password = "admin";
        
        AuthorService as = new AuthorService(
                new AuthorDao(
                        new MySqlDbAccessor(), driverClassName, url, userName,password
                )
        );
        List<String> columns = new ArrayList<>();
        columns.add("author_name");
        List<Object> values = new ArrayList<>();
        values.add("Ronda McChekin-Freiberger");
        as.modifyAuthorInfo("author", columns, values, "author_name" , "Ronda McChickenFri-Berger");
        
        List<Author> authors = as.getAllAuthors("author", 50);
        
        for (Author a : authors){
            System.out.println(a);
        }
    }
}
