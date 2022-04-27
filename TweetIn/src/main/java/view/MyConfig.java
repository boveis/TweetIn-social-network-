package view;

import java.io.*;
import java.util.Properties;

public class MyConfig {
    public Properties pageAddressProperties;
    public static MyConfig instance;
    public MyConfig() throws IOException {
        instance = this;
        loadConfing();
    }
    public void loadConfing() throws IOException {
        File file = new File("src\\main\\resources\\config\\properties");
        FileReader fr = new FileReader(file);
        this.pageAddressProperties = new Properties();
        pageAddressProperties.load(fr);
    }


}
