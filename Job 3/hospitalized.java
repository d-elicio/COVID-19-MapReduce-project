package ProgettiBigData.CovidMapReduce3;

import java.util.Date;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class hospitalized {
	
	public static void main(String[] args) throws Exception {
		long start = new Date().getTime();
			if (args.length != 2) {
				System.err.println("Usage: Topten <input path> <output path>");
				System.exit(-1);
			}
		Job job = new Job();
		job.setJarByClass(hospitalized.class);
		job.setJobName("Hospitalized");
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.setMapperClass(hospitalizedMapper.class);
		job.setReducerClass(hospitalizedReducer.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		job.waitForCompletion(true);

		long end = new Date().getTime();
		System.out.println("EXECUTION TIME IS: "+(end-start)+"ms");
	}
}
