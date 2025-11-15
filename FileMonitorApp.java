import java.awt.*;
import java.util.Scanner;
import java.io.File;
import javax.swing.JFileChooser;

public class FileMonitorApp  {
Scanner sc = new Scanner(System.in);
int choice = 0;

public static String getFileName(){
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Choose a file");
    int result = chooser.showOpenDialog(null);
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    if(result == JFileChooser.APPROVE_OPTION){
        File file = chooser.getSelectedFile();
        return file.getAbsolutePath();
    }
    else{
        System.out.println("No Selection");
        return null;
    }
}

public void FileMonitor (){
 getFileName();
}

public void FileIntegrity (){

}

public void MonitoredFiles(){

}


public void menu(){
    do {
        {
          System.out.print(
                    "+---------------------------+\n" +
                    "|        MAIN  MENU         |\n" +
                    "+---------------------------+\n" +
                    "| 1. Add File to monitor    |\n" +
                    "| 2. Verify file integrity  |\n" +
                    "| 3. List Monitored files   |\n" +
                    "| 4. Exit                   |\n" +
                    "+---------------------------+\n" +
                    "Enter Choice: ");
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
                continue;
            }

            switch (choice) {
                        case 1:
                            FileMonitor();
                            break;
                        case 2:
                            FileIntegrity();
                            break;
                        case 3:
                            MonitoredFiles();
                            break;
                        case 4:
                            System.out.println("Bye!");
                            System.exit(0);
                    }
        }
    } while (choice != 4);
}
    public static void main(String[] args) {
        FileMonitorApp app = new FileMonitorApp();
        app.menu();
    }
}
