package controllers.api;

import com.avaje.ebean.Ebean;
import apps.FakeApp;
import org.junit.Test;
import play.mvc.Result;
import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.*;
import static play.test.Helpers.*;

public class ChecksAPIControllerTest extends FakeApp{

    // 要実装
    // 取得したID=1のデータにname, resultの文言が含まれる
    // コンテンツタイプは"application/json"
	@Test
	public void testCheck001(){
		prepareSaveData();
		Result result = route(fakeRequest(GET, "/v1/check/1.json"));
		assertThat(status(result)).isEqualTo(OK);
    	assertThat(contentType(result)).isEqualTo("application/json");
    	assertThat(charset(result)).isEqualTo("utf-8");
    	assertThat(contentAsString(result)).contains("test_a");
    	assertThat(contentAsString(result)).contains("test_aさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。");
	}

    // 要実装
    // データ一覧取得API, 指定したページ（１ページ目）の１件目、５件目データの文字列（name, result）が含まれていること、６件目のデータの文言が含まれていないこと
	@Test
	public void testGetList001(){
		prepareSaveData();
		Result result = route(fakeRequest(GET, "/v1/checks/1.json"));
		assertThat(status(result)).isEqualTo(OK);
    	assertThat(contentType(result)).isEqualTo("application/json");
    	assertThat(charset(result)).isEqualTo("utf-8");
    	assertThat(contentAsString(result)).contains("test_a");
    	assertThat(contentAsString(result)).contains("test_aさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。");
    	assertThat(contentAsString(result)).contains("test_e");
    	assertThat(contentAsString(result)).contains("test_eさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。");
    	assertThat(contentAsString(result)).doesNotContain("test_f");
	}

	@Test
    public void testGetList002() throws Exception {
      // 要実装
      // データ一覧取得API, 指定したページ（３ページ目）の結果がエラー値であること、１，２ページ目のデータの文言（name, result）が含まれていないこと
    	prepareSaveData();
		Result result = route(fakeRequest(GET, "/v1/checks/3.json"));
		assertThat(status(result)).isEqualTo(400);
		assertThat(contentType(result)).isEqualTo("application/json");
    	assertThat(charset(result)).isEqualTo("utf-8");
    	assertThat(contentAsString(result)).doesNotContain("test_a");
    	assertThat(contentAsString(result)).doesNotContain("test_aさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。");
    	assertThat(contentAsString(result)).doesNotContain("test_e");
    	assertThat(contentAsString(result)).doesNotContain("test_eさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。");
    	assertThat(contentAsString(result)).doesNotContain("test_f");
    }

    private void prepareSaveData() {
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `checks` (`id`, `name`, `result`, `created`, `modified`) VALUES ('1',  'test_a',  'test_aさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:11', '2013-08-20 12:34:56');"));
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `checks` (`id`, `name`, `result`, `created`, `modified`) VALUES ('2',  'test_b',  'test_bさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:12', '2013-08-20 12:34:56');"));
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `checks` (`id`, `name`, `result`, `created`, `modified`) VALUES ('3',  'test_c',  'test_cさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:13', '2013-08-20 12:34:56');"));
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `checks` (`id`, `name`, `result`, `created`, `modified`) VALUES ('4',  'test_d',  'test_dさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:14', '2013-08-20 12:34:56');"));
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `checks` (`id`, `name`, `result`, `created`, `modified`) VALUES ('5',  'test_e',  'test_eさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:15', '2013-08-20 12:34:56');"));
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `checks` (`id`, `name`, `result`, `created`, `modified`) VALUES ('6',  'test_f',  'test_fさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:16', '2013-08-20 12:34:56');"));
    }
}