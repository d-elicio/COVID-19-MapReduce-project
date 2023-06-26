package ProgettiBigData.CovidMapReduce2;

import java.util.Date;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class newTests {

	public static void main (String [] args ) throws Exception{
		long start= new Date().getTime();
		
		if(args.length!=2) {
			System.err.println("Usage: newTests <input path> <output path>");
			System.exit(-1);
		}
		
		Job job= new Job();
		job.setJarByClass(newTests.class);
		job.setJobName("New Tests");
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(newTestMapper.class);
		job.setReducerClass(newTestReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		job.waitForCompletion(true);
		
		long end= new Date().getTime();
		System.out.println("EXECUTION TIME IS: " + (end-start) + "ms");
	}
}
