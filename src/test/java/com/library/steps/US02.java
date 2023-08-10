package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US02 {
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    String actualBorrowedBookNumber;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String userType) {
        loginPage.login(userType);
    }

    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {
        actualBorrowedBookNumber = dashBoardPage.borrowedBooksNumber.getText();
    }

    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
        DB_Util.runQuery("SELECT COUNT(*) FROM book_borrow\n" +
                "WHERE is_returned=0");

        String expectedBorrowedBookNumber = DB_Util.getFirstRowFirstColumn();

        Assert.assertEquals(actualBorrowedBookNumber,expectedBorrowedBookNumber);

    }

}
