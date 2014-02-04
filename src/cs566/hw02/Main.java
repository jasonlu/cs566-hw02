/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs566.hw02;

/**
 *
 * @author Chenghsien "Jason" Lu
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // Read args, if none provide, assume the input files are in the same directory.
        String homePath = System.getProperty("user.home");
        String dirPath = System.getProperty("user.dir");
        if(args.length > 0) {
            String userPath = args[0];
            userPath = userPath.replaceFirst("~", homePath);
            if(new File(userPath).exists()) {
                dirPath = userPath;
            }
        }

        out.println("Searching input files in:" + dirPath);
        
        File folder = new File(dirPath);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                out.println(file.getName());
                // read file here.
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = null;
                List<Integer> list = new ArrayList<>();

                while ((line = br.readLine()) != null) {
                    list.add(Integer.parseInt(line));
                }
                br.close();
                
                Integer[] A = list.toArray(new Integer[list.size()]);
                out.println("Length of array A: " + A.length);
                out.println("Insertion sort:");
                long startTime = System.nanoTime();
                Sort.Insertion(A);
                long endTime = System.nanoTime();
                long nanoseconds = (endTime - startTime);
                long microseconds = nanoseconds / 1000;
                
                //out.println("Running time: " + nanoseconds + " nano seconds."); 
                out.println("\tRunning time: " + microseconds + " micro seconds.");
                // Save sorted array to a new file.
                //break;
            }
        }
        out.println("Press enter to exit...");
        System.in.read();
    }
}
