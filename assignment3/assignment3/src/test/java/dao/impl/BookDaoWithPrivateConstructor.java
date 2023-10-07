package dao.impl;

import dao.BookDao;

/**
 * @author Ryann
 * @create 2023/10/7 - 17:33
 */
public class BookDaoWithPrivateConstructor implements BookDao {
    private BookDaoWithPrivateConstructor() {

    }
    public void save() {
        System.out.println("book dao save ...");
    }
}
