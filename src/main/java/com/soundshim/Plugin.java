package com.soundshim;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.AreaSoundEffectPlayed;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.PluginDescriptor;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@PluginDescriptor(
	name = "Sound Shim"
)
public class Plugin extends net.runelite.client.plugins.Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private Config config;

	@Override
	protected void startUp() throws Exception
	{
		log.debug("Sound Shim started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.debug("Sound Shim stopped!");
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		log.debug("Config changed: {}, {}", event.getGroup(), event.getKey());
		if (!event.getGroup().equals("soundshim")) {return;}

		if (event.getKey().equals("customSoundSwaps")) {
			replacements = parseColonCsv(config.customSoundSwaps());
			log.debug("Reload replacements, count: {}", replacements.size());
		} else if (event.getKey().equals("playSound")) {
			clientThread.invoke(() -> client.playSoundEffect(config.playSound()));
			log.debug("Play sound from spinner");
		}
	}

	// id:id
	private Map<Integer, Integer> replacements = new HashMap<>();

	@Subscribe
	public void onAreaSoundEffectPlayed(AreaSoundEffectPlayed event)
	{
		int soundId = event.getSoundId();
		if (!config.customSoundSwaps().isEmpty()) {
			if (replacements.containsKey(soundId)) {
				event.consume();
				int replacement = replacements.get(soundId);
				client.playSoundEffect(replacement);
				log.debug("Replaced ID {} with {}", soundId, replacement);
			}
		}
	}

	private Map<Integer, Integer> parseColonCsv(String csv) {
		HashMap<Integer, Integer> labelsMap = new HashMap<>();
		if (csv == null || csv.isEmpty()) {
			return labelsMap;
		}
		for (String entry : csv.split("[,\\n]+")) {
			String[] parts = entry.trim().split(":");
			if (parts.length == 2
					&& NumberUtils.isDigits(parts[0].trim())
					&& NumberUtils.isDigits(parts[1].trim())) {
				labelsMap.put(Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()));
			}
		}
		return labelsMap;
	}

	@Provides
	Config provideConfig(ConfigManager configManager) {return configManager.getConfig(Config.class);}
}
