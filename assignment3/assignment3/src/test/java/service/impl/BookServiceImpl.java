package service.impl;

import dao.BookDao;
import service.BookService;

/**
 * @author Ryann
 * @create 2023/10/7 - 19:39
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao;
    public BookServiceImpl() {}
    public BookServiceImpl (BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void save() {
        System.out.println("book service save ...");
        bookDao.save();
    }
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
