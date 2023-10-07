import service.BookService;

/**
 * @author Ryann
 * @create 2023/10/7 - 11:18
 */
public class App {
    public static void main (String[] args) throws MyIOCException {
        //  创建IoC容器
        MyApplicationContext ctx = new MyApplicationContext("applicationContext.xml");
        //  获取bean（根据bean配置id获取）
        BookService bookService = (BookService) ctx.getBean("bookService");
        bookService.save();
    }
}
