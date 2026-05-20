public class AutoSaveThread extends Thread {
    
    private StudentManager manager;
    

    // Constructor to link this thread with the main manager
    public AutoSaveThread(StudentManager manager) {
        this.manager = manager;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Sleep for 1 minute (60000 milliseconds)
                Thread.sleep(60000); 
                
                // Call the save method from the main system
                if (manager != null) {
                    manager.saveToFile(); 
                    System.out.println("\n Auto-Save: Student records saved successfully in the background.");
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Auto-save thread interrupted.");
        }
    }
}