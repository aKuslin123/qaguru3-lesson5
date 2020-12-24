import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;

public class IssueTest {
    private final static String USER = "aKuslin123";
    private final static String PASS = "d8W4y8mg";
    private final static String ISSUE = "Issue1";

    @Test
    public void createIssue() {
        open("https://github.com");

        //авторизация
        $("a[href='/login']").click();
        sleep(1000);
        $("#login_field").sendKeys(USER);
        $("#password").sendKeys(PASS);
        $(byValue("Sign in")).click();

        //перехожу в репозиторий
        $(byText("qaguru3-lesson5")).click();
        $("input[id='empty-setup-clone-url']").shouldHave(value("https://github.com/aKuslin123/qaguru3-lesson5.git"));

        //перехожу в issue
        $("[data-content='Issues']").click();

        //создаю issue
        $x("//span[contains(text(), 'New issue')]").click();
        $("#issue_title").sendKeys(ISSUE);

        //назначаю issue
        $(byText("Assignees")).click();
        $$(".js-username").findBy(text("aKuslin123")).click();
        $("#assignee-filter-field").pressEscape();

        //выбираю тег для issue
        $(byText("Labels")).click();
        $$(".name").findBy(text("bug")).click();
        $("#label-filter-field").pressEscape();

        //жму кнопку создания issue
        $(byText("Submit new issue")).click();

        //Закрываю Issue
        $(byText("Close issue")).click();
    }
}
