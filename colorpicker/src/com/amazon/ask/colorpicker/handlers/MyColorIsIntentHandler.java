/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.amazon.ask.colorpicker.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.colorpicker.handlers.WhatsMyColorIntentHandler.COLOR_KEY;
import static com.amazon.ask.colorpicker.handlers.WhatsMyColorIntentHandler.COLOR_SLOT;
import static com.amazon.ask.request.Predicates.intentName;

public class MyColorIsIntentHandler implements RequestHandler {
	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("MyColorIsIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slots = intent.getSlots();

		// Get the color slot from the list of slots.
		Slot favoriteColorSlot = slots.get(COLOR_SLOT);

		String speechText, repromptText;
		boolean isAskResponse = false;

		// Check for favorite color and create output to user.
		if (favoriteColorSlot != null) {
			// Store the user's favorite color in the Session and create response.
			String favoriteColor = favoriteColorSlot.getValue();
			input.getAttributesManager().setSessionAttributes(Collections.singletonMap(COLOR_KEY, favoriteColor));

			speechText = String.format("あなたが一番好きな色は %s だということは了解いたしました。  "

					+ "次は、「私の色は？」と聞いてください", favoriteColor);
			repromptText = "「私の色は？」と聞いてください";

		} else {
			// Render an error since we don't know what the users favorite color is.
			speechText = "一番好きな色は何だかは聞き取れませんでした。";
			repromptText = "一番好きな色は青です、のように、一番好きな色を教えてください。 ";
			isAskResponse = true;
		}

		ResponseBuilder responseBuilder = input.getResponseBuilder();

		responseBuilder.withSpeech(speechText).withShouldEndSession(false);

		if (isAskResponse) {
			responseBuilder.withShouldEndSession(false).withReprompt(repromptText);
		}

		return responseBuilder.build();
	}

}
