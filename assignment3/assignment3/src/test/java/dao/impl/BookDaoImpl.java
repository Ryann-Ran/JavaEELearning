package dao.impl;

import dao.BookDao;

/**
 * @author Ryann
 * @create 2023/10/7 - 19:39
 */
public class BookDaoImpl implements BookDao {
    private String databaseName;
    private int connectionNum;
    public BookDaoImpl() {}
    public BookDaoImpl (int connectionNum, String databaseName) {
        this.databaseName = databaseName;
        this.connectionNum = connectionNum;
    }
    public void save() {
        System.out.println("book dao save ...");
    }
}
