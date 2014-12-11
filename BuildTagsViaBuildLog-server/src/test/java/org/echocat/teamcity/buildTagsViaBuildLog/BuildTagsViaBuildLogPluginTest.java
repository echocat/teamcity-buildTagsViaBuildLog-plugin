package org.echocat.teamcity.buildTagsViaBuildLog;

import jetbrains.buildServer.messages.BuildMessage1;
import jetbrains.buildServer.serverSide.BuildServerListener;
import jetbrains.buildServer.util.EventDispatcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class BuildTagsViaBuildLogPluginTest {

    @Test
    public void recognizes_valid_patterns_and_ignores_other_messages() {
        final EventDispatcher<BuildServerListener> eventDispatcher = mock(EventDispatcher.class);

        final BuildTagsViaBuildLogPlugin plugin = new BuildTagsViaBuildLogPlugin(eventDispatcher);

        final RunningBuildStub build = new RunningBuildStub();

        plugin.messageReceived(build, buildMessage("Text", "any message"));
        plugin.messageReceived(build, buildMessage("Text", "##teamcity[addBuildTag 'cooltag']"));
        plugin.messageReceived(build, buildMessage("Text", "##teamcity[anyMessageType value='i like icecream']"));
        plugin.messageReceived(build, buildMessage("Text", "any message 2"));
        plugin.messageReceived(build, buildMessage("Text", "##teamcity[addBuildTag     'two words'    ]"));
        plugin.messageReceived(build, buildMessage("Text", "##teamcity"));
        plugin.messageReceived(build, buildMessage("Text", "##teamcity[addBuildTag    ]"));

        final List<String> tags = build.getTags();

        assertThat(tags, Matchers.hasSize(2));
        assertThat("Invalid tags: " + listToString(tags), tags, containsInAnyOrder("cooltag", "two words"));
    }

    private String listToString(List<String> tags) {
        return Arrays.toString(tags.toArray());
    }

    private BuildMessage1 buildMessage(String typeId, Object value) {
        return new BuildMessage1(null, typeId, null, null, value);
    }

}