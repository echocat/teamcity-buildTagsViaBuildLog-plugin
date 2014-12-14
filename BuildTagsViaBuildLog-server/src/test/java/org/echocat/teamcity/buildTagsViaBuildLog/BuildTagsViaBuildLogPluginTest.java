package org.echocat.teamcity.buildTagsViaBuildLog;

import jetbrains.buildServer.messages.BuildMessage1;
import jetbrains.buildServer.serverSide.BuildServerListener;
import jetbrains.buildServer.util.EventDispatcher;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class BuildTagsViaBuildLogPluginTest {

    private static final EventDispatcher<BuildServerListener> EVENT_DISPATCHER =
            (EventDispatcher<BuildServerListener>) mock(EventDispatcher.class);
    private static final String TEXT_MESSAGE = "Text";

    @Test
    public void detects_messages_and_passes_the_to_handlers() {
        RecordingBuildLogMessageHandlerStub messageHandler1 = new RecordingBuildLogMessageHandlerStub("messageName1");
        RecordingBuildLogMessageHandlerStub messageHandler2 = new RecordingBuildLogMessageHandlerStub("messageName2");

        List<BuildLogMessageHandler> handlersToRegister = new ArrayList<>();
        handlersToRegister.add(messageHandler1);
        handlersToRegister.add(messageHandler2);

        BuildTagsViaBuildLogPlugin plugin = new BuildTagsViaBuildLogPlugin(EVENT_DISPATCHER, handlersToRegister);

        final RunningBuildStub build = new RunningBuildStub();
        plugin.messageReceived(build, buildMessage(TEXT_MESSAGE, "##teamcity[messageName1 'value']"));
        plugin.messageReceived(build, buildMessage(TEXT_MESSAGE, "sth else"));
        plugin.messageReceived(build, buildMessage(TEXT_MESSAGE, "##teamcity[messageName2'value']"));
        plugin.messageReceived(build, buildMessage(TEXT_MESSAGE, "##teamcity[messageName1]"));
        plugin.messageReceived(build, buildMessage(TEXT_MESSAGE, "messageName1 'value2'"));

        assertThat(messageHandler1.getRecordedMessages(), hasSize(2));
        assertThat(messageHandler2.getRecordedMessages(), hasSize(1));

        assertThat(messageHandler1.getRecordedMessages(), contains("##teamcity[messageName1 'value']", "##teamcity[messageName1]"));
        assertThat(messageHandler2.getRecordedMessages(), contains("##teamcity[messageName2'value']"));
    }

    private BuildMessage1 buildMessage(String typeId, Object value) {
        return new BuildMessage1(null, typeId, null, null, value);
    }

}
