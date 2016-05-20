package models.request;

import org.junit.Test;
import play.data.Form;
import static org.fest.assertions.Assertions.assertThat;
import java.util.HashMap;
import java.util.Map;

import static play.data.Form.form;

public class ResultPostRequestTest {

// OKケース
    /**
     * 正しいId形式
     */
    @Test
    public void testValidationToRightParam() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", "test_v");
        Form<ResultPostRequest> form = form(ResultPostRequest.class).bind(map);
        assertThat(form.hasErrors()).isFalse();
    }

    @Test
    public void testValidationToRightParamWithUnderBar() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", "testv");
        Form<ResultPostRequest> form = form(ResultPostRequest.class).bind(map);
        assertThat(form.hasErrors()).isFalse();
    }

    @Test
    public void testValidationToRightParamWithNumber(){
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("name", "12345");
    	Form<ResultPostRequest> form = form(ResultPostRequest.class).bind(map);
    	assertThat(form.hasErrors()).isFalse();
    }

    @Test
    public void testValidationToRightParamWithNumberAndUnderBarAndEngWord(){
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("name", "test1_1");
    	Form<ResultPostRequest> form = form(ResultPostRequest.class).bind(map);
    	assertThat(form.hasErrors()).isFalse();
    }

    // 要実装
    // アンダーバーなし（testv）
    // 数字のみ
    // 数値、英数、アンダーバー混合

// NGケース
    /**
     * ハイフンつき
     */
    @Test
    public void testValidationToWrongParamWithHyphen() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "test-v");
        Form<ResultPostRequest> form = form(ResultPostRequest.class).bind(map);
        assertThat(form.hasErrors()).isTrue();
    }

    @Test
    public void testValidationToWrongParamWithForbittenWord(){
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("name", "test>");
    	Form<ResultPostRequest> form = form(ResultPostRequest.class).bind(map);
    	assertThat(form.hasErrors()).isTrue();
    }

    @Test
    public void testCalidationToWrongParamWithJpnWord(){
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("name", "てすと");
    	Form<ResultPostRequest> form = form(ResultPostRequest.class).bind(map);
    	assertThat(form.hasErrors()).isTrue();
    }


    // 要実装
    // 禁止文字">"を含む
    // ひらがなを含む
}