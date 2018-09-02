package com.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import java.io.IOException;

public class WordCountApp {

    @Test
    public void start() throws Exception {
        Configuration configuration = new Configuration();

        Job job = Job.getInstance(configuration, "wordcount");

        job.setJarByClass(WordCountApp.class);

        FileInputFormat.setInputPaths(job,new Path("/"));

        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputKeyClass(LongWritable.class);

        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        FileOutputFormat.setOutputPath(job,new Path("/"));

        job.waitForCompletion(true);
    }
}

class  MyMapper extends Mapper<LongWritable, Text,Text,LongWritable>{
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] ss = value.toString().split(" ");
        for (String s : ss) {
            context.write(new Text(s),new LongWritable(1));
        }
    }
}

class MyReducer extends Reducer<Text,LongWritable,Text,LongWritable>{

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long sum = 0;
        for (LongWritable value : values) {
            sum += value.get();
        }
        context.write(key,new LongWritable(sum));
    }
}