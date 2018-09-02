package com.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCountApp {

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        Configuration configuration = new Configuration();

        Job job = Job.getInstance(configuration, "wordcount");

        job.setJarByClass(WordCountApp.class);

        FileInputFormat.setInputPaths(job,new Path(args[0]));

        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        job.setCombinerClass(MyReducer.class);

        job.setPartitionerClass(MyPartitioner.class);
        job.setNumReduceTasks(4);

        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        FileSystem fileSystem = FileSystem.get(new Configuration());
        if (fileSystem.exists(new Path(args[1]))){
            fileSystem.delete(new Path(args[1]),true);
        }

        job.waitForCompletion(true);
    }
}

class  MyMapper extends Mapper<LongWritable, Text,Text,LongWritable>{

    LongWritable one = new LongWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] ss = value.toString().split(" ");
        for (String s : ss) {
            context.write(new Text(s),one);
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

class MyPartitioner extends Partitioner<Text,LongWritable>{

    @Override
    public int getPartition(Text text, LongWritable longWritable, int numPartitions) {
        if (text.toString().equals("a")){
            return 0;
        }
        if (text.toString().equals("b")){
            return 1;
        }
        if (text.toString().equals("c")){
            return 2;
        }
        return 3;
    }
}