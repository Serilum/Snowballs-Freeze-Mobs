package com.natamus.snowballsfreezemobs.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.snowballsfreezemobs.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry(min = 0, max = 3600000) public static int freezeTimeMs = 5000;

	public static void initConfig() {
		configMetaData.put("freezeTimeMs", Arrays.asList(
			"The amount of time in ms the mob hit will be frozen for."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}