package org.echocat.teamcity.buildTagsViaBuildLog;

import com.intellij.openapi.diagnostic.Logger;
import jetbrains.buildServer.log.Loggers;
import jetbrains.buildServer.serverSide.SRunningBuild;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isWhitespace;

public class AddBuildTagMessageHandler implements BuildLogMessageHandler {

    private static final Pattern BUILDTAG_MESSAGE_PATTERN = Pattern.compile("^##teamcity\\[addBuildTag\\s*'([^']*)'\\s*\\]$");
    private static final Logger SERVER_LOGGER = Loggers.SERVER;

    private static final String ACCEPTED_MESSAGE_NAME = "addBuildTag";

    @NotNull
    public String acceptedMessageName() {
        return ACCEPTED_MESSAGE_NAME;
    }

    public void handleMessage(@NotNull SRunningBuild runningBuild, @NotNull String message) {
        final Matcher matcher = BUILDTAG_MESSAGE_PATTERN.matcher(message);
        if (matcher.matches()) {
            final String newBuildTag = matcher.group(1);

            if (containsAtLeastOneCharacter(newBuildTag)) {
                addBuildTag(runningBuild, newBuildTag);
            }
        } else {
            if (SERVER_LOGGER.isDebugEnabled()) {
                SERVER_LOGGER.debug("Could not parse addBuildTag message. Build log message is: " + message);
            }
        }
    }

    private void addBuildTag(SRunningBuild build, String newBuildTag) {
        final Set<String> tags = new HashSet<>(build.getTags());
        tags.add(newBuildTag);

        build.setTags(build.getOwner(), new ArrayList<>(tags));
    }

    private static boolean containsAtLeastOneCharacter(String s) {
        return !isBlank(s);
    }

    private static boolean isBlank(String s) {
        /* copied from apache commons String Utils */
        final int len;
        if (s == null || (len = s.length()) == 0) {
            return true;
        }

        for (int i = 0; i < len; ++i) {
            if (!isWhitespace(s.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
