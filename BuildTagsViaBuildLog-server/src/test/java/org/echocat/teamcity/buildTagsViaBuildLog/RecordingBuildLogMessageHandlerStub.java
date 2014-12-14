package org.echocat.teamcity.buildTagsViaBuildLog;

import jetbrains.buildServer.serverSide.SRunningBuild;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecordingBuildLogMessageHandlerStub implements BuildLogMessageHandler {
    private final String acceptedMessageName;
    private final List<String> recordedMessages = new ArrayList<>();

    public RecordingBuildLogMessageHandlerStub(final String acceptedMessageName) {
        this.acceptedMessageName = acceptedMessageName;
    }

    @NotNull
    @Override
    public String acceptedMessageName() {
        return acceptedMessageName;
    }

    @Override
    public void handleMessage(@NotNull final SRunningBuild runningBuild, @NotNull final String message) {
        recordedMessages.add(message);
    }

    public List<String> getRecordedMessages() {
        return recordedMessages;
    }
}
