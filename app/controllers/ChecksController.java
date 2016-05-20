package controllers;

import static play.data.Form.form;

import models.request.ResultPostRequest;
import models.utils.ConfigUtil;
import play.data.Form;
import play.libs.F.Option;
import play.mvc.*;
import views.html.check.*;
import models.entity.*;
import models.service.*;
import java.util.*;
import play.api.mvc.Call;
import models.utils.*;
import java.net.*;


public class ChecksController extends Application {

  public static Result index() {
	try{
	response().setCookie("myName", URLEncoder.encode("くっきー","UTF-8"));
	}catch(Exception e){

	}
	ConfigUtil config = new ConfigUtil();
  	String title = config.get("checkyou.setting.message.title").get();
  	String message = config.get("checkyou.setting.message.question").get();
  	Form<ResultPostRequest> form = form(ResultPostRequest.class);
  	session("sessionText", "せっしょん");
    return ok(index.render(title, message, form));
	  //return TODO;
  }

  public static Result result() throws Exception{
	  Option<String> cookie =  OptionUtil.apply(request().cookie("myName").value());
	  String cook = new String(URLDecoder.decode(cookie.get(),"UTF-8"));
	  Form<ResultPostRequest> form = form(ResultPostRequest.class).bindFromRequest();
	  if(form.error("name") != null && form.error("name").message().contains("error.required")) {
          return validationErrorIndexResult("名前欄が空です", form);
      }else if(form.error("name") != null && form.error("name").message().contains("error.pattern")){
    	  return validationErrorIndexResult("Twitter id形式ではありません", form);
      }else if(form.error("name") != null && form.error("name").message().contains("error.maxLength")){
    	  return validationErrorIndexResult("文字数が15文字を超えています", form);
      }else{
    	  ResultPostRequest r = new ResultPostRequest();
		  r = form.get();
		  CheckService c = new CheckService();
		  Option<String> rs = c.getResult(r.getName());
		  Check check = new Check(r.getName(), rs.get());
		  Option<Check> oc = check.store();
		  if(oc.isDefined()){
			  check = oc.get();
			  Option<String> mySession = OptionUtil.apply(session("sessionText"));
			  return ok(result.render(check.name + ConfigUtil.get("checkyou.setting.message.resultTitle").get(), check, mySession.get(), cook));
		  }else{
			  fail(new Call("GET", "/"), "error", "診断エラーです");
		  }
		  return null;
	  }
	  //return TODO;
  }

  public static Result resultId(Long id) throws Exception{
	  Option<String> cookie =  OptionUtil.apply(request().cookie("myName").value());
	  String cook = new String(URLDecoder.decode(cookie.get(),"UTF-8"));
	  Option<Check> check = new Check(id).unique();
	  Option<String> mySession = OptionUtil.apply(session("sessionText"));
      return check.isDefined() ?
          ok(result.render(
                  check.get().name
                  + ConfigUtil.get("checkYou.setting.message.resultTitle").getOrElse(""), check.get(), mySession.get(), cook
                  ))
              : Application.fail(routes.ChecksController.index(), "error", "診断エラーです");
  }

  public static Result recent(Integer page) {
	  response().discardCookie("myName");
	  Option<List<Check>> result = new Check().entitiesList(page);
      Integer maxPage            = new Check().entitiesMaxPage(1);
      //return result.get().size() != 0 ? ok(recent.render("診断結果一覧", "過去に診断した方々の一覧です", result.get(), page, maxPage)) : ok(recentEmpty.render("診断結果一覧", "過去の診断がありません"));
	  if(result.isDefined()){
		  return ok(recent.render("診断結果一覧", "過去に診断した方々の一覧です", result.get(), page, maxPage));
	  }else{
		  return ok(recentEmpty.render("診断結果一覧", "過去の診断がありません"));
	  }
	  //return TODO;
  }

  private static Result validationErrorIndexResult(String message, Form<ResultPostRequest> form) {
	    flash("error", message);
	    return badRequest(index.render(
	        ConfigUtil.get("checkyou.setting.message.title").getOrElse(""),
	        ConfigUtil.get("checkyou.setting.message.question").getOrElse(""),
	        form));
	  }

}
