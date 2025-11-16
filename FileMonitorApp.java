import java.awt.*;
import java.util.HexFormat;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Scanner;
import java.io.File;
import javax.swing.JFileChooser;

public class FileMonitorApp  {
Scanner sc = new Scanner(System.in);
int choice = 0;
JFileChooser chooser = new JFileChooser();

public String getFileName(){
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

public static String fileHash(String filePath) throws Exception {
   var fContent = Files.readAllBytes(Paths.get(filePath));
   var digest = MessageDigest.getInstance("SHA-256");
   var hashBytes = digest.digest(fContent);
   return HexFormat.of().formatHex(hashBytes);
}

public void FileMonitor () throws Exception {

 String file = getFileName();
 var fHash = fileHash(file);
 System.out.println("File Path: " + file + " " + "Hash: " + fHash);
 File hashes = new File("fHashes.txt");
    if (hashes.createNewFile()) {
        System.out.println("File created: " + hashes.getName());
    } else {
        System.out.println("File already exists.");
    }

    // Write Code that ensures if the file is already created, it will continue adding hashes to the text file.
}

public void FileIntegrity (){

}

public void MonitoredFiles(){

}


public void menu() throws Exception {
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
    public static void main(String[] args) throws Exception{
        FileMonitorApp app = new FileMonitorApp();
        app.menu();
    }
}
