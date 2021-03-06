package slacknotifications.teamcity.extension.bean;

import slacknotifications.teamcity.BuildStateEnum;
import slacknotifications.teamcity.settings.SlackNotificationConfig;

import java.util.ArrayList;
import java.util.List;

public class SlacknotificationConfigAndBuildTypeListHolder {
	public String channel;
	public String uniqueKey; 
	public boolean enabled;
	public String payloadFormatForWeb = "Unknown";
	public List<StateBean> states = new ArrayList<StateBean>();
	public boolean allBuildTypesEnabled;
	public boolean subProjectsEnabled;
	private List<SlacknotificationBuildTypeEnabledStatusBean> builds = new ArrayList<SlacknotificationBuildTypeEnabledStatusBean>();
	private String enabledEventsListForWeb;
	private String enabledBuildsListForWeb;
	private boolean mentionChannelEnabled;
	private boolean mentionSlackUserEnabled;
	
	public SlacknotificationConfigAndBuildTypeListHolder(SlackNotificationConfig config) {
		channel = config.getChannel();
		uniqueKey = config.getUniqueKey();
		enabled = config.getEnabled();
		setEnabledEventsListForWeb(config.getEnabledListAsString());
		setEnabledBuildsListForWeb(config.getBuildTypeCountAsFriendlyString());
		allBuildTypesEnabled = config.isEnabledForAllBuildsInProject();
		subProjectsEnabled = config.isEnabledForSubProjects();
		for (BuildStateEnum state : config.getBuildStates().getStateSet()){
			states.add(new StateBean(state.getShortName(), config.getBuildStates().enabled(state)));
		}
		mentionChannelEnabled = config.getMentionChannelEnabled();
		mentionSlackUserEnabled = config.getMentionSlackUserEnabled();
	}

	public List<SlacknotificationBuildTypeEnabledStatusBean> getBuilds() {
		return builds;
	}
	
	public String getEnabledBuildTypes(){
		StringBuilder types = new StringBuilder();
		for (SlacknotificationBuildTypeEnabledStatusBean build : getBuilds()){
			if (build.enabled){
				types.append(build.buildTypeId).append(",");
			}
		}
		return types.toString();
		
	}

	public void setBuilds(List<SlacknotificationBuildTypeEnabledStatusBean> builds) {
		this.builds = builds;
	}
	
	
	public void addSlackNotificationBuildType(SlacknotificationBuildTypeEnabledStatusBean status){
		this.builds.add(status);
	}

	public String getEnabledEventsListForWeb() {
		return enabledEventsListForWeb;
	}

	public void setEnabledEventsListForWeb(String enabledEventsListForWeb) {
		this.enabledEventsListForWeb = enabledEventsListForWeb;
	}

	public String getEnabledBuildsListForWeb() {
		return enabledBuildsListForWeb;
	}

	public void setEnabledBuildsListForWeb(String enabledBuildsListForWeb) {
		this.enabledBuildsListForWeb = enabledBuildsListForWeb;
	}
	
}
