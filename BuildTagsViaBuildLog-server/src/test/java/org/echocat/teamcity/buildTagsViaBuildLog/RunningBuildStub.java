package org.echocat.teamcity.buildTagsViaBuildLog;

import jetbrains.buildServer.AgentRestrictor;
import jetbrains.buildServer.BuildProblemData;
import jetbrains.buildServer.StatusDescriptor;
import jetbrains.buildServer.issueTracker.Issue;
import jetbrains.buildServer.messages.BuildMessage1;
import jetbrains.buildServer.messages.Status;
import jetbrains.buildServer.parameters.ParametersProvider;
import jetbrains.buildServer.parameters.ValueResolver;
import jetbrains.buildServer.serverSide.*;
import jetbrains.buildServer.serverSide.artifacts.BuildArtifacts;
import jetbrains.buildServer.serverSide.artifacts.BuildArtifactsViewMode;
import jetbrains.buildServer.serverSide.artifacts.SArtifactDependency;
import jetbrains.buildServer.serverSide.buildLog.BuildLog;
import jetbrains.buildServer.serverSide.comments.Comment;
import jetbrains.buildServer.serverSide.impl.RunningBuildState;
import jetbrains.buildServer.serverSide.userChanges.CanceledInfo;
import jetbrains.buildServer.serverSide.vcs.VcsLabel;
import jetbrains.buildServer.tests.TestInfo;
import jetbrains.buildServer.users.SUser;
import jetbrains.buildServer.users.User;
import jetbrains.buildServer.users.UserSet;
import jetbrains.buildServer.vcs.SVcsModification;
import jetbrains.buildServer.vcs.SelectPrevBuildPolicy;
import jetbrains.buildServer.vcs.VcsException;
import jetbrains.buildServer.vcs.VcsRootInstanceEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jmock.util.NotImplementedException;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

import static org.mockito.Mockito.mock;

public class RunningBuildStub implements SRunningBuild {
    private List<String> tags = new ArrayList<String>();

    public String getCurrentPath() {
        throw new NotImplementedException();
    }

    @NotNull
    public File getArtifactsDirectory() {
        throw new NotImplementedException();
    }

    @NotNull
    public BuildArtifacts getArtifacts(@NotNull BuildArtifactsViewMode buildArtifactsViewMode) {
        throw new NotImplementedException();
    }

    @NotNull
    public List<SArtifactDependency> getArtifactDependencies() {
        throw new NotImplementedException();
    }

    public boolean isArtifactsExists() {
        return false;
    }

    public boolean isHasInternalArtifactsOnly() {
        return false;
    }

    public boolean isResponsibleNeeded() {
        return false;
    }

    @NotNull
    public BuildLog getBuildLog() {
        throw new NotImplementedException();
    }

    @NotNull
    public ShortStatistics getShortStatistics() {
        throw new NotImplementedException();
    }

    @NotNull
    public BuildStatistics getFullStatistics() {
        throw new NotImplementedException();
    }

    @NotNull
    public BuildStatistics getBuildStatistics(@NotNull BuildStatisticsOptions buildStatisticsOptions) {
        throw new NotImplementedException();
    }

    @Nullable
    public SUser getOwner() {
        return mock(SUser.class);
    }

    public TriggeredBy getTriggeredBy() {
        throw new NotImplementedException();
    }

    @NotNull
    public Date getStartDate() {
        throw new NotImplementedException();
    }

    public String getAgentName() {
        throw new NotImplementedException();
    }

    public long getBuildId() {
        return 0;
    }

    public StatusDescriptor getStatusDescriptor() {
        throw new NotImplementedException();
    }

    public List<String> getLogMessages(int i, int i1) {
        throw new NotImplementedException();
    }

    public List<TestInfo> getTestMessages(int i, int i1) {
        throw new NotImplementedException();
    }

    public List<String> getCompilationErrorMessages() {
        throw new NotImplementedException();
    }

    @Nullable
    public SBuildType getBuildType() {
        throw new NotImplementedException();
    }

    @NotNull
    public String getBuildTypeId() {
        throw new NotImplementedException();
    }

    @NotNull
    public String getBuildTypeExternalId() {
        throw new NotImplementedException();
    }

    @NotNull
    public String getBuildTypeName() {
        throw new NotImplementedException();
    }

    @NotNull
    public String getFullName() {
        throw new NotImplementedException();
    }

    @Nullable
    public String getProjectId() {
        throw new NotImplementedException();
    }

    @Nullable
    public String getProjectExternalId() {
        throw new NotImplementedException();
    }

    @NotNull
    public DownloadedArtifacts getDownloadedArtifacts() {
        throw new NotImplementedException();
    }

    @NotNull
    public DownloadedArtifacts getProvidedArtifacts() {
        throw new NotImplementedException();
    }

    public boolean isUsedByOtherBuilds() {
        return false;
    }

    @NotNull
    public List<SVcsModification> getContainingChanges() {
        throw new NotImplementedException();
    }

    public boolean isPersonal() {
        return false;
    }

    public Status getBuildStatus() {
        throw new NotImplementedException();
    }

    public boolean isFinished() {
        return false;
    }

    @NotNull
    public List<SVcsModification> getChanges(SelectPrevBuildPolicy selectPrevBuildPolicy, boolean b) {
        throw new NotImplementedException();
    }

    public UserSet<SUser> getCommitters(SelectPrevBuildPolicy selectPrevBuildPolicy) {
        throw new NotImplementedException();
    }

    public String getBuildNumber() {
        throw new NotImplementedException();
    }

