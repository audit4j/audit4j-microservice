package org.audit4j.microservice;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class AppRunningChecker {

    static File file;
    static FileChannel fileChannel;
    static FileLock lock;
    static boolean running = false;

    @SuppressWarnings("resource")
    public static boolean checkIfAlreadyRunning() throws IOException {
        file = new File("lock/"+ "server.lock");
        if (!file.exists()) {
            file.createNewFile();
            running = true;
        } else {
            file.delete();
        }

        fileChannel = new RandomAccessFile(file, "rw").getChannel();
        lock = fileChannel.tryLock();

        if (lock == null) {
            fileChannel.close();
            return true;
        }

        return running;
    }

    public static void unlockFile() {
        try {
            if (lock != null)
                lock.release();
            fileChannel.close();
            file.delete();
            running = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
