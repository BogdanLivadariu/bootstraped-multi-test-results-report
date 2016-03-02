package com.github.bogdanlivadariu.reporting.commons;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;

public class ResourceUtil {

    private final static String RESOURCE_DIR = "/web-resource/";

    public static enum WEB_RESOURCE {
        BOOTSTRAP_THEME_CSS("bootstrap-theme.min.css"),
        BOOTSTRAP_TOGGLE_JS("bootstrap-toggle.min.js"),
        BOOTSTRAP_TOGGLE_CSS("bootstrap-toggle.min.css"),
        BOOTSTRAP_MIN_CSS("bootstrap.min.css"),
        BOOTSTRAP_MIN_JS("bootstrap.min.js"),
        HIGHCHARTS_EXPORTING("exporting.js"),
        HIGHCHARTS_3D("highcharts-3d.js"),
        HIGHCHARTS_JS("highcharts.js"),
        JQUERY("jquery.min.js"),
        STYLE("style.css");

        private final String name;

        private WEB_RESOURCE(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    public static void copyResource(WEB_RESOURCE source, String destPath) throws URISyntaxException, IOException {
        InputStream sourceInputStream = ResourceUtil.class.getResourceAsStream(RESOURCE_DIR + source.toString());

        File dest = new File(destPath + File.separator + source.toString());
        FileUtils.copyInputStreamToFile(sourceInputStream, dest);
    }

    public static void copyAllResources(String destPath) throws URISyntaxException, IOException {
        for (WEB_RESOURCE res : WEB_RESOURCE.values()) {
            copyResource(res, destPath);
        }
    }
}
