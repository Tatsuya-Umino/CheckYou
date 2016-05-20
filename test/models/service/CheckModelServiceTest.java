package models.service;

import com.avaje.ebean.Ebean;
import apps.FakeApp;
import models.entity.Check;
import play.libs.F.Option;

import org.junit.Test;
import java.util.*;

import static play.libs.F.*;
import static org.fest.assertions.Assertions.assertThat;

public class CheckModelServiceTest extends FakeApp {

    // 正常系（Some）：1件のレコードから1つ取り出す
    @Test
    public void testFindByIdTo1ReturnSome() throws Exception {
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `checks` (`id`, `name`, `result`, `created`, `modified`) VALUES ('1',  'test_t',  'test_tさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:56', '2013-08-20 12:34:56');"));
        Option<Check> model = new CheckModelService().findById(1L);
        assertThat(model.getClass()).isEqualTo(Some.class);
        assertThat(model.get().getId()).isEqualTo(1L);
        assertThat(model.get().getName()).isEqualTo("test_t");
        assertThat(model.get().getResult()).isEqualTo("test_tさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。");
    }

    @Test
    public void testFindById1ToReturnNone() throws Exception{
    	Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `checks` (`id`, `name`, `result`, `created`, `modified`) VALUES ('1',  'test_t',  'test_tさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:56', '2013-08-20 12:34:56');"));
    	Option<Check> model = new CheckModelService().findById(2L);
    	assertThat(model.getClass()).isEqualTo(None.class);
    }

    @Test
    public void testFindByIdNullToReturnNone() throws Exception{
    	Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `checks` (`id`, `name`, `result`, `created`, `modified`) VALUES ('1',  'test_t',  'test_tさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:56', '2013-08-20 12:34:56');"));
    	Option<Check> model = new CheckModelService().findById(null);
    	System.out.println("返り値 = "+model);
    	assertThat(model.getClass()).isEqualTo(None.class);
    }

    @Test
    public void testSaveToReturnSome() throws Exception{
    	Check check = new Check("test", "testさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。");
    	check.id = 1L;
    	Option<Check> model = CheckModelService.use().save(check);
    	assertThat(model.getClass()).isEqualTo(Some.class);
    	assertThat(model.get().getId()).isEqualTo(1L);
        assertThat(model.get().getName()).isEqualTo("test");
        assertThat(model.get().getResult()).isEqualTo("testさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。");
    }

    @Test
    public void testSaveToReturnNone() throws Exception{
    	Check check = null;
    	Option<Check> model = CheckModelService.use().save(check);
    	assertThat(model.getClass()).isEqualTo(None.class);
    }


    // 要実装
    // 正常系（None）：1件のレコードから該当しないIdのものを取り出す

    // 要実装
    // 異常系（None）：1件のレコードから検索のキーとしてnullを渡す

    private void prepareSaveData() {
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `checks` (`id`, `name`, `result`, `created`, `modified`) VALUES ('1',  'test_a',  'test_aさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:11', '2013-08-20 12:34:56');"));
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `checks` (`id`, `name`, `result`, `created`, `modified`) VALUES ('2',  'test_b',  'test_bさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:12', '2013-08-20 12:34:56');"));
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `checks` (`id`, `name`, `result`, `created`, `modified`) VALUES ('3',  'test_c',  'test_cさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:13', '2013-08-20 12:34:56');"));
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `checks` (`id`, `name`, `result`, `created`, `modified`) VALUES ('4',  'test_d',  'test_dさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:14', '2013-08-20 12:34:56');"));
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `checks` (`id`, `name`, `result`, `created`, `modified`) VALUES ('5',  'test_e',  'test_eさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:15', '2013-08-20 12:34:56');"));
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `checks` (`id`, `name`, `result`, `created`, `modified`) VALUES ('6',  'test_f',  'test_fさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:16', '2013-08-20 12:34:56');"));
    }

    @Test
    public void testFindWithPage1Contains1And5() throws Exception {
    	prepareSaveData();
    	Option<List<Check>> oList = CheckModelService.use().findWithPage(1);
    	assertThat(oList.getClass()).isEqualTo(Some.class);
    	List<Check> list = oList.get();
    	assertThat(list.size()).isEqualTo(5);
    	assertThat(list.get(0).getId()).isEqualTo(1L);
    	assertThat(list.get(4).getId()).isEqualTo(5L);
    }

    @Test
    public void testFindWithPage2Contains6() throws Exception {
    	prepareSaveData();
    	Option<List<Check>> oList = CheckModelService.use().findWithPage(2);
    	assertThat(oList.getClass()).isEqualTo(Some.class);
    	List<Check> list = oList.get();
    	assertThat(list.size()).isEqualTo(1);
    	assertThat(list.get(0).getId()).isEqualTo(6L);
    }

    @Test
    public void testFindWithPage3ToReturnNone() throws Exception {
    	prepareSaveData();
    	Option<List<Check>> oList = CheckModelService.use().findWithPage(3);
    	assertThat(oList.getClass()).isEqualTo(None.class);
    }

    @Test
    public void testMaxPage1() throws Exception{
    	Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `checks` (`id`, `name`, `result`, `created`, `modified`) VALUES ('1',  'test_t',  'test_tさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:56', '2013-08-20 12:34:56');"));
    	Option<Integer> maxP = CheckModelService.use().getMaxPage();
    	assertThat(maxP.getClass()).isEqualTo(Some.class);
    	assertThat(maxP.get()).isEqualTo(1);
    }

    @Test
    public void testGetMaxPage1() throws Exception{
    	Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `checks` (`id`, `name`, `result`, `created`, `modified`) VALUES ('1',  'test_t',  'test_tさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:56', '2013-08-20 12:34:56');"));
    	Option<Integer> maxP = CheckModelService.use().getMaxPage();
    	assertThat(maxP.getClass()).isEqualTo(Some.class);
    	assertThat(maxP.get()).isEqualTo(1);
    }

    @Test
    public void testGetMaxPage0() throws Exception{
    	Option<Integer> maxP = CheckModelService.use().getMaxPage();
    	assertThat(maxP.getClass()).isEqualTo(Some.class);
    	assertThat(maxP.get()).isEqualTo(0);
    }

    @Test
    public void testGetMaxPage6() throws Exception{
    	prepareSaveData();
    	Option<Integer> maxP = CheckModelService.use().getMaxPage();
    	assertThat(maxP.getClass()).isEqualTo(Some.class);
    	assertThat(maxP.get()).isEqualTo(2);
    }

}