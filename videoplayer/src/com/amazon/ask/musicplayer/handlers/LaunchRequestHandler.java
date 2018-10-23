/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.amazon.ask.musicplayer.handlers;

import static com.amazon.ask.request.Predicates.requestType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.interfaces.display.BackButtonBehavior;
import com.amazon.ask.model.interfaces.display.Image;
import com.amazon.ask.model.interfaces.display.ImageInstance;
import com.amazon.ask.model.interfaces.display.ListItem;
import com.amazon.ask.model.interfaces.display.ListTemplate1;
import com.amazon.ask.model.interfaces.display.ListTemplate1.Builder;
import com.amazon.ask.model.interfaces.display.PlainText;
import com.amazon.ask.model.interfaces.display.TextContent;
import com.amazon.ask.musicplayer.VideoPlayerStreamHandler;
import com.amazon.ask.response.ResponseBuilder;

public class LaunchRequestHandler implements RequestHandler {
	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(requestType(LaunchRequest.class));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String speechText = "アレクサビデオ再生サンプルスキルへようこそ。好きなビデオを選択してください。";
		ResponseBuilder responseBuilder = input.getResponseBuilder();
		responseBuilder.withSpeech(speechText);
		ListTemplate1 listTemplate = buildList();
		responseBuilder.addRenderTemplateDirective(listTemplate);
		return responseBuilder.build();
	}

	private ListTemplate1 buildList() {
		Builder listBuilder = ListTemplate1.builder();
		listBuilder.withBackButton(BackButtonBehavior.HIDDEN);
		listBuilder.withTitle("タイトル");

		String imageUrl = "https://images.pexels.com/photos/1098581/pexels-photo-1098581.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940";
		listBuilder.withBackgroundImage(buildAlexaImageFromUrl(imageUrl));
		listBuilder.withToken("token");
		listBuilder.withListItems(this.buildItemList());
		listBuilder.build();
		return listBuilder.build();
	}

	private Image buildAlexaImageFromUrl(String imageUrl) {
		return Image.builder().addSourcesItem(ImageInstance.builder().withUrl(imageUrl).build()).build();
	}

	private List<ListItem> buildItemList() {
		List<ListItem> list = new ArrayList<>();
		for (int i = 0; i < VideoPlayerStreamHandler.VIDEO_IMAGES.length; i++) {
			com.amazon.ask.model.interfaces.display.ListItem.Builder listItemBuilder = ListItem.builder();
			listItemBuilder.withToken("" + i);
			String primaryText = "ビデオ" + (i + 1);
			String secondaryText = "ビデオsecondary" + (i + 1);
			String tertiaryText = "ビデオTertiaryText" + (i + 1);
			TextContent textContent = TextContent.builder().withPrimaryText(buildAlexaPlainText(primaryText))
					.withSecondaryText(buildAlexaPlainText(secondaryText))
					.withTertiaryText(buildAlexaPlainText(tertiaryText)).build();
			listItemBuilder.withTextContent(textContent);
			listItemBuilder.withImage(this.buildAlexaImageFromUrl(VideoPlayerStreamHandler.VIDEO_IMAGES[i]));
			list.add(listItemBuilder.build());
		}

		return list;
	}

	private PlainText buildAlexaPlainText(String text) {
		return PlainText.builder().withText(text).build();
	}
}
