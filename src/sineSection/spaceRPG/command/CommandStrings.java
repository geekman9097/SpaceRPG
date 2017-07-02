package sineSection.spaceRPG.command;

import java.util.HashMap;
import java.util.Map;

public class CommandStrings {

	private static Map<String, CommandString> validCommands = addCommands();
	
	private static Map<String, CommandString> addCommands() {
		Map<String, CommandString> result = new HashMap<>();
		for(CommandString command : CommandString.values()) {
			result.put(command.getCall(), command);
		}
		return result;
	}
	
	public static CommandString getCommandString(String commandString) {
		return validCommands.getOrDefault(commandString, CommandString.UNKNOWN);
	}
	
	public static boolean isCommand(String commandString) {
		return validCommands.containsKey(commandString);
	}
	
	public static String getCommands() {
		StringBuilder result = new StringBuilder("Commands");
		validCommands.keySet().forEach((command) -> result.append(" " + command));
		return result.toString();
	}

}
