package com.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;

public class HDFSApp {

    FileSystem fileSystem = null;
    Configuration configuration = null;

    @Test
    public void mkdir() throws IOException {
        fileSystem.mkdirs(new Path("/CentOS7/123"));
    }

    @Test
    public void createFile() throws Exception {
        FSDataOutputStream outputStream = fileSystem.create(new Path("/CentOS7/a.txt"));
        outputStream.write("hello world!".getBytes());
        outputStream.flush();
        outputStream.close();
    }

    @Test
    public void catFile() throws Exception {
        FSDataInputStream open = fileSystem.open(new Path("/CentOS7/a.txt"));
        IOUtils.copyBytes(open,System.out,1024);
        open.close();
    }

    @Test
    public void renameFile() throws Exception {
        Path newpath = new Path("/CentOS7/b.txt");
        boolean rename = fileSystem.rename(new Path("/CentOS7/a.txt"), newpath);
        System.out.println(rename);
    }

    @Before
    public void setUp() throws Exception {
        configuration = new Configuration();
        fileSystem = FileSystem.get(new URI("hdfs://192.168.137.8:8020"),configuration,"CentOS7");
    }

    @After
    public void setDown(){

    }
}
