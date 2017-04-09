package com.recipe.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

/**
 * Created by dough on 4/9/2017.
 */
public class LoggerUtils {
    public static void logRemoteAddr(String routeName, Logger logger, HttpServletRequest httpServletRequest) {
        logger.info(String.format("Request for page: %s at [IP: %s, HOST: %s, PORT: %s]",
                routeName,
                httpServletRequest.getRemoteAddr(),
                httpServletRequest.getRemoteHost(),
                httpServletRequest.getRemotePort()));
    }
}
