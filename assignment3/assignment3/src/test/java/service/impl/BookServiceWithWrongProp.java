package service.impl;

import dao.BookDao;
import service.BookService;

/**
 * @author Ryann
 * @create 2023/10/7 - 19:52
 */
public class BookServiceWithWrongProp implements BookService {
    private BookDao userDao;
    public BookServiceWithWrongProp() {}
    public BookServiceWithWrongProp (BookDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        System.out.println("book service save ...");
        userDao.save();
    }
    public void setUserDao(BookDao userDao) {
        this.userDao = userDao;
    }
}
