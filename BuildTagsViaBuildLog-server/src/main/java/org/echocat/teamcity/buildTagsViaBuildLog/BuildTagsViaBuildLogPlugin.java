package org.echocat.teamcity.buildTagsViaBuildLog;

import com.intellij.openapi.diagnostic.Logger;
import jetbrains.buildServer.log.Loggers;
import jetbrains.buildServer.messages.BuildMessage1;
import jetbrains.buildServer.serverSide.BuildServerAdapter;
import jetbrains.buildServer.serverSide.BuildServerListener;
import jetbrains.buildServer.serverSide.SRunningBuild;
import jetbrains.buildServer.util.EventDispatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Character.isWhitespace;

public class BuildTagsViaBuildLogPlugin extends BuildServerAdapter {

    private static final Logger SERVER_LOGGER = Loggers.SERVER;

    private final Map<String, BuildLogMessageHandler> messageHandlers;

    public BuildTagsViaBuildLogPlugin(@NotNull final EventDispatcher<BuildServerListener> serverDispatcher,
                                      @NotNull final Collection<BuildLogMessageHandler> messageHandlersToRegister) {
        messageHandlers = createMessageHandlersMap(messageHandlersToRegister);

        serverDispatcher.addListener(this);

        if (SERVER_LOGGER.isDebugEnabled()) {
            SERVER_LOGGER.debug("BuildTagsViaBuildLogPlugin loaded");
        }
    }

    @Override
    public void messageReceived(@NotNull SRunningBuild build, @NotNull BuildMessage1 message) {
        final Object value = message.getValue();
        if (value != null && value instanceof String) {
            final String text = (String) value;
            parseLogMessage(build, text);
        }
    }

    private void parseLogMessage(@NotNull SRunningBuild build, @NotNull String text) {
        if (!isTeamcityInteractionMessage(text)) {
            return;
        }

        String messageName = extractMessageName(text);

        BuildLogMessageHandler handler = messageHandlers.get(messageName);

        if (handler != null) {
            handler.handleMessage(build, text);
        }
    }

    @Nullable
    private String extractMessageName(@NotNull final String text) {
        final int textLength = text.length();

        /* skip preceding whitespaces */
        int indexStart = 11;
        while (indexStart < textLength && isWhitespace(text.charAt(indexStart))) {
            ++indexStart;
        }

        if (indexStart >= textLength) {
            return null;
        }

        int indexEnd = indexStart + 1;
        while (indexEnd < textLength && Character.isLetterOrDigit(text.charAt(indexEnd))) {
            ++indexEnd;
        }

        return text.substring(indexStart, indexEnd);
    }


    private boolean isTeamcityInteractionMessage(String text) {
        return text.startsWith("##teamcity[");
    }

    private static Map<String, BuildLogMessageHandler> createMessageHandlersMap(Collection<BuildLogMessageHandler> messageHandlersToRegister) {
        Map<String, BuildLogMessageHandler> messageHandlerMap = new HashMap<>(messageHandlersToRegister.size());

        for (BuildLogMessageHandler messageHandler : messageHandlersToRegister) {
            messageHandlerMap.put(messageHandler.acceptedMessageName(), messageHandler);
        }

        return messageHandlerMap;
    }



}


