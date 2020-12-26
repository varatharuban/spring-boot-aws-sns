package com.rubanelearning.sns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;

@RestController
public class SNSController {

	@Autowired
	private AmazonSNSClient snsClient;

	String TOPIC_ARN = "<>";

	@GetMapping("/add-subscription/{email}")
	public String addSubscription(@PathVariable String email) {
		SubscribeRequest request = new SubscribeRequest(TOPIC_ARN, "email", email);
		snsClient.subscribe(request);
		return "Subscription request is pending. To confirm the subscription, check your email : " + email;
	}

	@GetMapping("/send-notification")
	public String publishMessageToTopic() {
		PublishRequest publishRequest = new PublishRequest(TOPIC_ARN, buildEmailBody(),
				"Notification: Team Meeting");
		snsClient.publish(publishRequest);
		return "Notification send successfully !!";
	}

	private String buildEmailBody() {
		return "Dear user ,\n" + "\n" + "\n"
				+ "Lorem Ipsum is simply dummy text of the printing and typesetting industry." + "\n"
				+ "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. \n"
				+ "It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.";
	}
}
