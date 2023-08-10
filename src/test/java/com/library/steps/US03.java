package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class US03 {

    BookPage bookPage = new BookPage();
    List<String> actualBookCategory = new ArrayList<>();

    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        new DashBoardPage().navigateModule(moduleName);

    }

    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {
//        Select select = new Select(bookPage.mainCategoryElement);
//        List<WebElement> options = select.getOptions();
//
//        for (int i = 1; i < options.size(); i++) {
//            actualBookCategory.add(options.get(i).getText());
//        }

//         actualBookCategory = BrowserUtil.getElementsText(options); // i used the other one bc it should start from index 1 to get rid of "ALL" option

        actualBookCategory = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        System.out.println("actualBookCategory = " + actualBookCategory);

        // EXCLUDE ALL FROM UI
        actualBookCategory.remove(0);
        System.out.println("----AFTER EXCLUDE ALL FROM LIST ----");
        System.out.println("actualBookCategory = " + actualBookCategory);


    }

    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        DB_Util.runQuery("SELECT NAME FROM book_categories");

        List<String> expectedBookCategory = DB_Util.getColumnDataAsList(1);
        System.out.println("expectedBookCategory = " + expectedBookCategory);

        Assert.assertEquals(expectedBookCategory, actualBookCategory);


    }
}
