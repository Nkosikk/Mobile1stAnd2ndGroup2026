package Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReportManager {

    private static ExtentReports extentReports;

    public static ExtentReports getExtentReports()
    {
        if(extentReports==null)
        {
            new File("target/reports").mkdirs();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/reports/NdosiAutomationReport.html"
            );

            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Android and IOS Automation Results");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);

            extentReports.setSystemInfo("Framework","Appium Hybrid Automation framework");
            extentReports.setSystemInfo("Test Runner","TestNG");
            extentReports.setSystemInfo("Platforms","Android and IOS");
            extentReports.setSystemInfo("Execution Types", "Native App and Mobile Browser");

        }
        return extentReports;
    }
}
