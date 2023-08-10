package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class US06 {

    BookPage bookPage = new BookPage();

    String expectedBookName;
    String expectedISBN;
    String expectedYear;
    String expectedAuthor;
    String expectedBookCategory;


    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        bookPage.addBook.click();
    }

    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String expectedBookName) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), (10));
        wait.until(ExpectedConditions.visibilityOf(bookPage.bookName));

        bookPage.bookName.sendKeys(expectedBookName);

        this.expectedBookName = expectedBookName;
    }

    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String expectedISBN) {
        bookPage.isbn.sendKeys(expectedISBN);
        this.expectedISBN = expectedISBN;
    }

    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String expectedYear) {
        bookPage.year.sendKeys(expectedYear);
        this.expectedYear = expectedYear;
    }

    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String expectedAuthor) {
        bookPage.author.sendKeys(expectedAuthor);
        this.expectedAuthor = expectedAuthor;
    }

    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String expectedBookCategory) {
        Select select = new Select(bookPage.categoryDropdown);
        select.selectByVisibleText(expectedBookCategory);
        this.expectedBookCategory = expectedBookCategory;
    }

    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
        bookPage.saveChanges.click();
    }

    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String expectedVerifyMsg) {
        String actualVerifyMsg = bookPage.toastMessage.getText();
        Assert.assertEquals(expectedVerifyMsg, actualVerifyMsg);
    }

    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String expectedBookName) {
        DB_Util.runQuery("SELECT b.NAME as bookName, ISBN, YEAR, AUTHOR, bc.name as categoryName FROM books b JOIN book_categories bc on bc.id = b.book_category_id\n" +
                "order by added_date desc ;");


        String actualBookName = DB_Util.getCellValue(1, 1);
        String actualISBN = DB_Util.getCellValue(1, 2);
        String actualYear = DB_Util.getCellValue(1, 3);
        String actualAuthor = DB_Util.getCellValue(1, 4);
        String actualCategoryName = DB_Util.getCellValue(1, 5);


        Assert.assertEquals(actualBookName, expectedBookName);
        Assert.assertEquals(actualISBN, expectedISBN);
        Assert.assertEquals(actualYear, expectedYear);
        Assert.assertEquals(actualAuthor, expectedAuthor);
        Assert.assertEquals(actualCategoryName, expectedBookCategory);


    }
}
