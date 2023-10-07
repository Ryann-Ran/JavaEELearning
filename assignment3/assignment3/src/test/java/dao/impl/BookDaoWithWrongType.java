package dao.impl;

import dao.BookDao;

/**
 * @author Ryann
 * @create 2023/10/7 - 19:05
 */
public class BookDaoWithWrongType implements BookDao {
    private String databaseName;
    private String connectionNum;
    public BookDaoWithWrongType() {}
    public BookDaoWithWrongType (String databaseName, String connectionNum) {
        this.databaseName = databaseName;
        this.connectionNum = connectionNum;
    }
    public void save() {
        System.out.println("book dao save ...");
    }
}
