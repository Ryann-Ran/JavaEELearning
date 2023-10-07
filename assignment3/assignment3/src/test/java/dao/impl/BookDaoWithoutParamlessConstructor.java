package dao.impl;

import dao.BookDao;

/**
 * @author Ryann
 * @create 2023/10/7 - 17:45
 */
public class BookDaoWithoutParamlessConstructor implements BookDao {
    private String databaseName;
    private String connectionNum;
    public BookDaoWithoutParamlessConstructor (String databaseName, String connectionNum) {
        this.databaseName = databaseName;
        this.connectionNum = connectionNum;
    }
    public void save() {
        System.out.println("book dao save ...");
    }
}
