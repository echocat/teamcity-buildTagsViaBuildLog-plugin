package org.echocat.teamcity.buildTagsViaBuildLog;

import com.intellij.openapi.diagnostic.Logger;
import jetbrains.buildServer.log.Loggers;
import jetbrains.buildServer.messages.BuildMessage1;
import jetbrains.buildServer.serverSide.BuildServerAdapter;
import jetbrains.buildServer.serverSide.BuildServerListener;
import jetbrains.buildServer.serverSide.SRunningBuild;
import jetbrains.buildServer.util.EventDispatcher;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuildTagsViaBuildLogPlugin extends BuildServerAdapter {

    public static final Pattern BUILTAG_MESSAGE_PATTERN = Pattern.compile("^##teamcity\\[addBuildTag\\s*'([^']*)'\\s*\\]$");
    public static final Logger SERVER_LOGGER = Loggers.SERVER;

    public BuildTagsViaBuildLogPlugin(@NotNull EventDispatcher<BuildServerListener> serverDispatcher) {
        serverDispatcher.addListener(this);
        SERVER_LOGGER.debug("BuildTagsViaBuildLogPlugin loaded");
    }

    @Override
    public void messageReceived(@NotNull SRunningBuild build, @NotNull BuildMessage1 message) {
        final Object value = message.getValue();
        if (value != null && value instanceof String) {
            final String text = (String) value;
            parseLogMessage(build, text);
        }
    }

    @SuppressWarnings("MethodWithMultipleReturnPoints")
    private void parseLogMessage(@NotNull SRunningBuild build, @NotNull String text) {
        if (!isBuildLogTagMessage(text)) {
            return;
        }

        final Matcher matcher = BUILTAG_MESSAGE_PATTERN.matcher(text);
        if (matcher.matches()) {
            final String newBuildTag = matcher.group(1);

            if (containsAtLeastOneCharacter(newBuildTag)) {
                addBuildTag(build, newBuildTag);
            }
        } else {
            SERVER_LOGGER.debug("Could not parse addBuildTag message. Build log message is: " + text);
        }
    }

    private void addBuildTag(SRunningBuild build, String newBuildTag) {
        final Set<String> tags = new HashSet<String>(build.getTags());
        tags.add(newBuildTag);

        build.setTags(build.getOwner(), new ArrayList<String>(tags));
    }

    private boolean isBuildLogTagMessage(String text) {
        return text.startsWith("##teamcity[addBuildTag");
    }

    private static boolean containsAtLeastOneCharacter(String s) {
        return !isBlank(s);
    }

    @SuppressWarnings("MethodWithMultipleReturnPoints")
    private static boolean isBlank(String s) {
        /* copied from apache commons String Utils */
        final int len;
        if (s == null || (len = s.length()) == 0) {
            return true;
        }

        for (int i = 0; i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }

        return true;
    }


}


