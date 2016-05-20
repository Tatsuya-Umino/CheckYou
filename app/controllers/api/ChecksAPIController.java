package controllers.api;

import play.mvc.*;
import models.response.*;
import models.setting.CheckYouStatusSetting;
import models.entity.*;
import static play.libs.F.*;
import play.libs.*;
import models.utils.*;
import models.service.*;
import java.util.*;


public class ChecksAPIController extends Controller {

	  // 診断結果取得
	public static Result checks(Long id) {
	    ChecksDefaultResponse result = new ChecksDefaultResponse();
	    Option<Check> checkOps = new Check(id).unique();
	    if(checkOps.isDefined()) {
	    	CheckYouStatusSetting status = CheckYouStatusSetting.OK;
	    	result.code = status.code;
	    	result.status = status.message;
	    	Option<CheckResponse> cr = result.response(checkOps.get());
	    	result.result = cr.get();
	    	// JSON形式に変換して返す
	    	return ok(Json.toJson(result));
	    }
	    return badRequest(Json.toJson(ConfigUtil.get("checkyou.setting.message.error.noResult").getOrElse("")));
	  }

	  // 診断結果の一覧取得
	  public static Result getList(Integer page) {
		  CheckPagingResponse result = new CheckPagingResponse();
		  Check c = new Check();
		  Option<List<Check>> oclist = c.entitiesList(page);
		  List<CheckResponse> crlist = new ArrayList<CheckResponse>();
		  if(oclist.isDefined()){
			  List<Check> clist = oclist.get();
			  CheckResponseService crs = new CheckResponseService();
			  CheckYouStatusSetting status = CheckYouStatusSetting.OK;
		    	result.code = status.code;
		    	result.status = status.message;
		    	for (int i = 0, n = clist.size(); i < n; i++) {
		    		Check c2 = new Check();
		    	    c2= clist.get(i);
		    	    crlist.add(crs.getCheckResponse(c2).get());
		    	}
		    	result.results = crlist;
		    	return ok(Json.toJson(result));
		  }
		  return badRequest(Json.toJson(ConfigUtil.get("checkyou.setting.message.error.noResult").getOrElse("")));
	  }
}