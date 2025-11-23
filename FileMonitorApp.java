import java.awt.*;
import java.io.*;
import java.util.HexFormat;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class FileMonitorApp  {
Scanner sc = new Scanner(System.in);
int choice = 0;
JFileChooser chooser = new JFileChooser();

// getter method
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
// Hash Reader
    public static String getStoredHash(String targetFile) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader("fHashes.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                String file = parts[0].trim();
                String storedHash = parts[1].trim();

                if (file.equals(targetFile)) {
                    return storedHash; // Found the hash
                }
            }
        }
        return null; // File not found in hashes.txt
    }

    public static void listMonitoredFiles() throws Exception {
        System.out.println("Monitored files:");
        try (BufferedReader br = new BufferedReader(new FileReader("fHashes.txt"))) {
            String line;
            int count = 1;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split("\\|");
                String file = parts[0].trim();

                System.out.println(count + ". " + file);
                count++;
            }
        }
    }

    // hasher
public static String fileHash(String filePath) throws Exception {
   var fContent = Files.readAllBytes(Paths.get(filePath));
   var digest = MessageDigest.getInstance("SHA-256");
   var hashBytes = digest.digest(fContent);
   return HexFormat.of().formatHex(hashBytes);
}
// actual monitor sys
public void FileMonitor () throws Exception {

 String file = getFileName();
 var fHash = fileHash(file);
// System.out.println("File Path: " + file + " " + "Hash: " + fHash);
 File hashes = new File("fHashes.txt");
    PrintWriter writer = new PrintWriter(new FileWriter("fHashes.txt", true));
    if (hashes.createNewFile()) {
        System.out.println("File created: " + hashes.getName());
        System.out.println("Writing to...... " + hashes.getName());
        writer.println(file + "|" + fHash);
        writer.close();
    } else {
        System.out.println("Writing to......" + hashes.getName());
        writer.println(file + "|" + fHash);
        writer.close();
    }
}

public void FileIntegrity () throws Exception {
System.out.println("--------| File Integrity |--------");
System.out.println("What file would you like to check? ");
String file = getFileName();
System.out.println("Comparing hashes... ");
var fHash = fileHash(file);
String storedHash = getStoredHash(file);
if(fHash.equals(storedHash)){
    System.out.println("The File " + file +  " has not been changed!");
}
if(!fHash.equals(storedHash)){
    System.out.println("The File" + file + "has been changed!");
}
    System.out.println("CURRENT HASH: " + fHash + " " + "DATABASE HASH: " +storedHash);

}

public void menu() throws Exception {
    do {
        {
            System.out.print(
            "╔════════════════════════════════════╗\n" +
            "║           FILE MONITOR             ║\n" +
            "╠════════════════════════════════════╣\n" +
            "║ 1. Add File to Monitor             ║\n" +
            "║ 2. Verify File Integrity           ║\n" +
            "║ 3. List Monitored Files            ║\n" +
            "║ 4. Exit                            ║\n" +
            "╚════════════════════════════════════╝\n" +
            "Please enter your choice: "
            );

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
                            listMonitoredFiles();
                            break;
                        case 4:
                            System.out.print("Created by Voumeaux (GitHub: https://github.com/Voumeaux)" + " Thank you for using my program!");
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

