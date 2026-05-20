public class ReportThread extends Thread {
    
    private final StudentManager manager;
    private final String criteria; 

    // Constructor to initialize the thread with the manager and criteria
    public ReportThread(StudentManager manager, String criteria) {
        this.manager = manager;
        this.criteria = criteria;
    }

    @Override
    public void run() {
        System.out.println("\n System: Preparing report in the background... Please wait.");
        
        try {
            // Sleep for 2 seconds to simulate the time taken to process large data
            Thread.sleep(2000); 
            
            if (manager != null) {
                // Call the report generation method from the main system
                manager.generateReport(criteria); 
                System.out.println("\n System: Report generated successfully.");
            }
            
        } catch (InterruptedException e) {
            System.out.println("Report generation was interrupted.");
        }
    }
}