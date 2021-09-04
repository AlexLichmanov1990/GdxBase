package engine.propsparser;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.List;

public class PropsParser {
    public static List<PropsRow> parse(String file) {
        return parse(file, Files.FileType.Internal);
    }

    public static List<PropsRow> parse(String file, Files.FileType fileType) {
        return parse(Gdx.files.getFileHandle(file, fileType));
    }

    public static List<PropsRow> parse(FileHandle fileHandle) {
        List<PropsRow> stringRow = new ArrayList<>();
        String s = fileHandle.readString();
        if (s != null) {
            String[] strings = s.split("\n");
            for (String row : strings) {
                try {
                    stringRow.add(new PropsRow(row).build());
                } catch (IllegalStateException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return stringRow;
    }
}