    @Nullable
    public Date getFinishDate() {
        throw new NotImplementedException();
    }

    public CanceledInfo getCanceledInfo() {
        throw new NotImplementedException();
    }

    public long getDuration() {
        return 0;
    }

    public boolean isOutOfChangesSequence() {
        return false;
    }

    public List<String> getTags() {
        return Collections.unmodifiableList(tags);
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setTags(User user, List<String> tags) {
        this.tags = tags;
    }

    @NotNull
    public byte[] getFileContent(String s) throws VcsException {
        return new byte[0];
    }

    public List<BuildRevision> getRevisions() {
        throw new NotImplementedException();
    }

    public List<VcsLabel> getLabels() {
        throw new NotImplementedException();
    }

    @NotNull
    public Date getQueuedDate() {
        throw new NotImplementedException();
    }

    @NotNull
    public Date getServerStartDate() {
        throw new NotImplementedException();
    }

    public List<VcsRootInstanceEntry> getVcsRootEntries() {
        throw new NotImplementedException();
    }

    @Nullable
    public Date getClientStartDate() {
        throw new NotImplementedException();
    }

    public boolean isStartedOnAgent() {
        return false;
    }

    @NotNull
    public Date convertToServerTime(@NotNull Date date) {
        throw new NotImplementedException();
    }

    @NotNull
    public Date convertToAgentTime(@NotNull Date date) {
        throw new NotImplementedException();
    }

    @Nullable
    public String getBuildDescription() {
        throw new NotImplementedException();
    }

    @NotNull
    public ParametersProvider getParametersProvider() {
        throw new NotImplementedException();
    }

    @NotNull
    public ValueResolver getValueResolver() {
        throw new NotImplementedException();
    }

    @Nullable
    public Comment getBuildComment() {
        throw new NotImplementedException();
    }

    public void setBuildComment(@Nullable User user, @Nullable String s) {

    }

    public boolean isPinned() {
        return false;
    }

    @NotNull
    public Collection<Issue> getRelatedIssues() {
        throw new NotImplementedException();
    }

    public boolean isHasRelatedIssues() {
        return false;
    }

    @NotNull
    public Map<String, String> getBuildOwnParameters() {
        throw new NotImplementedException();
    }

    public String getRawBuildNumber() {
        throw new NotImplementedException();
    }

    public boolean isInternalError() {
        return false;
    }

    @Nullable
    public String getFirstInternalError() {
        throw new NotImplementedException();
    }

    @Nullable
    public String getFirstInternalErrorMessage() {
        throw new NotImplementedException();
    }

    @Nullable
    public TimeZone getClientTimeZone() {
        throw new NotImplementedException();
    }

    @NotNull
    public SBuildAgent getAgent() {
        throw new NotImplementedException();
    }

    public void addBuildProblem(@NotNull BuildProblemData buildProblemData) {

    }

    public boolean hasBuildProblemOfType(@NotNull String s) {
        return false;
    }

    @NotNull
    public List<BuildProblemData> getFailureReasons() {
        throw new NotImplementedException();
    }

    public void muteBuildProblems(@NotNull User user, boolean b, @NotNull String s) {

    }

    public BuildProblemData addUserBuildProblem(@NotNull User user, @NotNull String s) {
        throw new NotImplementedException();
    }

    @Nullable
    public Branch getBranch() {
        throw new NotImplementedException();
    }

    @Nullable
    public SFinishedBuild getPreviousFinished() {
        throw new NotImplementedException();
    }

    @Nullable
    public BigDecimal getStatisticValue(String s) {
        throw new NotImplementedException();
    }

    @NotNull
    public Map<String, BigDecimal> getStatisticValues() {
        throw new NotImplementedException();
    }

    @Nullable
    public Integer getQueuedAgentId() {
        throw new NotImplementedException();
    }

    @Nullable
    public AgentRestrictor getQueuedAgentRestrictor() {
        throw new NotImplementedException();
    }

    public boolean isInterrupted() {
        return false;
    }

    public int getSignature() {
        return 0;
    }

    public void setSignature(int i) {

    }

    public int getCompletedPercent() {
        return 0;
    }

    public void addBuildMessages(@NotNull List<BuildMessage1> list) {

    }

    public void addBuildMessage(@NotNull BuildMessage1 buildMessage1) {

    }

    public void setBuildNumber(@NotNull String s) {

    }

    public void setBuildStatus(Status status) {

    }

    public void setInterrupted(@NotNull RunningBuildState runningBuildState, @Nullable User user, @Nullable String s) {

    }

    public String getAgentAccessCode() {
        throw new NotImplementedException();
    }

    public boolean isProbablyHanging() {
        return false;
    }

    public boolean isOutdated() {
        return false;
    }

    public Date getLastBuildActivityTimestamp() {
        throw new NotImplementedException();
    }

    public long getTimeSpentSinceLastBuildActivity() {
        return 0;
    }

    public void stop(@Nullable User user, @Nullable String s) {

    }

    @NotNull
    public Collection<SBuildFeatureDescriptor> getBuildFeaturesOfType(@NotNull String s) {
        throw new NotImplementedException();
    }

    public long getEstimationForTimeLeft() {
        return 0;
    }

    public long getDurationEstimate() {
        return 0;
    }

    public long getDurationOvertime() {
        return 0;
    }

    public long getElapsedTime() {
        return 0;
    }

    @NotNull
    public BuildPromotion getBuildPromotion() {
        throw new NotImplementedException();
    }

    @Nullable
    public SBuild getSequenceBuild() {
        throw new NotImplementedException();
    }
}
