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

import com.aimluck.eip.account.AccountPasswdFormData;
import com.aimluck.eip.account.util.AccountUtils;
import com.aimluck.eip.util.ALEipUtils;

/**
 * 管理者のパスワード変更フォームを処理するクラスです。 <br />
 * 
 */
public class AccountAdminFormScreen extends ALVelocityScreen {

  /** logger */
  private static final JetspeedLogger logger = JetspeedLogFactoryService
    .getLogger(AccountAdminFormScreen.class.getName());

  /**
   * 
   * @param rundata
   * @param context
   * @throws Exception
   */
  @Override
  protected void doOutput(RunData rundata, Context context) throws Exception {

    try {
      doAccount_adminpasswd_form(rundata, context);
    } catch (Exception ex) {
      logger.error("AccountAdminFormScreen.doOutput.", ex);
      ALEipUtils.redirectDBError(rundata);
    }
  }

  protected void doAccount_adminpasswd_form(RunData rundata, Context context) {
    AccountPasswdFormData formData = new AccountPasswdFormData();
    formData.initField();
    formData.doViewForm(this, rundata, context);
    String layout_template =
      "portlets/html/ja/ajax-account-adminpasswd-form.vm";
    setTemplate(rundata, context, layout_template);
  }

  /**
   * @return
   */
  @Override
  protected String getPortletName() {
    return AccountUtils.ACCOUNT_PORTLET_NAME;
  }
}
