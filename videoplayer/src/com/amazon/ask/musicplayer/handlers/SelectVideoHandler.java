package com.amazon.ask.musicplayer.handlers;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.interfaces.display.ElementSelectedRequest;
import com.amazon.ask.musicplayer.VideoPlayerStreamHandler;
import com.amazon.ask.request.Predicates;
import com.amazon.ask.response.ResponseBuilder;

public class SelectVideoHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.requestType(ElementSelectedRequest.class));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		ElementSelectedRequest request = (ElementSelectedRequest) input.getRequest();
		String token = request.getToken();
		int index = Integer.parseInt(token);
		ResponseBuilder responseBuilder = input.getResponseBuilder();
		responseBuilder.addVideoAppLaunchDirective(VideoPlayerStreamHandler.VIDEOS[index], "タイトル", "サブタイトル");
		return responseBuilder.build();
	}

}
