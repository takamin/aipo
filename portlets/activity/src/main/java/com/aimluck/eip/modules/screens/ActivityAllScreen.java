/*
 * Aipo is a groupware program developed by TOWN, Inc.
 * Copyright (C) 2004-2015 TOWN, Inc.
 * http://www.aipo.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aimluck.eip.modules.screens;

import org.apache.jetspeed.services.logging.JetspeedLogFactoryService;
import org.apache.jetspeed.services.logging.JetspeedLogger;
import org.apache.turbine.util.ParameterParser;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.aimluck.eip.activity.ActivityAllSelectData;
import com.aimluck.eip.activity.util.ActivityUtils;
import com.aimluck.eip.common.ALEipConstants;
import com.aimluck.eip.util.ALEipUtils;

/**
 *
 */
public class ActivityAllScreen extends ALVelocityScreen {

  private static final JetspeedLogger logger = JetspeedLogFactoryService
    .getLogger(ActivityAllScreen.class.getName());

  /**
   * @param rundata
   * @param context
   * @throws Exception
   */
  @Override
  protected void doOutput(RunData rundata, Context context) throws Exception {

    try {
      ParameterParser parser = rundata.getParameters();

      ActivityUtils.passPSML(rundata, context, "p12f-filters", parser
        .getString(ALEipConstants.LIST_FILTER));
      ActivityUtils.passPSML(rundata, context, "p12g-filtertypes", parser
        .getString(ALEipConstants.LIST_FILTER_TYPE));
      ActivityAllSelectData listData = new ActivityAllSelectData();
      listData.initField();
      listData.setRowsNum(Integer.parseInt(ALEipUtils.getPortlet(
        rundata,
        context).getPortletConfig().getInitParameter("p1a-rows")));
      listData.setTableColumNum(Integer.parseInt(ALEipUtils.getPortlet(
        rundata,
        context).getPortletConfig().getInitParameter("p2e-rows")));
      listData.doViewList(this, rundata, context);
      String layout_template = "portlets/html/ja/ajax-activity-all.vm";
      setTemplate(rundata, context, layout_template);
    } catch (Exception ex) {
      logger.error("ActivityAllListScreen.doOutput", ex);
      ALEipUtils.redirectDBError(rundata);
    }
  }

  /**
   * @return
   */
  @Override
  protected String getPortletName() {
    return ActivityUtils.ACTIVITY_PORTLET_NAME;
  }

}
