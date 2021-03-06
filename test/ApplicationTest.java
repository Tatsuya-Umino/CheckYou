import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import views.html.*;

import com.fasterxml.jackson.databind.JsonNode;

import models.request.ResultPostRequest;

import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.Form;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;

import static play.data.Form.form;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {


    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }


    public void renderTemplate() {
    	Form<ResultPostRequest> form = form(ResultPostRequest.class);
        Content html = views.html.check.index.render("testTitle", "testMessage", form);
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Your new application is ready.");
    }


}
