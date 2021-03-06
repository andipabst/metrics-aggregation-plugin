package io.jenkins.plugins.metrics.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.jenkins.plugins.metrics.model.measurement.MetricsMeasurement;
import io.jenkins.plugins.metrics.model.metric.Metric;

/**
 * Data class containing all metrics and entries for the detail page of a tool.
 */
public class MetricsProvider {

    private String origin;
    private List<MetricsMeasurement> metricsMeasurements = new ArrayList<>();
    private List<String> projectSummaryEntries = new LinkedList<>();
    private List<Metric> projectMetrics = new ArrayList<>();

    public List<MetricsMeasurement> getMetricsMeasurements() {
        return metricsMeasurements;
    }

    public void setMetricsMeasurements(final List<MetricsMeasurement> metricsMeasurements) {
        this.metricsMeasurements = metricsMeasurements;
    }

    public List<String> getProjectSummaryEntries() {
        return projectSummaryEntries;
    }

    public void setProjectSummaryEntries(final List<String> projectSummaryEntries) {
        this.projectSummaryEntries = projectSummaryEntries;
    }

    /**
     * Add an entry to the project summary.
     *
     * @param entry
     *         the entry to add
     */
    public void addProjectSummaryEntry(final String entry) {
        this.projectSummaryEntries.add(entry);
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(final String origin) {
        this.origin = origin;
    }

    public void setProjectMetrics(final List<Metric> projectMetrics) {
        this.projectMetrics = projectMetrics;
    }

    public List<Metric> getProjectMetrics() {
        return projectMetrics;
    }

    public void addProjectMetric(final Metric metric) {
        projectMetrics.add(metric);
    }
}
