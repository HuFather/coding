package com.zhaohu.design.currentlimiting.exception;

/**
 * @creator : zhaohu
 * @date : 6/9/2022
 * @description :
 */
public class ConfigurationParseException extends RuntimeException{
    public ConfigurationParseException(String msg){
        super(msg);
    }

    public ConfigurationParseException(String msg,Exception exception){
        super(msg,exception);
    }
}
