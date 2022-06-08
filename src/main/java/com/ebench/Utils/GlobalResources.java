package com.ebench.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalResources {

    public static Logger getlogger(Class className)
    {
       return LoggerFactory.getLogger(className);
    }
}
