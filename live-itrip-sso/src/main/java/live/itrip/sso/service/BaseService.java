package live.itrip.sso.service;

import live.itrip.common.ErrorCode;
import live.itrip.common.response.BaseResult;
import live.itrip.sso.interfaces.IWriteResponse;

public class BaseService implements IWriteResponse {

    public BaseService() {
        super();
    }


    @Override
    public BaseResult paramInvalid(String paramName, String paramValue) {
        BaseResult result = new BaseResult();
        result.setCode(ErrorCode.PARAM_INVALID.getCode());
        result.setMsg(String.format(ErrorCode.PARAM_INVALID.getMessage() + "%s(%s).", paramName, paramValue));

        return result;
    }

    @Override
    public BaseResult paramInvalid( String paramName) {
        BaseResult result = new BaseResult();
        result.setCode(ErrorCode.PARAM_INVALID.getCode());
        result.setMsg(String.format(ErrorCode.PARAM_INVALID.getMessage() + "(%s).", paramName));

        return result;
    }

}
