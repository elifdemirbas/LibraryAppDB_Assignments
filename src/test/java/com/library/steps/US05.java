package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US05 {
    String actualMPGenre;

    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {
        String query = "select bc.name, count(*)\n" +
                "from book_borrow bb\n" +
                "         join books b on b.id = bb.book_id\n" +
                "         join book_categories bc on bc.id = b.book_category_id\n" +
                "group by name\n" +
                "order by 2 desc";

        DB_Util.runQuery(query);
        actualMPGenre = DB_Util.getFirstRowFirstColumn();

    }

    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String expectedMPGenre) {
        Assert.assertEquals(actualMPGenre,expectedMPGenre);
    }
}
