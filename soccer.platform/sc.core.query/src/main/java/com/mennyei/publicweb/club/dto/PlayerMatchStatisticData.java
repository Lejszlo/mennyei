package com.mennyei.publicweb.club.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
public class PlayerMatchStatisticData {
	
	@Getter
	private int yellowCardAmount;
	
	@Getter
	private int redCardAmount;
	
	@Getter
	private int scoredGoalAmount;
	
	@Getter
	private int scoredOwnGoalAmount;
	
	@Getter
	private int playedMinute;
	
	@Getter
	private int totalMatch;
	
	@Getter
	private int substitutionIn;
	
	@Getter
	private int substitutionOut;
	
	@Getter
	private int starter;
	
	@Getter
	private int subtitution;

	public void incraseYellowCardAmount(int yellowCardAmount) {
		this.yellowCardAmount += yellowCardAmount;
	}

	public void incraseRedCardAmount(int redCardAmount) {
		this.redCardAmount += redCardAmount;
	}

	public void incraseScoredGoalAmount(int scoredGoalAmount) {
		this.scoredGoalAmount += scoredGoalAmount;
	}

	public void incraseScoredOwnGoalAmount(int scoredOwnGoalAmount) {
		this.scoredOwnGoalAmount += scoredOwnGoalAmount;
	}

	public void incrasePlayedMinute(int playedMinute) {
		this.playedMinute += playedMinute;
	}

	public void incraseTotalMatch(int totalMatch) {
		this.totalMatch += totalMatch;
	}

	public void incraseSubstitutionIn(int substitutionIn) {
		this.substitutionIn += substitutionIn;
	}

	public void incraseSubstitutionOut(int substitutionOut) {
		this.substitutionOut += substitutionOut;
	}

	public void incraseStarter(int starter) {
		this.starter += starter;
	}

	public void incraseSubtitution(int subtitution) {
		this.subtitution += subtitution;
	}
	
}
