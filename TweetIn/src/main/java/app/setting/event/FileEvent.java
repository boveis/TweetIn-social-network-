package app.setting.event;

import java.io.File;

public class FileEvent {

    public File file;
    public int id;
    public FileEvent(int id , File file){
        this.file = file;
        this.id = id;
    }
}
