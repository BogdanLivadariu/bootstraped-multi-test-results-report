package com.github.bogdanlivadariu.reporting.cucumber.json.models;

import static com.github.bogdanlivadariu.reporting.cucumber.helpers.Constants.FAILED;
import static com.github.bogdanlivadariu.reporting.cucumber.helpers.Constants.PASSED;

import java.util.UUID;

import com.github.bogdanlivadariu.reporting.cucumber.helpers.Constants;
import com.github.bogdanlivadariu.reporting.cucumber.helpers.SpecialProperties;

/**
 * Represents a Feature.
 */
public class Feature {

    private String pageTitle;

    private String uniqueID;

    private String id;

    private String name;

    private String description;

    private int line;

    private String keyword;

    private Tag[] tags;

    private String uri;

    private Element[] elements;

    private int zbang;

    private long totalDuration;

    private String overallStatus = PASSED;

    private int scenariosPassedCount;

    private int scenariosFailedCount;

    private int stepsTotalCount;

    private int stepsPassedCount;

    private int stepsFailedCount;

    private int stepsSkippedCount;

    private int stepsUndefinedCount;

    private String outputFileLocation;

    public String getOutputFileLocation() {
        return outputFileLocation;
    }

    public void setOutputFileLocation(String outputFileLocation) {
        this.outputFileLocation = outputFileLocation;
    }

    public Feature postProcess(SpecialProperties props) {
        pageTitle = Constants.FEATURE_SUMMARY_REPORT;
        uniqueID = UUID.randomUUID().toString();
        outputFileLocation = "feature-reports/" + uniqueID + ".html";
        for (Element el : elements) {
            el.postProcess(props);
            totalDuration += el.getTotalDuration();
            stepsTotalCount += el.getStepsTotalCount();
            stepsPassedCount += el.getStepsPassedCount();
            stepsFailedCount += el.getStepsFailedCount();
            stepsSkippedCount += el.getStepsSkippedCount();
            stepsUndefinedCount += el.getStepsUndefinedCount();

            if (el.getOverallStatus().equals(PASSED)) {
                scenariosPassedCount++;
            } else {
                scenariosFailedCount++;
            }
        }

        if (scenariosFailedCount > 0) {
            overallStatus = FAILED;
        }
        return this;
    }

    public Integer getScenariosCount() {
        return elements.length;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getLine() {
        return line;
    }

    public String getKeyword() {
        return keyword;
    }

    public Tag[] getTags() {
        return tags == null ? new Tag[] {} : tags;
    }

    public String getUri() {
        return uri;
    }

    public Element[] getElements() {
        return elements;
    }

    public int getZbang() {
        return zbang;
    }

    public Integer getStepsSkippedCount() {
        return stepsSkippedCount;
    }

    public Integer getStepsUndefinedCount() {
        return stepsUndefinedCount;
    }

    public Integer getStepsFailedCount() {
        return stepsFailedCount;
    }

    public Integer getStepsPassedCount() {
        return stepsPassedCount;
    }

    public Integer getScenariosFailedCount() {
        return scenariosFailedCount;
    }

    public Integer getStepsTotalCount() {
        return stepsTotalCount;
    }

    public long getTotalDuration() {
        return totalDuration;
    }

    public Integer getScenariosPassedCount() {
        return scenariosPassedCount;
    }

    public String getOverallStatus() {
        return overallStatus;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public String getPageTitle() {
        return pageTitle;
    }

}
