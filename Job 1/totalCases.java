package ProgettiBigData.CovidMapReduce;

import java.util.Date;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class totalCases {
	
	public static void main (String[]args) throws Exception{
		long start= new Date().getTime();
		
		if (args.length != 2) {
			System.err.println("Usage: totalCases <input path> <output path>");
			System.exit(-1);
		}
		
		Job job = new Job();
		job.setJarByClass(totalCases.class);
		job.setJobName("total cases");
		FileInputFormat.addInputPath(job, new Path (args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(totalCasesMapper.class);
		job.setReducerClass(totalCasesReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.waitForCompletion(true);
		
		
		long end=new Date().getTime();
		System.out.println("EXECUTION TIME IS: "+(end -start)+"ms");
	}
}
