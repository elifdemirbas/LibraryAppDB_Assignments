package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class UserStepDefs {
    String actualUserCount;

    List<String> allIDs;


    @Given("Establish the database connection")
    public void establish_the_database_connection() {
      //  DB_Util.createConnection();
        System.out.println("------------------------------------------------------");
        System.out.println("------CONNECTION WILL BE DONE WITH BEFORE HOOK--------");
        System.out.println("------------------------------------------------------");
    }
    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
        String query = "select count(id) from users";
        DB_Util.runQuery(query);

        actualUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(actualUserCount);

    }
    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        String query = "select count(distinct id) from users";
        DB_Util.runQuery(query);

        String expectedUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(expectedUserCount);

        Assert.assertEquals(expectedUserCount,actualUserCount);

        //Close conn
       // DB_Util.destroy();
        System.out.println("-------------------------------------------------------");
        System.out.println("------CONNECTION WILL BE CLOSED WITH AFTER HOOK--------");
        System.out.println("-------------------------------------------------------");

    }

    //US01-2
    List<String> actualColumnNames;
    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
        DB_Util.runQuery("SELECT * FROM users");
        actualColumnNames = DB_Util.getAllColumnNamesAsList();


    }

    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> expectedColumnNames) {
        Assert.assertEquals(actualColumnNames,expectedColumnNames);
    }

}
