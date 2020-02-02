package io.jenkins.plugins.metrics.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.jenkins.plugins.datatables.TableColumn;
import io.jenkins.plugins.datatables.TableConfiguration;
import io.jenkins.plugins.datatables.TableModel;
import io.jenkins.plugins.metrics.model.measurement.MethodMetricsMeasurement;
import io.jenkins.plugins.metrics.model.metric.MetricDefinition;

public class ClassDetailsTableModel extends TableModel {

    private final List<MetricDefinition> supportedMetrics;
    private final List<MethodMetricsMeasurement> metricsMeasurements;

    public ClassDetailsTableModel(final List<MetricDefinition> supportedMetrics,
            final List<MethodMetricsMeasurement> metricsMeasurements) {
        this.supportedMetrics = supportedMetrics;
        this.metricsMeasurements = metricsMeasurements;
    }

    @Override
    public String getId() {
        return "metrics-table";
    }

    @Override
    public List<TableColumn> getColumns() {
        List<TableColumn> columns = new ArrayList<>();
        columns.add(new TableColumn("Line", "beginLine"));
        columns.add(new TableColumn("Method", "methodName"));

        columns.addAll(supportedMetrics
                .stream()
                .map(metricDefinition -> new TableColumn(metricDefinition.getDisplayName(),
                        "metricsDisplay." + metricDefinition.getId()))
                .collect(Collectors.toList())
        );

        return columns;
    }

    @Override
    public List<Object> getRows() {
        return metricsMeasurements.stream()
                .map(Row::new)
                .collect(Collectors.toList());
    }

    @Override
    public TableConfiguration getTableConfiguration() {
        return new TableConfiguration()
                .responsive()
                .colReorder()
                .stateSave()
                .buttons("colvis");
    }

    public static class Row {
        private final MethodMetricsMeasurement metricsMeasurement;

        Row(final MethodMetricsMeasurement metricsMeasurement) {
            this.metricsMeasurement = metricsMeasurement;
        }

        /**
         * Get the Name of a class. At the same time this is a link to the detail view (@link {@link ClassDetailsView})
         * for this class.
         *
         * @return a link to the details with the name of the class as text
         */
        public String getName() {
            return metricsMeasurement.getMethodName();
        }

        /**
         * Get the metrics of this measurement as they should be displayed (i.e. formatted).
         *
         * @return the map of metrics
         */
        public Map<String, String> getMetricsDisplay() {
            return metricsMeasurement.getMetricsDisplay();
        }

        /**
         * Get the raw metrics of this measurement e.g. for use in filtering.
         *
         * @return the map of metrics
         */
        public Map<String, Number> getMetricsRaw() {
            return metricsMeasurement.getMetricsRaw();
        }
    }
}
