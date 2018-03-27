package com.ledo.core.exceptions;

public enum OAuthExceptionConstants implements BaseExceptionConstants {

    /*
    # 11000~11999 oauth相关的错误，报告400
     */

    // invalid_scope:Invalid scope parameter in request
    // 12000: 在请求中存在无效的scope参数
    INVALID_SCOPE_PARAMETER(0, "在请求中存在无效的scope参数"),
    // invalid_scope:Missing scope parameter in request
    // 12001: 在请求中scope参数丢失
    MISSING_SCOPE_PARAMETER(1, "在请求中scope参数丢失"),
    // unauthorized_client
    // 12002: 不支持当前的授权方式
    UNAUTHORIZED_CLIENT(2, "不支持当前的授权方式"),
    // access_denied:Authorization denied by user
    // 12003: 用户授权拒绝
    AUTHORIZATION_DENIED_BY_USER(3, "用户授权拒绝"),
    // invalid_request:Missing code parameter in request
    // 12004: code参数不能为空
    MISSING_CODE_PARAMETER(4, "code参数不能为空"),
    // invalid_request:Missing redirect_uri parameter in request
    // 12005: redirect_uri参数不能为空
    MISSING_REDIRECT_URI_PARAMETER(5, "redirect_uri参数不能为空"),
    // invalid_request:Invalid redirect_uri parameter
    // 12006: 无效的redirect_uri参数
    INVALID_REDIRECT_URI_PARAMETER(6, "无效的redirect_uri参数"),
    // invalid_request:Invalid authorization code parameter
    // invalid_grant:Invalid code parameter in request
    // 12007: 无效的授权码
    INVALID_AUTHORIZATION_CODE(7, "无效的授权码"),
    // invalid_grant:Authorization code has expired
    // 12008: 授权码已过期
    AUTHORIZATION_CODE_HAS_EXPIRED(8, "授权码已过期"),
    // invalid_request:Missing username parameter in request
    // 12009: username参数不能为空
    MISSING_USERNAME_PARAMETER(9, "username参数不能为空"),
    // invalid_request:Missing password parameter in request
    // 12010: password参数不能为空
    MISSING_PASSWORD_PARAMETER(10, "password参数不能为空"),
    // invalid_request:Invalid refresh token
    // 12011: 无效的refresh token
    INVALID_REFRESH_TOKEN(11, "无效的refresh token"),
    // invalid_request:Missing refresh_token in request body
    // 12012: refresh_token参数不能为空
    MISSING_REFRESH_TOKEN(12, "refresh_token参数不能为空"),
    // unsupported_response_type:Grant not supported
    // 12013: 不支持的授权类型
    GRANT_NOT_SUPPORTED(13, "不支持的授权类型"),
    // invalid_request:Invalid username parameter in request
    // 12014: 无效的username参数
    INVALID_USERNAME_PARAMETER(14, "无效的username参数"),
    // invalid_client:No client could be found
    // 12015: 无效的客户端
    NO_CLIENT_COULD_BE_FOUND(15, "无效的客户端"),

    // invalid_request:Invalid access token
    // 12011: 无效的access token
    INVALID_ACCESS_TOKEN(16, "无效的access token"),

    // invalid_client:Invalid client credentials
    // 12017: 无效的客户端授权
    INVALID_CLIENT_CREDENTIALS(17, "无效的客户端授权"),
    // invalid_request:Missing confirm_password parameter in request
    // 12018: 确认密码不为空
    MISSING_CONFIRM_PASSWORD(18, "确认密码不为空"),
    // invalid_request:Missing validation_code parameter in request
    // 12019: 手机验证码不为空
    MISSING_VALIDATION_CODE(19, "手机验证码不为空"),
    // for resource server, Missing access_token in request header
    // 12050: access_token参数不能为空
    MISSING_ACCESS_TOKEN(20, "access_token参数不能为空"),
    // invalid_request:access token has expired
    // 12051: access_token已过期
    ACCESS_TOKEN_HAS_EXPIRED(21, "access_token已过期"),

    INVALID_TICKET(22, "无效的ticket值"),

    INVALID_TIMESTAMP(23, "无效的timestamp值"),

    INVALID_SIGN(24, "无效的签名"),

    INVALID_DATA(25, "无效的数据"),

    INVALID_VERSION(26, "无效的版本号");

    private long code;
    private String message;

    OAuthExceptionConstants(long code, String message) {
        this.code = BaseExceptionConstants.StartCode.OAUTH_EXCEPTION_START_CODE + code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
