package org.echocat.teamcity.buildTagsViaBuildLog;

import jetbrains.buildServer.serverSide.SRunningBuild;
import org.jetbrains.annotations.NotNull;

public interface BuildLogMessageHandler {
    @NotNull public String acceptedMessageName();
    public void handleMessage(@NotNull final SRunningBuild runningBuild, @NotNull final String message);
}
