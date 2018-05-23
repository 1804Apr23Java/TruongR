package com.revature.main;

import java.io.File;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.revature.dao.*;

public class TestingDriver {

	public static void main(String[] args) { /*
												 * EmployeeDao ed = new EmployeeDaoImpl();
												 * System.out.println(ed.getEmployee(2));
												 */

		final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
		List<Bucket> buckets = s3.listBuckets();

		try {
			s3.putObject("rtruong2-receipts", "deg.jpg", new File("/home/robert/Desktop/degree.JPG"));
		} catch (AmazonServiceException e) {
			System.err.println(e.getErrorMessage());
			System.exit(1);
		}
		System.out.println("Done!");
	}

}
