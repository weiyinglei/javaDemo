/**
 * Created by weiyinglei on 2016-10-19.
 */
package com.cetiti.log;

import org.apache.log4j.Logger;

public class logDemo {
    private static Logger LOGGER = Logger.getLogger(logDemo.class);

    public static void main(String[] args) {
        LOGGER.error("Hello World!");
    }
}
