package com.cenmo.mogu.common.constant;

/**
 * @author Cenmo
 * @Date 2020-10-16 2020/10/16
 */
public enum ResultEnum {
    SUCCESS(true,200,"成功"),
    UNKNOWN_REASON(false,501,"未知错误"),
    BAD_SQL_GRAMMAR(false,502,"sql语法错误"),
    JSON_PARSE_ERROR(false,503,"json解析异常"),
    PARAM_ERROR(false,504,"参数错误"),
    FILE_UPLOAD_ERROR(false,505,"文件上传错误"),
    EXCEL_DATA_IMPORT_ERROR(false,506,"Excel数据导入错误"),
    VERIFIED_ERROR(false,507,"校验错误"),
    SQL_OPERATION_ERROR(false,508,"数据库操作失败");

    private Boolean success;
    private Integer status;
    private String msg;

    ResultEnum() {
    }

    ResultEnum(Boolean success, Integer status, String msg) {
        this.success = success;
        this.status = status;
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
