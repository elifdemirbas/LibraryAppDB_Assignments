package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;

public class US04 {

    BookPage bookPage = new BookPage();
    String bookName;

    String actualBookName;
    String actualISBN;
    String actualYear;
    String actualAuthor;
    String actualBookCategory;
    String actualDescription;

    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String bookName) {
        this.bookName = bookName;
        System.out.println("bookName = " + bookName);
        bookPage.search.sendKeys(bookName);

    }

    @And("the user clicks edit book button")
    public void and_the_user_clicks_edit_book_button(){
        bookPage.editBook(bookName).click();


        actualBookName = bookPage.bookName.getAttribute("value");
        actualISBN = bookPage.isbn.getAttribute("value");
        actualYear = bookPage.year.getAttribute("value");
        actualAuthor = bookPage.author.getAttribute("value");

        Select select = new Select(bookPage.categoryDropdown);
        actualBookCategory = select.getFirstSelectedOption().getText();

        actualDescription = bookPage.description.getAttribute("value");
    }

    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {
        String query = "select b.name as bookName, b.isbn, b.year, b.author, bc.name as categoryName, b.description\n" +
                "from books b\n" +
                "         join book_categories bc on bc.id = b.book_category_id\n" +
                "where b.name = '"+bookName+ "'\n" +
                "order by b.isbn desc;";

        DB_Util.runQuery(query);

       // Map<String, String> bookInfo = DB_Util.getRowMap(1);

        String expectedBookName = DB_Util.getCellValue(1, 1);
        String expectedISBN = DB_Util.getCellValue(1, 2);
        String expectedYear = DB_Util.getCellValue(1, 3);
        String expectedAuthor = DB_Util.getCellValue(1, 4);
        String expectedBookCategory = DB_Util.getCellValue(1, 5);
        String expectedDescription = DB_Util.getCellValue(1,6);

        Assert.assertEquals(expectedBookName,actualBookName);
        Assert.assertEquals(expectedISBN,actualISBN);
        Assert.assertEquals(expectedYear,actualYear);
        Assert.assertEquals(expectedAuthor,actualAuthor);
        Assert.assertEquals(expectedBookCategory,actualBookCategory);
        Assert.assertEquals(expectedDescription,actualDescription);


    }

}
