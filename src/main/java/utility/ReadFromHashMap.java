package utility;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ReadFromHashMap {

    public String readHashMapTextFileWithComma(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append(", "));
        }
        catch (IOException e)
        {

        }
        return contentBuilder.toString();
    }


    public  String readHashMapTextFileWithEmptySpace(String filePath)
    {
        //String filePath = "src/test/resources/testdata/HashMapFromCancer.txt";
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append(" "));
        }
        catch (IOException e)
        {

        }
        return contentBuilder.toString();
    }








}
