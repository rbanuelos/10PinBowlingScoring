package com.input;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileReader {
  public List<String> readFile(String filePath) throws IOException {
    List<String> lineList = new ArrayList<>();

    try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
      stream.forEach(lineList::add);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return lineList;
  }
}
