package semgraphvisualizer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.xy.XYDataset;

import semdata.Version;

public class GraphManager {
	private GraphVisualizer graphVisualizer;
	
	
	public GraphManager(List<Version> versions){
		graphVisualizer = new GraphVisualizer(versions);
	}
	
    public List <JFreeChart> computeChartsForASpecificLaw(int lawId){
    	if(lawId == 1 )
    		return createLehmanLawOneGraphs();	
    	if(lawId == 2)
    		return createLehmanLawTwoGraphs();
    	if(lawId == 3)
    		return createLehmanLawThreeGraphs();
    	if(lawId == 4)
    		return createLehmanLawFourGraphs();
    	if(lawId == 5)
    		return createLehmanLawFiveGraphs();    	
    	if(lawId == 6)
    		return createLehmanLawSixGraphs();    	 	
    	
    	return createLehmanLawEightGraphs();    	
    	
    }
	
	private JFreeChart createBarChart(CategoryDataset dataset, String chartName,
						String xAxisName, String yAxisName) {
        JFreeChart chart = ChartFactory.createBarChart(chartName, xAxisName , 
        											yAxisName , dataset);
	        chart.setBackgroundPaint(Color.white);
	        CategoryPlot plot = (CategoryPlot) chart.getPlot();
	        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	        BarRenderer renderer = (BarRenderer) plot.getRenderer();
	        renderer.setDrawBarOutline(false);
	        chart.getLegend().setFrame(BlockBorder.NONE);
	        return chart;
    }
    
    private JFreeChart createLineChart( CategoryDataset dataset,String chartName,
    							String xAxisName, String yAxisName) {
        JFreeChart chart = ChartFactory.createLineChart(chartName, xAxisName,
        		yAxisName, dataset, PlotOrientation.VERTICAL,
                true,true,false);
        return chart;    
    }
	
    private List<JFreeChart> createLehmanLawOneGraphs(){
    	List<JFreeChart> graphs = new ArrayList<JFreeChart>();
    	graphs.add(createBarChart(graphVisualizer.createDatasetOperationChanges
    			(),"Operation Changes","Versions","Number Of Changes"));
    	graphs.add(createBarChart(graphVisualizer.
    			createDatasetDataStructuresChanges(),
    			"DataStructure Changes","Versions","Number Of Changes"));
    	graphs.add(createBarChart(graphVisualizer.createDatasetVersionsPerYear()
    			, "Versions Per Year","Year","Number Of Versions"));
		return graphs;
	}

	private List<JFreeChart> createLehmanLawTwoGraphs(){
		List<JFreeChart> graphs = new ArrayList<JFreeChart>();
		graphs.add(createLineChart(graphVisualizer.createDatasetOperationsComplexity(),
				"Operations Complexity","Versions","Complexity"));
		graphs.add(createBarChart(graphVisualizer.createDatasetMaintenanceActions(),
				"Maintenace Actions", "Versions", "Number of Actions"));
			return graphs;
	}
	
	private List<JFreeChart> createLehmanLawThreeGraphs(){
		List<JFreeChart> graphs = new ArrayList<JFreeChart>();
		graphs.add(createLineChart(graphVisualizer.createDatasetOperationsGrowth(),
				"Operations Growth","Versions","Growth"));
		graphs.add(createLineChart(graphVisualizer.createDatasetDataStructuresGrowth(),
				"Data Structures Growth","Versions","Growth"));		
		return graphs;
	}
	
	private List<JFreeChart> createLehmanLawFourGraphs(){
		List<JFreeChart> graphs = new ArrayList<JFreeChart>();
		graphs.add(createLineChart(graphVisualizer.createDatasetOperationsWorkRate(),
				"Operations Work Rate", "Versions","Work Rate (days)"));
		graphs.add(createLineChart(graphVisualizer.createDatasetDataStructuresWorkRate(),
				"Data Structures Work Rate","Versions","Work Rate (days)"));		
		return graphs;	
	}
	
	private List<JFreeChart> createLehmanLawFiveGraphs(){
		List<JFreeChart> graphs = new ArrayList<JFreeChart>();
		graphs.add(createLineChart(graphVisualizer.createDatasetOperationsGrowth(),
				"Operations Growth","Versions","Growth"));
		graphs.add(createLineChart(graphVisualizer.createDatasetDataStructuresGrowth(),
				"Data Structures Growth","Versions","Growth"));		
		return graphs;
	}
	
	private List<JFreeChart> createLehmanLawSixGraphs(){
		List<JFreeChart> graphs = new ArrayList<JFreeChart>();
		graphs.add(createLineChart(graphVisualizer.createDatasetOperationsNumber(),
				"Operations	Number","Versions","Number Of Operations"));
		graphs.add(createLineChart(graphVisualizer.createDatasetDataStructuresNumber(),
				"Data Structures Number","Versions","Number Of Data Structures"));		
		return graphs;
	}

	private List<JFreeChart> createLehmanLawEightGraphs(){
		List<JFreeChart> graphs = new ArrayList<JFreeChart>();
		graphs.add(createLineChart(graphVisualizer.createDatasetNumberOfOperationsEstimations(),
				"Number Of operations estimations","Versions","Number Of operations"));		
		return graphs;
	}
}
