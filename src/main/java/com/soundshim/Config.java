package com.soundshim;

import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.*;


@ConfigGroup("soundshim")
public interface Config extends net.runelite.client.config.Config
{
//	@ConfigItem(
//			keyName = "displayAreaSounds",
//			name = "Area Sounds",
//			description = "Visualize the area sounds",
//			position = 1
//	)
//	default boolean displayAreaSounds() {
//		return false;
//	}
//
//	@ConfigItem(
//			keyName = "soundCountLimit",
//			name = "Sound Count Limit",
//			description = "The max number of sounds to show in the overlay",
//			position = 4
//	)
//	default int soundCountLimit() {
//		return 5;
//	}
//
//	@ConfigItem(
//			keyName = "ignoredSounds",
//			name = "Ignored Sounds",
//			description = "A list of sounds that should be ignored. Separate ids with commas (,)",
//			position = 17
//	)
//	default String ignoredSounds() {
//		return "";
//	}
//
//	@ConfigItem(
//			keyName = "blacklistedAreaSounds",
//			name = "Blacklist Area Sounds",
//			description = "Sound ids consumed regardless of the 'Consume Area Sound Effects' config option being enabled<br>" +
//					"Format: 123,456,789",
//			position = 5
//	)
//	default String blacklistedAreaSounds()
//	{
//		return "";
//	}
//
//	@ConfigItem(
//			keyName = "customSoundLabels",
//			name = "Custom Sound Labels",
//			description = "A list of custom sound labels. Format: soundId:label, separated by a new line (e.g. 369:Cow attacks)",
//			position = 20
//	)
//	default String customSoundLabels() {
//		return "";
//	}

	@ConfigItem(
			keyName = "customSoundSwaps",
			name = "Custom Sound Swaps",
			description = "List of sound ID pairs (id:id), separated by commas or newlines."
					+ "\nThe first ID is replaced by the second. For example  111:222, 333:444",
			position = 0
	)
	default String customSoundSwaps() {
		return "";
	}

	@ConfigItem(
			keyName = "playSound",
			name = "Play Sound ID",
			description = "",
			position = 10
	)
	default int playSound()
	{
		return 0;
	}
}
