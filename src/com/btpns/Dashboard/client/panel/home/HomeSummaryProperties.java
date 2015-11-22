package com.btpns.Dashboard.client.panel.home;

import com.btpns.Dashboard.client.model.home.HomeSummary;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface HomeSummaryProperties extends PropertyAccess<HomeSummary> {
	ModelKeyProvider<HomeSummary> id();

	ValueProvider<HomeSummary, String> name();

	ValueProvider<HomeSummary, String> value();
}