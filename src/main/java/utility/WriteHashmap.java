package utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WriteHashmap {


    // final static String outputFilePath = "src/test/resources/testdata/StringFile.txt";

    public void writeToAHashMap(LinkedHashMap<String, String> map, String filePath) {


        //new file object
        File file = new File(filePath);

        BufferedWriter bf = null;
        ;

        try {

            //create new BufferedWriter for the output file
            bf = new BufferedWriter(new FileWriter(file));

            //iterate map entries
            for (Map.Entry<String, String> entry : map.entrySet()) {

                //put key and value separated by a colon
                //  bf.write( entry.getKey() + ":" + entry.getValue() );
                bf.write(entry.getKey());

                //new line
                bf.newLine();
            }

            bf.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                //always close the writer
                bf.close();
            } catch (Exception e) {
            }
        }
    }
}


//    public static LinkedHashMap<String, String> testMap(){
//        LinkedHashMap<String, String> map = new LinkedHashMap<>();
//        map.put ("AJCC_GROUP_BEST_STAGE","99");
//        map.put ("STATUS", "ACCEPTED");
//        return map;
//    }
//}
