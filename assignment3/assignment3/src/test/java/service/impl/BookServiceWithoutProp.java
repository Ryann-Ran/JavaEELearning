package service.impl;

import dao.BookDao;
import service.BookService;

/**
 * @author Ryann
 * @create 2023/10/7 - 17:55
 */
public class BookServiceWithoutProp implements BookService {
    private BookDao userDao;
    public BookServiceWithoutProp() {}
    public BookServiceWithoutProp(BookDao bookDao) {
        this.userDao = bookDao;
    }

    public void save() {
        System.out.println("book service save ...");
        userDao.save();
    }
    public void setBookDao(BookDao bookDao) {
        this.userDao = bookDao;
    }
}
