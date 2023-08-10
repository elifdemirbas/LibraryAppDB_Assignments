package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.BorrowedBooksPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class US07 {

    BorrowedBooksPage borrowedBooksPage =new BorrowedBooksPage();
    BookPage bookPage = new BookPage();
    @When("the user clicks {string}")
    public void the_user_clicks_borrow_book(String borrowBook) {



    }
    @Then("verify that book is shown in {string} page")
    public void verify_that_book_is_shown_in_page(String borrowingBooksPage) {

    }
    @Then("verify logged student has same book in database")
    public void verify_logged_student_has_same_book_in_database() {

    }
}
