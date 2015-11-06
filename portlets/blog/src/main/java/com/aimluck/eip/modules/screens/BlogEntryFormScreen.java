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
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.aimluck.eip.blog.BlogEntryFormData;
import com.aimluck.eip.blog.util.BlogUtils;
import com.aimluck.eip.util.ALEipUtils;

/**
 * ブログエントリーを処理するクラスです。 <br />
 * 
 */
public class BlogEntryFormScreen extends ALVelocityScreen {

  /** logger */
  private static final JetspeedLogger logger = JetspeedLogFactoryService
    .getLogger(BlogEntryFormScreen.class.getName());

  /**
   * 
   * @param rundata
   * @param context
   * @throws Exception
   */
  @Override
  protected void doOutput(RunData rundata, Context context) throws Exception {

    try {
      doBlogEntry_form(rundata, context);
    } catch (Exception ex) {
      logger.error("[BlogEntryFormScreen] Exception.", ex);
      ALEipUtils.redirectDBError(rundata);
    }
  }

  protected void doBlogEntry_form(RunData rundata, Context context) {
    BlogEntryFormData formData = new BlogEntryFormData();
    formData.initField();
    formData.loadThemaList(rundata, context);
    formData.doViewForm(this, rundata, context);

    if (formData.getThemaId().getValue() == 0) {
      formData.setThemaId(1);
    }

    String layout_template = "portlets/html/ja/ajax-blog-entry-form.vm";
    setTemplate(rundata, context, layout_template);
  }

  /**
   * @return
   */
  @Override
  protected String getPortletName() {
    return BlogUtils.BLOG_PORTLET_NAME;
  }
}
