package model.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FolderManager {
    private static FolderManager instance;
    private Map<String, Folder> folderMap;

    private FolderManager() {
        folderMap = new HashMap<>();
    }

    public FolderManager getInstance() {
        if (instance == null) {
            return new FolderManager();
        }

        return instance;
    }

    public Folder getFolderWithName(String name) {
        if (folderMap.containsKey(name)) {
            return folderMap.get(name);
        } else {
            Folder folder = new Folder(name);
            folderMap.put(name, folder);
            return folderMap.get(name);
        }
    }

    public void removeFolder(String name) {
        if (folderMap.containsKey(name))
            folderMap.remove(name);
    }

    public Iterator<Folder> iterator() {
        return folderMap.values().iterator();
    }
}
