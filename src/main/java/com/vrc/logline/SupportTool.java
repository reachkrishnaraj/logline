package com.vrc.logline;

import com.vrc.logline.domain.AppServer;
import org.apache.log4j.Logger;

public class SupportTool {
    private static final Logger log = Logger.getLogger(SupportTool.class);

    public static void main(String[] list) throws Exception {
        final AppServer appServer = new AppServer();
        appServer.start();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    appServer.stop();
                } catch (Exception e) {
                    log.error(e);
                }
            }
        }));

    }
}