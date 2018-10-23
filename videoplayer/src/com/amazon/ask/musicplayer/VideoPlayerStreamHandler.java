/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.amazon.ask.musicplayer;

import java.util.HashMap;
import java.util.Map;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.amazon.ask.musicplayer.handlers.CancelandStopIntentHandler;
import com.amazon.ask.musicplayer.handlers.LaunchRequestHandler;
import com.amazon.ask.musicplayer.handlers.SelectVideoHandler;

public class VideoPlayerStreamHandler extends SkillStreamHandler {
	public static Map<String, Integer> currentItemIndex = new HashMap<>();
	public static final String[] VIDEOS = new String[] {
			"https://www.sample-videos.com/video/mp4/480/big_buck_bunny_480p_1mb.mp4",
			"https://www.sample-videos.com/video/mp4/360/big_buck_bunny_360p_5mb.mp4",
			"https://www.sample-videos.com/video/mp4/240/big_buck_bunny_240p_1mb.mp4" };

	public static final String[] VIDEO_IMAGES = new String[] {
			"https://images.pexels.com/photos/1118866/pexels-photo-1118866.jpeg?cs=srgb&dl=clouds-high-lake-1118866.jpg&fm=jpg",
			"https://images.pexels.com/photos/1117267/pexels-photo-1117267.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
			"https://images.pexels.com/photos/1123491/pexels-photo-1123491.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940" };

	private static Skill getSkill() {
		return Skills.standard().addRequestHandlers(new LaunchRequestHandler(), new CancelandStopIntentHandler(),
				new SelectVideoHandler()).build();
	}

	public VideoPlayerStreamHandler() {
		super(getSkill());
	}

}
