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

package org.symphonyoss.integration.api.client;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.symphonyoss.integration.logging.DistributedTracingUtils.TRACE_ID;

import org.slf4j.MDC;

import java.util.Map;

/**
 * Utility methods for integration client decorators.
 * Created by Milton Quilzini on 25/11/16.
 */
public class ApiClientDecoratorUtils {

  /**
   * If "X-Trace-Id" is present on {@link MDC}, it will be aggregated into the header parameters.
   * It does override an already existent "X-Trace-Id" on the passed header parameters since those are incorporated on
   * MDC at the moment the request is received by our filters.
   * @param headerParams to be modified accordingly.
   */
  public static void setHeaderTraceId(Map<String, String> headerParams) {
    if (isNotBlank(MDC.get(TRACE_ID))) {
      headerParams.put(TRACE_ID, MDC.get(TRACE_ID));
    }
  }
}
