package models.service;

import models.entity.Check;
import models.response.*;
import models.setting.CheckYouStatusSetting;
import static play.libs.F.*;
import models.response.*;
import models.utils.*;

// レスポンス用サービスクラス
public class CheckResponseService {

    public static CheckResponseService use() {
        return new CheckResponseService();
    }

    // BadRequestを受け取る
    public ChecksDefaultResponse getBadRequest(String message) {
        ChecksDefaultResponse result = new ChecksDefaultResponse();
        CheckYouStatusSetting status = CheckYouStatusSetting.NO_RESULT;
        result.code    = status.code;
        result.status  = status.message;
        result.message = message;
        return result;
    }


    // CheckからCheckResponseレスポンスへの変換クラス
    public Option<CheckResponse> getCheckResponse(Check response) {
        Option<Check> checkOps = OptionUtil.apply(response);
        if(checkOps.isDefined()){
        	return OptionUtil.apply(new CheckResponse(response.id, response.name, response.result, response.created, response.modified));
        }
        return OptionUtil.apply(new CheckResponse());
    }
}