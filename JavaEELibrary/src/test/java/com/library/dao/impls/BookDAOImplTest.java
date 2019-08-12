package com.library.dao.impls;

import com.library.dao.interfaces.BookDAO;
import com.library.impls.book.BookImpl;
import com.library.interfaces.book.Book;
import org.junit.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.library.dao.impls.DBUtils.updateBookTable;
import static com.library.db.ConnectionDB.connection;

public class BookDAOImplTest {
    private BookDAO bookDAO = new BookDAOImpl();

    @BeforeClass
    public static void setUpConnection() {
        DBUtils.setUpConnection();
    }

    @AfterClass
    public static void afterClassCloseConnections() {
        DBUtils.updateBookTable();
        DBUtils.closeConnections();
    }

    @Before
    public void beforeUpdateTableBook() {
        updateBookTable();
    }

    @Test
    public void getById() {
        this.bookGenerator("testBook", "testAuthor", 15, "testResource");
        int id = 1;
        Book expectedBook  = new BookImpl(id, "testBook", "testAuthor", "testResource", 15);
        Book actualBook = null;
        actualBook = bookDAO.getById(id);

        Assert.assertEquals("The expected and actual books are not equal!", expectedBook, actualBook);
        System.out.println("GetById test: ");
        System.out.println("Expected: " + expectedBook);
        System.out.println("Actual: " + actualBook);
    }

    @Test
    public void getByName() {
        String name = "bookName";
        this.bookGenerator(name, "testAuthor", 15, "testResource");

        Book expectedBook  = new BookImpl(1, name, "testAuthor", "testResource", 15);

        Book actualBook = null;
        actualBook = bookDAO.getByName(name);

        Assert.assertEquals("The expected and actual books are not equal!", expectedBook, actualBook);
        System.out.println("GetByName test: ");
        System.out.println("Expected: " + expectedBook);
        System.out.println("Actual: " + actualBook);
    }

    @Test
    public void getByAuthor() {
        final int id1 = 1;
        final int id2 = 2;
        final String name1 = "testBook1";
        final String name2 = "testBook2";
        final String authorName1 = "testAuthor1";
        final String authorName2 = "testAuthor1";
        final String resourcePath1 = "testPath1";
        final String resourcePath2 = "testPath2";
        final int pages1 = 15;
        final int pages2 = 16;

        bookGenerator(name1, authorName1, pages1, resourcePath1);
        bookGenerator(name2, authorName2, pages2, resourcePath2);

        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new BookImpl(id1, name1, authorName1, resourcePath1, pages1));
        expectedBooks.add(new BookImpl(id2, name2, authorName2, resourcePath2, pages2));

        List<Book> actualBooks = bookDAO.getByAuthor("testAuthor1");

        Assert.assertEquals("The expected books and actual books are not equal!", expectedBooks, actualBooks);
        System.out.println("GetByAuthor test:");
        System.out.println("Expected books:");
        expectedBooks.forEach(System.out::println);
        System.out.println("Actual books:");
        actualBooks.forEach(System.out::println);
    }

    @Test
    public void getIssuedBooks() {
    }

    @Test
    public void getBooks() {
        final int id1 = 1;
        final int id2 = 2;
        final String name1 = "testBook1";
        final String name2 = "testBook2";
        final String authorName1 = "testAuthor1";
        final String authorName2 = "testAuthor1";
        final String resourcePath1 = "testPath1";
        final String resourcePath2 = "testPath2";
        final int pages1 = 15;
        final int pages2 = 16;

        bookGenerator(name1, authorName1, pages1, resourcePath1);
        bookGenerator(name2, authorName2, pages2, resourcePath2);

        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new BookImpl(id1, name1, authorName1, resourcePath1, pages1));
        expectedBooks.add(new BookImpl(id2, name2, authorName2, resourcePath2, pages2));

        List<Book> actualBooks = bookDAO.getBooks();

        Assert.assertEquals("The expected books and actual books are not equal!", expectedBooks, actualBooks);
        System.out.println("GetBooks test:");
        System.out.println("Expected books:");
        expectedBooks.forEach(System.out::println);
        System.out.println("Actual books:");
        actualBooks.forEach(System.out::println);
    }

    @Test
    public void deleteById() {
        this.bookGenerator("testBook", "testAuthor", 15, "testResource");
        int expected = 1;
        List<Book> books = bookDAO.getBooks();
        List<Book> expectedBooks = Collections.emptyList();
        int actual = bookDAO.deleteById(1);
        List<Book> actualBooks = bookDAO.getBooks();
        System.out.println("Table book before deleteById operation:" + books);
        Assert.assertEquals("The note with id = 1 is not deleted!", expectedBooks, actualBooks);
        System.out.println("DeleteById:");
        System.out.println("Expected: " + expectedBooks);
        System.out.println("Actual: " + actualBooks);
    }

    private void bookGenerator(String name, String author, int pages, String resourcePath) {
        final String query = "insert into book (name, authorName, pages, resourcePath)\n" +
                "values (?, ?, ?, ?);";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, author);
            preparedStatement.setInt(3, pages);
            preparedStatement.setString(4, resourcePath);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
