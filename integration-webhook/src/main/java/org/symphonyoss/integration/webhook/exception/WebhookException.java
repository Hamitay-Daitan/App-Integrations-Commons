/**
 * Copyright 2016-2017 Symphony Integrations - Symphony LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.symphonyoss.integration.webhook.exception;

import org.symphonyoss.integration.exception.IntegrationRuntimeException;

/**
 * Abstract class to be used for all Webhook Exception.
 *
 * It contains the component name: Webhook Dispatcher
 *
 * Created by cmarcondes on 10/27/16.
 */
public abstract class WebhookException extends IntegrationRuntimeException {

  private static final String COMPONENT = "Webhook Dispatcher";

  public WebhookException(String message, String... solutions) {
    super(COMPONENT, message, solutions);
  }

  public WebhookException(String message, Throwable cause, String... solutions) {
    super(COMPONENT, message, cause, solutions);
  }

  public WebhookException(String message) {
    super(COMPONENT, message);
  }

  public WebhookException(String message, Throwable cause) {
    super(COMPONENT, message, cause);
  }
}
