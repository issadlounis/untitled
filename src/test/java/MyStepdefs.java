import org.junit.Assert;

public class MyStepdefs {
    int result;
    @io.cucumber.java.en.Given("I have a calculator")
    public void iHaveACalculator() {
    }

    @io.cucumber.java.en.When("I add {int} and {int}")
    public void iAddAnd(int arg0, int arg1) {
        result = arg0 + arg1;
    }

    @io.cucumber.java.en.Then("The result should be {int}")
    public void theResultShouldBe(int arg0) {
        Assert.assertEquals(arg0, result);
    }
}
