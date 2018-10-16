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
import java.util.HashSet;
import java.util.Map;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.amazon.ask.musicplayer.handlers.CancelandStopIntentHandler;
import com.amazon.ask.musicplayer.handlers.LaunchRequestHandler;

public class MusicPlayerStreamHandler extends SkillStreamHandler {
	public static Map<String, Integer> currentItemIndex = new HashMap<>();
	public static final String[] MUSICS = new String[] {
			"https://freemusicdownloads.world/wp-content/uploads/2017/05/Justin-Bieber-Sorry-PURPOSE-The-Movement.mp3",
			"https://freemusicarchive.org/file/music/WFMU/Gillicuddy/Plays_Guitar/Gillicuddy_-_05_-_Springish.mp3",
			"https://www.mfiles.co.uk/mp3-downloads/chopin-nocturne-op9-no2.mp3" };

	private static Skill getSkill() {
		return Skills.standard().addRequestHandlers(new LaunchRequestHandler(), new CancelandStopIntentHandler())
				.build();
	}

	public MusicPlayerStreamHandler() {
		super(getSkill());
	}

}
