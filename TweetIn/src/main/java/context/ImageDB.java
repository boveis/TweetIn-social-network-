package context;

import javafx.scene.image.Image;

import java.io.*;
import java.nio.channels.FileChannel;

public class ImageDB {
    public static void saveImage(String field , int id , File file) throws IOException {
        if (file != null){
            FileChannel sourceChannel = null;
            FileChannel destChannel = null;
            try {
                sourceChannel = new FileInputStream(file).getChannel();
                destChannel = new FileOutputStream(new File("src\\main\\resources\\context\\image\\"+field+"\\"+id+".png")).getChannel();
                destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            }finally{
                sourceChannel.close();
                destChannel.close();
            }
        }
    }






}
