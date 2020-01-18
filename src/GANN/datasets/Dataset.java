package datasets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Dataset {
    double[][] dataset;
    String path;
    int lineCount;

    public Dataset(String path) throws IOException {
        super();
        this.path = path;
        fillDataSet(path);
    }

    private void fillDataSet(String path) throws IOException {
        int lineCount = (int) Files.lines(Paths.get(path)).count();
        this.lineCount = lineCount;
        File file1 = new File(path);
        dataset = new double[lineCount][5];
        Scanner scanner = new Scanner(file1);
        int counter = 0;
        while (scanner.hasNextLine()) {
            String[] values = scanner.nextLine().split("\t");
            for(int i = 0; i < values.length; i++) {
               dataset[counter][i] = Double.parseDouble(values[i]);
            }

            counter++;
        }
    }

    public double[][] getDataset() {
        return dataset;
    }

    public void setDataset(double[][] dataset) {
        this.dataset = dataset;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }
}
