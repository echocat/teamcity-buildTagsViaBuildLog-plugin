package org.echocat.teamcity.buildTagsViaBuildLog;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class AddBuildTagMessageHandlerTest {

    @Test
    public void recognizes_valid_patterns_and_ignores_other_messages() {
        final AddBuildTagMessageHandler plugin = new AddBuildTagMessageHandler();

        final RunningBuildStub build = new RunningBuildStub();

        plugin.handleMessage(build, "any message");
        plugin.handleMessage(build, "##teamcity[addBuildTag 'cooltag']");
        plugin.handleMessage(build, "##teamcity[anyMessageType value='i like icecream']");
        plugin.handleMessage(build, "any message 2");
        plugin.handleMessage(build, "##teamcity[addBuildTag     'two words'    ]");
        plugin.handleMessage(build, "##teamcity");
        plugin.handleMessage(build, "##teamcity[addBuildTag    ]");

        final List<String> tags = build.getTags();

        assertThat(tags, Matchers.hasSize(2));
        assertThat("Invalid tags: " + listToString(tags), tags, containsInAnyOrder("cooltag", "two words"));
    }

    private String listToString(List<String> tags) {
        return Arrays.toString(tags.toArray());
    }
}