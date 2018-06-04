package com.davsen;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;

public class WidgetLambdaClient {
	
	private static String AWS_KEY = "AKIAJUAGOR3HLXWTTYNQ";
	private static String AWS_SECRET_ACCESS_KEY = "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";

	public static void main(String[] args) {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(AWS_KEY, AWS_SECRET_ACCESS_KEY);
		
		// AWS lambda client
		AWSLambda lambda = AWSLambdaClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(Regions.US_EAST_1).build();
		
		// Create an Invoke Request
		InvokeRequest request = new InvokeRequest()
				.withFunctionName("get-widget")
				.withPayload("{ \"id\": \"1\"}");
		
		try {
			// Execute Invoke Request
			InvokeResult result = lambda.invoke(request);
			System.out.println("Status code: " + result.getStatusCode());
			
			// Get the response as JSON
			String json = new String(result.getPayload().array(), "UTF-8");
			System.out.println("JSON result : " + json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
